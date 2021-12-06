package com.maxmind.minfraud;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.InjectableValues;
import com.fasterxml.jackson.databind.InjectableValues.Std;
import com.maxmind.minfraud.exception.*;
import com.maxmind.minfraud.request.Transaction;
import com.maxmind.minfraud.request.TransactionReport;
import com.maxmind.minfraud.response.FactorsResponse;
import com.maxmind.minfraud.response.InsightsResponse;
import com.maxmind.minfraud.response.ScoreResponse;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.*;

/**
 * Client for MaxMind minFraud Score, Insights, and Factors
 */
public final class WebServiceClient implements Closeable {
    private static final String pathBase = "/minfraud/v2.0/";
    private static final String userAgent = "minFraud-API/"
            + WebServiceClient.class.getPackage().getImplementationVersion()
            + " Java/" + System.getProperty("java.version");

    private final String authHeader;
    private final String host;
    private final int port;
    private final boolean useHttps;
    private final List<String> locales;
    private final Duration requestTimeout;

    private final HttpClient httpClient;

    private WebServiceClient(WebServiceClient.Builder builder) {
        host = builder.host;
        port = builder.port;
        useHttps = builder.useHttps;
        locales = builder.locales;

        // HttpClient supports basic auth, but it will only send it after the
        // server responds with an unauthorized. As such, we just make the
        // Authorization header ourselves.
        authHeader = "Basic " +
                Base64.getEncoder()
                        .encodeToString((builder.accountId + ":" + builder.licenseKey)
                                .getBytes(StandardCharsets.UTF_8));

        requestTimeout = builder.requestTimeout;
        HttpClient.Builder httpClientBuilder = HttpClient.newBuilder()
                .proxy(builder.proxy);
        if (builder.connectTimeout != null) {
            httpClientBuilder.connectTimeout(builder.connectTimeout);
        }
        httpClient = httpClientBuilder.build();

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
        final int accountId;
        final String licenseKey;


        String host = "minfraud.maxmind.com";
        int port = 443;
        boolean useHttps = true;

        Duration connectTimeout;
        Duration requestTimeout;

        List<String> locales = Collections.singletonList("en");
        private ProxySelector proxy = ProxySelector.getDefault();

        /**
         * @param accountId  Your MaxMind account ID.
         * @param licenseKey Your MaxMind license key.
         */
        public Builder(int accountId, String licenseKey) {
            this.accountId = accountId;
            this.licenseKey = licenseKey;
        }

        /**
         * @param val Timeout in milliseconds to establish a connection to the
         *            web service. There is no timeout by default.
         * @return Builder object
         * @deprecated Use connectTimeout(Duration) instead
         */
        @Deprecated
        public WebServiceClient.Builder connectTimeout(int val) {
            return connectTimeout(Duration.ofMillis(val));
        }

        /**
         * @param val Timeout duration to establish a connection to the web
         *            service. There is no timeout by default.
         * @return Builder object
         */
        public WebServiceClient.Builder connectTimeout(Duration val) {
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
         * @deprecated Use requestTimeout(Duration) instead
         */
        @Deprecated
        public WebServiceClient.Builder readTimeout(int val) {
            return requestTimeout(Duration.ofMillis(val));
        }

        /**
         * @param val Request timeout duration. here is no timeout by default.
         * @return Builder object
         */
        public Builder requestTimeout(Duration val) {
            requestTimeout = val;
            return this;
        }

        /**
         * @param val the proxy to use when making this request.
         * @return Builder object
         * @deprecated Use proxy(ProxySelector)
         */
        @Deprecated
        public Builder proxy(Proxy val) {
            if (val != null && val != Proxy.NO_PROXY) {
                proxy = ProxySelector.of((InetSocketAddress) val.address());
            }
            return this;
        }

        /**
         * @param val the proxy to use when making this request.
         * @return Builder object
         */
        public Builder proxy(ProxySelector val) {
            proxy = val;
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

    /**
     * Make a Report Transaction request to the web service using the TransactionReport
     * request object passed to the method.
     *
     * @param transaction A TransactionReport request object.
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
    public void reportTransaction(TransactionReport transaction) throws IOException,
            MinFraudException, InsufficientFundsException, InvalidRequestException,
            AuthenticationException, PermissionRequiredException, HttpException {
        if (transaction == null) {
            throw new IllegalArgumentException("transaction report must not be null");
        }
        URI uri = createUri(WebServiceClient.pathBase + "transactions/report");
        HttpRequest request = requestFor(transaction, uri);

        HttpResponse<InputStream> response = null;
        try {
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofInputStream());
            maybeThrowException(response, uri);
            exhaustBody(response);
        } catch (InterruptedException e) {
            throw new MinFraudException("Interrupted sending request", e);
        } finally {
            if (response != null) {
                response.body().close();
            }
        }
    }

    private <T> T responseFor(String service, AbstractModel transaction, Class<T> cls)
            throws IOException, MinFraudException {
        if (transaction == null) {
            throw new IllegalArgumentException("transaction must not be null");
        }
        URI uri = createUri(WebServiceClient.pathBase + service);
        HttpRequest request = requestFor(transaction, uri);

        HttpResponse<InputStream> response = null;
        try {
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofInputStream());
            return handleResponse(response, uri, cls);
        } catch (InterruptedException e) {
            throw new MinFraudException("Interrupted sending request", e);
        } finally {
            if (response != null) {
                response.body().close();
            }
        }
    }

    private HttpRequest requestFor(AbstractModel transaction, URI uri)
            throws MinFraudException, IOException {
        HttpRequest.Builder builder = HttpRequest.newBuilder()
                .uri(uri)
                .header("Accept", "application/json")
                .header("Authorization", authHeader)
                .header("Content-Type", "application/json; charset=UTF-8")
                .header("User-Agent", userAgent)
                // XXX - creating this JSON string is somewhat wasteful. We
                // could use an input stream instead.
                .POST(HttpRequest.BodyPublishers.ofString(transaction.toJson()));

        if (requestTimeout != null) {
            builder.timeout(requestTimeout);
        }

        return builder.build();
    }

    private void maybeThrowException(HttpResponse<InputStream> response, URI uri) throws IOException, MinFraudException {
        int status = response.statusCode();
        if (status >= 400 && status < 500) {
            this.handle4xxStatus(response, uri);
        } else if (status >= 500 && status < 600) {
            exhaustBody(response);
            throw new HttpException("Received a server error (" + status
                    + ") for " + uri, status, uri);
        } else if (status != 200 && status != 204) {
            exhaustBody(response);
            throw new HttpException("Received an unexpected HTTP status ("
                    + status + ") for " + uri, status, uri);
        }
    }

    private <T> T handleResponse(HttpResponse<InputStream> response, URI uri, Class<T> cls)
            throws MinFraudException, IOException {
        maybeThrowException(response, uri);

        InjectableValues inject = new Std().addValue(
                "locales", locales);

        try (InputStream stream = response.body()) {
            return Mapper.get().readerFor(cls).with(inject).readValue(stream);
        } catch (IOException e) {
            throw new MinFraudException(
                    "Received a 200 response but could not decode it as JSON", e);
        }
    }

    private void handle4xxStatus(HttpResponse<InputStream> response, URI uri)
            throws IOException, InsufficientFundsException,
            InvalidRequestException, AuthenticationException,
            PermissionRequiredException {
        int status = response.statusCode();

        String body;
        try (InputStream stream = response.body()) {
            body = new String(stream.readAllBytes(), StandardCharsets.UTF_8);
        }

        Map<String, String> content;
        try {
            content = Mapper.get().readValue(body,
                    new TypeReference<HashMap<String, String>>() {
                    });
            this.handleErrorWithJsonBody(content, body, status, uri);
        } catch (HttpException e) {
            throw e;
        } catch (IOException e) {
            throw new HttpException("Received a " + status + " error for "
                    + uri + " but it did not include the expected JSON body: "
                    + body, status, uri, e);
        }
    }

    private void handleErrorWithJsonBody(Map<String, String> content,
                                         String body, int status, URI uri)
            throws HttpException, InsufficientFundsException,
            InvalidRequestException, AuthenticationException,
            PermissionRequiredException {
        String error = content.get("error");
        String code = content.get("code");

        if (error == null || code == null) {
            throw new HttpException(
                    "Error response contains JSON but it does not specify code or error keys: "
                            + body, status, uri);
        }

        switch (code) {
            case "ACCOUNT_ID_REQUIRED":
            case "AUTHORIZATION_INVALID":
            case "LICENSE_KEY_REQUIRED":
            case "USER_ID_REQUIRED":
                throw new AuthenticationException(error);
            case "INSUFFICIENT_FUNDS":
                throw new InsufficientFundsException(error);
            case "PERMISSION_REQUIRED":
                throw new PermissionRequiredException(error);
            default:
                throw new InvalidRequestException(error, code, status, uri, null);
        }
    }

    private URI createUri(String path) throws MinFraudException {
        try {
            return new URI(
                    useHttps ? "https" : "http",
                    null,
                    host,
                    port,
                    path,
                    null,
                    null
            );

        } catch (URISyntaxException e) {
            throw new MinFraudException("Error creating service URL", e);
        }
    }

    private void exhaustBody(HttpResponse<InputStream> response) throws HttpException {
        InputStream body = response.body();

        try {
            // Make sure we read the stream until the end so that
            // the connection can be reused.
            while (body.read() != -1) {
            }
        } catch (IOException e) {
            throw new HttpException("Error reading response body", response.statusCode(), response.uri(), e);
        }
    }

    /**
     * @deprecated Closing is no longer necessary with java.net.http.HttpClient.
     */
    @Override
    @Deprecated
    public void close() throws IOException {
    }

    @Override
    public String toString() {
        return "WebServiceClient{" +
                "host='" + host + '\'' +
                ", port=" + port +
                ", useHttps=" + useHttps +
                ", locales=" + locales +
                ", httpClient=" + httpClient +
                '}';
    }
}
