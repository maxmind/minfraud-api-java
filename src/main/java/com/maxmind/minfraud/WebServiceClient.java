package com.maxmind.minfraud;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.InjectableValues;
import com.fasterxml.jackson.databind.InjectableValues.Std;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.maxmind.minfraud.exception.*;
import com.maxmind.minfraud.request.InsightsRequest;
import com.maxmind.minfraud.request.ScoreRequest;
import com.maxmind.minfraud.response.InsightsResponse;
import com.maxmind.minfraud.response.ScoreResponse;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;

/**
 * Client for MaxMind minFraud ScoreResponse and InsightsResponse
 */
public class WebServiceClient {
    private static final String pathBase = "/minfraud/v2.0/";

    private final String host;
    private final int port;
    private final boolean useHttps;
    private final List<String> locales;
    private final String licenseKey;
    private final int connectTimeout;
    private final int readTimeout;
    private final int userId;

    private WebServiceClient(WebServiceClient.Builder builder) {
        host = builder.host;
        port = builder.port;
        useHttps = builder.useHttps;
        locales = builder.locales;
        licenseKey = builder.licenseKey;
        connectTimeout = builder.connectTimeout;
        readTimeout = builder.readTimeout;
        userId = builder.userId;
    }

    /**
     * <p>
     * {@code Builder} creates instances of {@code WebServiceClient}
     * from values set by the methods.
     * </p>
     * <p>
     * This example shows how to create a {@code WebServiceClient} object
     * with the {@code Builder}:
     * </p>
     * <p>
     * WebServiceClient client = new
     * WebServiceClient.Builder(12,"licensekey").host
     * ("geoip.maxmind.com").build();
     * </p>
     * <p>
     * Only the values set in the {@code Builder} constructor are required.
     * </p>
     */
    public static final class Builder {
        final int userId;
        final String licenseKey;

        String host = "minfraud.maxmind.com";
        int port = 443;
        boolean useHttps = true;

        int connectTimeout = 3000;
        int readTimeout = 20000;

        List<String> locales = Collections.singletonList("en");

        /**
         * @param userId     Your MaxMind user ID.
         * @param licenseKey Your MaxMind license key.
         */
        public Builder(int userId, String licenseKey) {
            this.userId = userId;
            this.licenseKey = licenseKey;
        }

        /**
         * @param val Timeout in milliseconds to establish a connection to the
         *            web service. The default is 3000 (3 seconds).
         * @return Builder object
         */
        public WebServiceClient.Builder connectTimeout(int val) {
            connectTimeout = val;
            return this;
        }

        /**
         * Disables HTTPS to connect to a test server or proxy. The minFraud ScoreResponse and InsightsResponse web services require
         * HTTPS.
         *
         * @return Builder object
         */
        public WebServiceClient.Builder disableHttps() {
            useHttps = false;
            return this;
        }


        /**
         * @param val The host to use.
         * @return Builder object
         */
        public WebServiceClient.Builder host(String val) {
            host = val;
            return this;
        }

        /**
         * @param val The port to use.
         * @return Builder object
         */
        public WebServiceClient.Builder port(int val) {
            port = val;
            return this;
        }


        /**
         * @param val List of locale codes to use in name property from most
         *            preferred to least preferred.
         * @return Builder object
         */
        public WebServiceClient.Builder locales(List<String> val) {
            locales = new ArrayList<>(val);
            return this;
        }

        /**
         * @param val readTimeout in milliseconds to read data from an
         *            established connection to the web service. The default is
         *            20000 (20 seconds).
         * @return Builder object
         */
        public WebServiceClient.Builder readTimeout(int val) {
            readTimeout = val;
            return this;
        }

        /**
         * @return an instance of {@code WebServiceClient} created from the
         * fields set on this builder.
         */
        public WebServiceClient build() {
            return new WebServiceClient(this);
        }
    }

    private static void handle4xxStatus(HttpResponse response, URL url)
            throws MinFraudException, IOException {

        HttpEntity entity = response.getEntity();
        int status = response.getStatusLine().getStatusCode();

        if (entity.getContentLength() <= 0L) {
            throw new HttpException("Received a " + status + " error for "
                    + url + " with no body", status, url);
        }

        String body = EntityUtils.toString(response.getEntity(), "UTF-8");

        Map<String, String> content;
        try {
            ObjectMapper mapper = new ObjectMapper();
            content = mapper.readValue(body,
                    new TypeReference<HashMap<String, String>>() {
                    });
            WebServiceClient.handleErrorWithJsonBody(content, body, status, url);
        } catch (HttpException e) {
            throw e;
        } catch (IOException e) {
            throw new HttpException("Received a " + status + " error for "
                    + url + " but it did not include the expected JSON body: "
                    + body, status, url);
        }
    }

    private static void handleErrorWithJsonBody(Map<String, String> content,
                                                String body, int status, URL url) throws MinFraudException,
            HttpException {
        String error = content.get("error");
        String code = content.get("code");

        if (error == null || code == null) {
            throw new HttpException(
                    "Response contains JSON but it does not specify code or error keys: "
                            + body, status, url);
        }

        switch (code) {
            case "AUTHORIZATION_INVALID":
            case "LICENSE_KEY_REQUIRED":
            case "USER_ID_REQUIRED":
                throw new AuthenticationException(error);
            case "INSUFFICIENT_CREDITS":
                throw new InsufficientCreditException(error);
            default:
                throw new InvalidRequestException(error, code, url);
        }
    }

    public final InsightsResponse insights(InsightsRequest request) throws IOException, MinFraudException {
        return this.responseFor("insights", request, InsightsResponse.class);
    }

    public final ScoreResponse score(ScoreRequest request) throws IOException, MinFraudException {
        return this.responseFor("score", request, ScoreResponse.class);
    }

    private <T> T responseFor(String service, Object mfRequest, Class<T> cls)
            throws IOException, MinFraudException {
        URL url = createUrl(WebServiceClient.pathBase + service);

        RequestConfig config = RequestConfig.custom()
                .setConnectTimeout(this.connectTimeout)
                .setSocketTimeout(this.readTimeout)
                .build();

        Credentials credentials = new UsernamePasswordCredentials(Integer.toString(userId), licenseKey);

        HttpPost request;
        try {
            request = new HttpPost(url.toURI());
        } catch (URISyntaxException e) {
            throw new MinFraudException("Error parsing request URL", e);
        }
        try {
            request.addHeader(new BasicScheme().authenticate(credentials, request, null));
        } catch (org.apache.http.auth.AuthenticationException e) {
            throw new AuthenticationException("Error setting up request authentication", e);
        }
        request.addHeader("Accept", "application/json");
        request.addHeader("User-Agent", this.userAgent());

        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(Include.NON_NULL);
        mapper.setSerializationInclusion(Include.NON_EMPTY);
        mapper.enable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING);

        String requestBody = mapper.writeValueAsString(mfRequest);

        StringEntity input = new StringEntity(requestBody);
        input.setContentType("application/json");

        request.setEntity(input);

        try (
                CloseableHttpClient httpClient = HttpClientBuilder.create().setDefaultRequestConfig(config).build();
                CloseableHttpResponse response = httpClient.execute(request)) {
            int status = response.getStatusLine().getStatusCode();
            if (status >= 400 && status < 500) {
                WebServiceClient.handle4xxStatus(response, url);
            } else if (status >= 500 && status < 600) {
                throw new HttpException("Received a server error (" + status
                        + ") for " + url, status, url);
            } else if (status != 200) {
                throw new HttpException("Received a very surprising HTTP status ("
                        + status + ") for " + url, status, url);
            }

            HttpEntity entity = response.getEntity();

            if (entity.getContentLength() <= 0L) {
                throw new HttpException("Received a 200 response for " + url
                        + " but there was no message body.", 200, url);
            }

            mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            InjectableValues inject = new Std().addValue(
                    "locales", locales);

            try {
                return mapper.reader(cls).with(inject).readValue(response.getEntity().getContent());
            } catch (IOException e) {
                throw new MinFraudException(
                        "Received a 200 response but not decode it as JSON", e);
            } finally {
                EntityUtils.consume(entity);
            }
        }
    }

    private URL createUrl(String path) throws MinFraudException {
        try {
            return new URIBuilder()
                    .setScheme(this.useHttps ? "https" : "http")
                    .setHost(this.host)
                    .setPort(this.port)
                    .setPath(path)
                    .build().toURL();
        } catch (MalformedURLException | URISyntaxException e) {
            throw new MinFraudException("Error creation service URL", e);
        }
    }

    private String userAgent() {
        return "MaxMind minFraud Client v"
                + getClass().getPackage().getImplementationVersion();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("WebServiceClient{");
        sb.append("host='").append(this.host).append('\'');
        sb.append(", port=").append(this.port);
        sb.append(", useHttps=").append(this.useHttps);
        sb.append(", locales=").append(this.locales);
        sb.append(", licenseKey='").append(this.licenseKey).append('\'');
        sb.append(", connectTimeout=").append(this.connectTimeout);
        sb.append(", readTimeout=").append(this.readTimeout);
        sb.append(", userId=").append(this.userId);
        sb.append('}');
        return sb.toString();
    }
}
