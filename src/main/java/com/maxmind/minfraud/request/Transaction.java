package com.maxmind.minfraud.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class representing the transaction to be sent to minFraud.
 */
public final class Transaction {
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
    protected Transaction(Transaction.Builder builder) {
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

    /**
     * {@code Builder} creates instances of the parent class from values set
     * by the builder's methods.
     */
    @SuppressWarnings("unchecked")
    public static class Builder {
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

        /**
         * The constructor for {@code Builder}
         *
         * @param device The {@code Device} model for the request
         */
        public Builder(Device device) {
            this.device = device;
        }

        /**
         * @param val The Account object.
         * @return The builder object.
         */
        public Builder account(Account val) {
            account = val;
            return this;
        }

        /**
         * @param val The Billing object.
         * @return The builder object.
         */
        public Builder billing(Billing val) {
            billing = val;
            return this;
        }

        /**
         * @param val The CreditCard object.
         * @return The builder object.
         */
        public Builder creditCard(CreditCard val) {
            creditCard = val;
            return this;
        }

        /**
         * @param val The Email object.
         * @return The builder object.
         */
        public Builder email(Email val) {
            email = val;
            return this;
        }

        /**
         * @param val The Event object.
         * @return The builder object.
         */
        public Builder event(Event val) {
            event = val;
            return this;
        }

        /**
         * @param val The Order object.
         * @return The builder object.
         */
        public Builder order(Order val) {
            order = val;
            return this;
        }

        /**
         * @param val The Payment object.
         * @return The builder object.
         */
        public Builder payment(Payment val) {
            payment = val;
            return this;
        }

        /**
         * @param val The Shipping object.
         * @return The builder object.
         */
        public Builder shipping(Shipping val) {
            shipping = val;
            return this;
        }

        /**
         * Add a {@code ShoppingCartItem} to the shopping cart.
         *
         * @param val A ShoppingCartItem object.
         * @return The builder object.
         */
        public Builder addShoppingCartItem(ShoppingCartItem val) {
            shoppingCart.add(val);
            return this;
        }

        /**
         * @return An instance of {@code Transaction} created from the
         * fields set on this builder.
         */
        public Transaction build() {
            return new Transaction(this);
        }
    }

    /**
     * @return The Account object.
     */
    public Account getAccount() {
        return account;
    }

    /**
     * @return The Billing object.
     */
    public Billing getBilling() {
        return billing;
    }

    /**
     * @return The CreditCard object.
     */
    public CreditCard getCreditCard() {
        return creditCard;
    }

    /**
     * @return The Device object.
     */
    public Device getDevice() {
        return device;
    }

    /**
     * @return The Email object.
     */
    public Email getEmail() {
        return email;
    }

    /**
     * @return The Event object.
     */
    public Event getEvent() {
        return event;
    }

    /**
     * @return The Order object.
     */
    public Order getOrder() {
        return order;
    }

    /**
     * @return The Payment object.
     */
    public Payment getPayment() {
        return payment;
    }

    /**
     * @return The Shipping object.
     */
    public Shipping getShipping() {
        return shipping;
    }

    /**
     * @return A list of items in the shopping cart.
     */
    public List<ShoppingCartItem> getShoppingCart() {
        return new ArrayList<>(shoppingCart);
    }

    /**
     * @return The transaction as JSON.
     * @throws IOException when there is an issue encoding as JSON.
     */
    public String toJson() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        mapper.enable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING);

        return mapper.writeValueAsString(this);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Transaction{");
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
