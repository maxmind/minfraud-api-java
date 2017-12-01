package com.maxmind.minfraud;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.InjectableValues;
import com.fasterxml.jackson.databind.InjectableValues.Std;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import com.maxmind.minfraud.exception.*;
import com.maxmind.minfraud.request.Transaction;
import com.maxmind.minfraud.response.FactorsResponse;
import com.maxmind.minfraud.response.InsightsResponse;
import com.maxmind.minfraud.response.ScoreResponse;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
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

import java.io.Closeable;
import java.io.IOException;
import java.net.*;
import java.util.*;

import static org.apache.http.entity.ContentType.APPLICATION_JSON;

/**
 * Client for MaxMind minFraud Score, Insights, and Factors
 */
public final class WebServiceClient implements Closeable {
    private static final String pathBase = "/minfraud/v2.0/";

    private final String host;
    private final int port;
    private final boolean useHttps;
    private final List<String> locales;
    private final String licenseKey;
    private final int userId;


    private final ObjectMapper mapper;
    private final CloseableHttpClient httpClient;

    private WebServiceClient(WebServiceClient.Builder builder) {
        host = builder.host;
        port = builder.port;
        useHttps = builder.useHttps;
        locales = builder.locales;
        licenseKey = builder.licenseKey;
        userId = builder.userId;

        mapper = new ObjectMapper();
        mapper.disable(MapperFeature.CAN_OVERRIDE_ACCESS_MODIFIERS);
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        mapper.setDateFormat(new StdDateFormat().withColonInTimeZone(true));

        RequestConfig.Builder configBuilder = RequestConfig.custom()
                .setConnectTimeout(builder.connectTimeout)
                .setSocketTimeout(builder.readTimeout);

        if (builder.proxy != null && builder.proxy != Proxy.NO_PROXY) {
            InetSocketAddress address = (InetSocketAddress) builder.proxy.address();
            HttpHost proxyHost = new HttpHost(address.getHostName(), address.getPort());
            configBuilder.setProxy(proxyHost);
        }

        RequestConfig config = configBuilder.build();
        httpClient =
                HttpClientBuilder.create()
                        .setUserAgent(userAgent())
                        .setDefaultRequestConfig(config).build();
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

        int connectTimeout = -1;
        int readTimeout = -1;

        List<String> locales = Collections.singletonList("en");
        private Proxy proxy;

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
         *            web service. There is no timeout by default.
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
            if (locales == null) {
                throw new IllegalArgumentException("locales must not be null");
            }
            locales = new ArrayList<>(val);
            return this;
        }

        /**
         * @param val readTimeout in milliseconds to read data from an
         *            established connection to the web service. There is no
         *            timeout by default.
         * @return Builder object
         */
        public WebServiceClient.Builder readTimeout(int val) {
            readTimeout = val;
            return this;
        }

        /**
         * @param val the proxy to use when making this request.
         * @return Builder object
         */
        public Builder proxy(Proxy val) {
            this.proxy = val;
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

    /**
     * Make a minFraud Factors request to the web service using the transaction
     * request object passed to the method.
     *
     * @param transaction A transaction request object.
     * @return An Factors model object
     * @throws InsufficientFundsException  when there are insufficient funds on
     *                                     the account.
     * @throws AuthenticationException     when there is a problem authenticating.
     * @throws InvalidRequestException     when the request is invalid for some
     *                                     other reason.
     * @throws PermissionRequiredException when permission is required to use the
     *                                     service.
     * @throws MinFraudException           when the web service returns unexpected
     *                                     content.
     * @throws HttpException               when the web service returns an unexpected
     *                                     response.
     * @throws IOException                 when some other IO error occurs.
     */
    public FactorsResponse factors(Transaction transaction) throws IOException,
            MinFraudException, InsufficientFundsException, InvalidRequestException,
            AuthenticationException, PermissionRequiredException, HttpException {
        return responseFor("factors", transaction, FactorsResponse.class);
    }

    /**
     * Make a minFraud Insights request to the web service using the transaction
     * request object passed to the method.
     *
     * @param transaction A transaction request object.
     * @return An Insights model object
     * @throws InsufficientFundsException  when there are insufficient funds on
     *                                     the account.
     * @throws AuthenticationException     when there is a problem authenticating.
     * @throws InvalidRequestException     when the request is invalid for some
     *                                     other reason.
     * @throws PermissionRequiredException when permission is required to use the
     *                                     service.
     * @throws MinFraudException           when the web service returns unexpected
     *                                     content.
     * @throws HttpException               when the web service returns an unexpected
     *                                     response.
     * @throws IOException                 when some other IO error occurs.
     */
    public InsightsResponse insights(Transaction transaction) throws IOException,
            MinFraudException, InsufficientFundsException, InvalidRequestException,
            AuthenticationException, PermissionRequiredException, HttpException {
        return responseFor("insights", transaction, InsightsResponse.class);
    }

    /**
     * Make a minFraud Score request to the web service using the transaction
     * request object passed to the method.
     *
     * @param transaction A transaction request object.
     * @return An Score model object
     * @throws InsufficientFundsException  when there are insufficient funds on
     *                                     the account.
     * @throws AuthenticationException     when there is a problem authenticating.
     * @throws InvalidRequestException     when the request is invalid for some
     *                                     other reason.
     * @throws PermissionRequiredException when permission is required to use the
     *                                     service.
     * @throws MinFraudException           when the web service returns unexpected
     *                                     content.
     * @throws HttpException               when the web service returns an unexpected
     *                                     response.
     * @throws IOException                 when some other IO error occurs.
     */
    public ScoreResponse score(Transaction transaction) throws IOException,
            MinFraudException, InsufficientFundsException, InvalidRequestException,
            AuthenticationException, PermissionRequiredException, HttpException {
        return responseFor("score", transaction, ScoreResponse.class);
    }

    private <T> T responseFor(String service, Transaction transaction, Class<T> cls)
            throws IOException, MinFraudException {
        if (transaction == null) {
            throw new IllegalArgumentException("transaction must not be null");
        }
        URL url = createUrl(WebServiceClient.pathBase + service);
        HttpPost request = requestFor(transaction, url);

        try (CloseableHttpResponse response = httpClient.execute(request)) {
            return handleResponse(response, url, cls);
        }
    }

    private HttpPost requestFor(Transaction transaction, URL url)
            throws MinFraudException, IOException {
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

        String requestBody = transaction.toJson();

        StringEntity input = new StringEntity(requestBody, APPLICATION_JSON);

        request.setEntity(input);
        return request;
    }

    private <T> T handleResponse(CloseableHttpResponse response, URL url, Class<T> cls)
            throws MinFraudException, IOException {
        int status = response.getStatusLine().getStatusCode();
        if (status >= 400 && status < 500) {
            this.handle4xxStatus(response, url);
        } else if (status >= 500 && status < 600) {
            throw new HttpException("Received a server error (" + status
                    + ") for " + url, status, url);
        } else if (status != 200) {
            throw new HttpException("Received an unexpected HTTP status ("
                    + status + ") for " + url, status, url);
        }

        HttpEntity entity = response.getEntity();

        if (entity.getContentLength() <= 0L) {
            throw new HttpException("Received a 200 response for " + url
                    + " but there was no message body.", 200, url);
        }

        InjectableValues inject = new Std().addValue(
                "locales", locales);

        try {
            return mapper.readerFor(cls).with(inject).readValue(entity.getContent());
        } catch (IOException e) {
            throw new MinFraudException(
                    "Received a 200 response but could not decode it as JSON", e);
        } finally {
            EntityUtils.consume(entity);
        }
    }


    private void handle4xxStatus(HttpResponse response, URL url)
            throws IOException, InsufficientFundsException,
            InvalidRequestException, AuthenticationException,
            PermissionRequiredException {
        HttpEntity entity = response.getEntity();
        int status = response.getStatusLine().getStatusCode();

        if (entity.getContentLength() <= 0L) {
            throw new HttpException("Received a " + status + " error for "
                    + url + " with no body", status, url);
        }

        String body = EntityUtils.toString(entity, "UTF-8");

        Map<String, String> content;
        try {
            content = mapper.readValue(body,
                    new TypeReference<HashMap<String, String>>() {
                    });
            this.handleErrorWithJsonBody(content, body, status, url);
        } catch (HttpException e) {
            throw e;
        } catch (IOException e) {
            throw new HttpException("Received a " + status + " error for "
                    + url + " but it did not include the expected JSON body: "
                    + body, status, url, e);
        }
    }

    private void handleErrorWithJsonBody(Map<String, String> content,
                                         String body, int status, URL url)
            throws HttpException, InsufficientFundsException,
            InvalidRequestException, AuthenticationException,
            PermissionRequiredException {
        String error = content.get("error");
        String code = content.get("code");

        if (error == null || code == null) {
            throw new HttpException(
                    "Error response contains JSON but it does not specify code or error keys: "
                            + body, status, url);
        }

        switch (code) {
            case "AUTHORIZATION_INVALID":
            case "LICENSE_KEY_REQUIRED":
            case "USER_ID_REQUIRED":
                throw new AuthenticationException(error);
            case "INSUFFICIENT_FUNDS":
                throw new InsufficientFundsException(error);
            case "PERMISSION_REQUIRED":
                throw new PermissionRequiredException(error);
            default:
                throw new InvalidRequestException(error, code, status, url, null);
        }
    }

    private URL createUrl(String path) throws MinFraudException {
        try {
            return new URIBuilder()
                    .setScheme(useHttps ? "https" : "http")
                    .setHost(this.host)
                    .setPort(this.port)
                    .setPath(path)
                    .build().toURL();
        } catch (MalformedURLException | URISyntaxException e) {
            throw new MinFraudException("Error creating service URL", e);
        }
    }

    private String userAgent() {
        return "minFraud-API/"
                + getClass().getPackage().getImplementationVersion()
                + " Java/" + System.getProperty("java.version");
    }


    /**
     * Close any open connections and return resources to the system.
     */
    @Override
    public void close() throws IOException {
        httpClient.close();
    }

    @Override
    public String toString() {
        return "WebServiceClient{" +
                "host='" + host + '\'' +
                ", port=" + port +
                ", useHttps=" + useHttps +
                ", locales=" + locales +
                ", licenseKey='" + licenseKey + '\'' +
                ", userId=" + userId +
                ", mapper=" + mapper +
                ", httpClient=" + httpClient +
                '}';
    }
}
