package com.maxmind.minfraud;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.maxmind.minfraud.input.*;

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

    public MinFraudRequest(MinFraudRequest.Builder builder) {
        account = builder.account;
        billing = builder.billing;
        creditCard = builder.creditCard;
        device = builder.device;
        email = builder.email;
        event = builder.event;
        order = builder.order;
        payment = builder.payment;
        shipping = builder.shipping;
        shoppingCart = builder.shoppingCart;
    }

    public static final class Builder {
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

        public MinFraudRequest.Builder account(Account val) {
            account = val;
            return this;
        }

        public MinFraudRequest.Builder billing(Billing val) {
            billing = val;
            return this;
        }

        public MinFraudRequest.Builder creditCard(CreditCard val) {
            creditCard = val;
            return this;
        }

        public MinFraudRequest.Builder device(Device val) {
            device = val;
            return this;
        }

        public MinFraudRequest.Builder email(Email val) {
            email = val;
            return this;
        }

        public MinFraudRequest.Builder event(Event val) {
            event = val;
            return this;
        }

        public MinFraudRequest.Builder order(Order val) {
            order = val;
            return this;
        }

        public MinFraudRequest.Builder payment(Payment val) {
            payment = val;
            return this;
        }

        public MinFraudRequest.Builder shipping(Shipping val) {
            shipping = val;
            return this;
        }

        public MinFraudRequest.Builder addShoppingCartItem(ShoppingCartItem val) {
            shoppingCart.add(val);
            return this;
        }

        public MinFraudRequest build() {
            return new MinFraudRequest(this);
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder("Builder{");
            sb.append("account=").append(this.account);
            sb.append(", billing=").append(this.billing);
            sb.append(", creditCard=").append(this.creditCard);
            sb.append(", device=").append(this.device);
            sb.append(", email=").append(this.email);
            sb.append(", event=").append(this.event);
            sb.append(", order=").append(this.order);
            sb.append(", payment=").append(this.payment);
            sb.append(", shipping=").append(this.shipping);
            sb.append(", shoppingCart=").append(this.shoppingCart);
            sb.append(", ild=").append(this.build());
            sb.append('}');
            return sb.toString();
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("MinFraudRequest{");
        sb.append("account=").append(this.account);
        sb.append(", billing=").append(this.billing);
        sb.append(", creditCard=").append(this.creditCard);
        sb.append(", device=").append(this.device);
        sb.append(", email=").append(this.email);
        sb.append(", event=").append(this.event);
        sb.append(", order=").append(this.order);
        sb.append(", payment=").append(this.payment);
        sb.append(", shipping=").append(this.shipping);
        sb.append(", shoppingCart=").append(this.shoppingCart);
        sb.append('}');
        return sb.toString();
    }
}
