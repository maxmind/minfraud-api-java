package com.maxmind.minfraud.request;

import org.junit.Test;

import static org.junit.Assert.*;

public abstract class AbstractRequestTest {
    abstract AbstractRequest.Builder builder();

    @Test
    public void testAccount() throws Exception {
        AbstractRequest request = this.builder().account(new Account.Builder().userId("1").build()).build();
        assertEquals("1", request.getAccount().getUserId());
    }

    @Test
    public void testBilling() throws Exception {
        AbstractRequest request = this.builder().billing(new Billing.Builder().address("add").build()).build();
        assertEquals("add", request.getBilling().getAddress());
    }

    @Test
    public void testCreditCard() throws Exception {
        AbstractRequest request = this.builder().creditCard(new CreditCard.Builder().bankName("name").build()).build();
        assertEquals("name", request.getCreditCard().getBankName());
    }

    @Test
    public void testDevice() throws Exception {
        AbstractRequest request = this.builder().device(new Device.Builder().acceptLanguage("en-US").build()).build();
        assertEquals("en-US", request.getDevice().getAcceptLanguage());
    }

    @Test
    public void testEmail() throws Exception {
        AbstractRequest request = this.builder().email(new Email.Builder().domain("test.com").build()).build();
        assertEquals("test.com", request.getEmail().getDomain());
    }

    @Test
    public void testEvent() throws Exception {
        AbstractRequest request = this.builder().event(new Event.Builder().shopId("1").build()).build();
        assertEquals("1", request.getEvent().getShopId());
    }

    @Test
    public void testOrder() throws Exception {
        AbstractRequest request = this.builder().order(new Order.Builder().affiliateId("af1").build()).build();
        assertEquals("af1", request.getOrder().getAffiliateId());
    }

    @Test
    public void testPayment() throws Exception {
        AbstractRequest request = this.builder().payment(new Payment.Builder().declineCode("d").build()).build();
        assertEquals("d", request.getPayment().getDeclineCode());
    }

    @Test
    public void testShipping() throws Exception {
        AbstractRequest request = this.builder().shipping(new Shipping.Builder().lastName("l").build()).build();
        assertEquals("l", request.getShipping().getLastName());
    }

    @Test
    public void testShoppingCart() throws Exception {
        AbstractRequest request = this.builder().addShoppingCartItem(new ShoppingCartItem.Builder().itemId("1").build()).build();
        assertEquals("1", request.getShoppingCart().get(0).getItemId());
    }
}