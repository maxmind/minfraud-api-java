package com.maxmind.minfraud.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract class representing a minFraud request.
 *
 * The request classes are separated as in the future, we may wish to offer inputs
 * that are only useful on one service.
 */
abstract class AbstractRequest {
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

    @SuppressWarnings("unchecked")
    public AbstractRequest(AbstractRequest.Builder builder) {
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

    @SuppressWarnings("unchecked")
    public abstract static class Builder<T extends AbstractRequest.Builder>  {
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

        public T account(Account val) {
            account = val;
            return (T) this;
        }

        public T billing(Billing val) {
            billing = val;
            return (T) this;
        }

        public T creditCard(CreditCard val) {
            creditCard = val;
            return (T) this;
        }

        public T device(Device val) {
            device = val;
            return (T) this;
        }

        public T email(Email val) {
            email = val;
            return (T) this;
        }

        public T event(Event val) {
            event = val;
            return (T) this;
        }

        public T order(Order val) {
            order = val;
            return (T) this;
        }

        public T payment(Payment val) {
            payment = val;
            return (T) this;
        }

        public T shipping(Shipping val) {
            shipping = val;
            return (T) this;
        }

        public T addShoppingCartItem(ShoppingCartItem val) {
            shoppingCart.add(val);
            return (T) this;
        }

        abstract AbstractRequest build();
    }

    public Account getAccount() {
        return account;
    }

    public Billing getBilling() {
        return billing;
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }

    public Device getDevice() {
        return device;
    }

    public Email getEmail() {
        return email;
    }

    public Event getEvent() {
        return event;
    }

    public Order getOrder() {
        return order;
    }

    public Payment getPayment() {
        return payment;
    }

    public Shipping getShipping() {
        return shipping;
    }

    public List<ShoppingCartItem> getShoppingCart() {
        return new ArrayList<>(shoppingCart);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("AbstractRequest{");
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
