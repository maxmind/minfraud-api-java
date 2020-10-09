package com.maxmind.minfraud.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.maxmind.minfraud.AbstractModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Class representing the transaction to be sent to minFraud.
 */
public final class Transaction extends AbstractModel {
    private final Account account;
    private final Billing billing;
    private final CreditCard creditCard;
    private final CustomInputs customInputs;
    private final Device device;
    private final Email email;
    private final Event event;
    private final Order order;
    private final Payment payment;
    private final Shipping shipping;
    private final List<ShoppingCartItem> shoppingCart;

    private Transaction(Transaction.Builder builder) {
        account = builder.account;
        billing = builder.billing;
        creditCard = builder.creditCard;
        customInputs = builder.customInputs;
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
    public static class Builder {
        Account account;
        Billing billing;
        CreditCard creditCard;
        CustomInputs customInputs;
        Device device;
        Email email;
        Event event;
        Order order;
        Payment payment;
        Shipping shipping;
        final List<ShoppingCartItem> shoppingCart = new ArrayList<>();

        /**
         * Constructor for {@code Builder}
         */
        public Builder() {
        }

        /**
         * Constructor for {@code Builder}
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
         * @param val The CustomInputs object.
         * @return The builder object.
         */
        public Builder customInputs(CustomInputs val) {
            customInputs = val;
            return this;
        }

        /**
         * @param val The Device object.
         * @return The builder object.
         */
        public Builder device(Device val) {
            device = val;
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
    @JsonProperty("account")
    public Account getAccount() {
        return account;
    }

    /**
     * @return The Billing object.
     */
    @JsonProperty("billing")
    public Billing getBilling() {
        return billing;
    }

    /**
     * @return The CreditCard object.
     */
    @JsonProperty("credit_card")
    public CreditCard getCreditCard() {
        return creditCard;
    }

    /**
     * @return The CustomInputs object.
     */
    @JsonProperty("custom_inputs")
    public CustomInputs getCustomInputs() {
        return customInputs;
    }

    /**
     * @return The Device object.
     */
    @JsonProperty("device")
    public Device getDevice() {
        return device;
    }

    /**
     * @return The Email object.
     */
    @JsonProperty("email")
    public Email getEmail() {
        return email;
    }

    /**
     * @return The Event object.
     */
    @JsonProperty("event")
    public Event getEvent() {
        return event;
    }

    /**
     * @return The Order object.
     */
    @JsonProperty("order")
    public Order getOrder() {
        return order;
    }

    /**
     * @return The Payment object.
     */
    @JsonProperty("payment")
    public Payment getPayment() {
        return payment;
    }

    /**
     * @return The Shipping object.
     */
    @JsonProperty("shipping")
    public Shipping getShipping() {
        return shipping;
    }

    /**
     * @return A list of items in the shopping cart.
     */
    @JsonProperty("shopping_cart")
    public List<ShoppingCartItem> getShoppingCart() {
        return new ArrayList<>(shoppingCart);
    }
}
