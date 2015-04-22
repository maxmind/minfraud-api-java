package com.maxmind.minfraud;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.maxmind.minfraud.input.*;
import com.maxmind.minfraud.output.Insights;
import org.apache.http.HttpEntity;
import org.apache.http.auth.AuthenticationException;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is used for creating a request to the minFraud web service.
 */
public class MinFraudRequest {
    @JsonProperty
    private final Account account;

    @JsonProperty
    private final Billing billing;

    @JsonProperty("credit_card")
    private final CreditCard creditCard;

    @JsonProperty
    private final Device device;

    @JsonProperty
    private final Email email;

    @JsonProperty
    private final Event event;

    @JsonProperty
    private final Order order;

    @JsonProperty
    private final Payment payment;

    @JsonProperty
    private final Shipping shipping;

    @JsonProperty("shopping_cart")
    private final List<ShoppingCartItem> shoppingCart;

    public MinFraudRequest(Builder builder) {
        this.account = builder.account;
        this.billing = builder.billing;
        this.creditCard = builder.creditCard;
        this.device = builder.device;
        this.email = builder.email;
        this.event = builder.event;
        this.order = builder.order;
        this.payment = builder.payment;
        this.shipping = builder.shipping;
        this.shoppingCart = builder.shoppingCart;
    }

    public final static class Builder {
         Account account;
         Billing billing;
         CreditCard creditCard;
         Device device;
         Email email;
         Event event;
         Order order;
         Payment payment;
         Shipping shipping;
         List<ShoppingCartItem> shoppingCart = new ArrayList<>();

        public Builder() {
        }

        public Builder account(Account val) {
            this.account = val;
            return this;
        }

        public Builder billing(Billing val) {
            this.billing = val;
            return this;
        }

        public Builder creditCard(CreditCard val) {
            this.creditCard = val;
            return this;
        }

        public Builder device(Device val) {
            this.device = val;
            return this;
        }

        public Builder email(Email val) {
            this.email = val;
            return this;
        }

        public Builder event(Event val) {
            this.event = val;
            return this;
        }

        public Builder order(Order val) {
            this.order = val;
            return this;
        }

        public Builder payment(Payment val) {
            this.payment = val;
            return this;
        }

        public Builder shipping(Shipping val) {
            this.shipping = val;
            return this;
        }

        public Builder addShoppingCartItem(ShoppingCartItem val) {
            this.shoppingCart.add(val);
            return this;
        }

        public MinFraudRequest build() {
            return new MinFraudRequest(this);
        }

    }
}
