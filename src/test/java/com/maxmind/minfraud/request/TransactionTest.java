package com.maxmind.minfraud.request;

import org.junit.Test;

import java.net.InetAddress;
import java.net.UnknownHostException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class TransactionTest {
    private Transaction.Builder builder() throws UnknownHostException {
        return new Transaction.Builder(new Device.Builder(InetAddress.getByName("152.216.7.110")).build());
    }

    @Test
    public void testConstructorWithoutDevice() {
        Transaction request = new Transaction.Builder().build();
        assertNull(request.getDevice());
    }

    @Test
    public void testAccount() throws Exception {
        Transaction request = this.builder().account(new Account.Builder().userId("1").build()).build();
        assertEquals("1", request.getAccount().getUserId());
    }

    @Test
    public void testBilling() throws Exception {
        Transaction request = this.builder().billing(new Billing.Builder().address("add").build()).build();
        assertEquals("add", request.getBilling().getAddress());
    }

    @Test
    public void testCreditCard() throws Exception {
        Transaction request = this.builder().creditCard(new CreditCard.Builder().bankName("name").build()).build();
        assertEquals("name", request.getCreditCard().getBankName());
    }

    @Test
    public void testCustomInputs() throws Exception {
        Transaction request = this.builder().customInputs(
                new CustomInputs.Builder().put("key", "value").build()).build();
        assertEquals("value", request.getCustomInputs().getInputs().get("key"));
    }

    @Test
    public void testDevice() throws Exception {
        Transaction request = this.builder().build();
        assertEquals(InetAddress.getByName("152.216.7.110"), request.getDevice().getIpAddress());
    }

    @Test
    public void testDeviceThroughMethod() throws Exception {
        InetAddress ip = InetAddress.getByName("152.216.7.110");

        Device device = new Device.Builder().ipAddress(ip).build();

        Transaction request = new Transaction.Builder()
                .device(device)
                .build();

        assertEquals(ip, request.getDevice().getIpAddress());
    }

    @Test
    public void testEmail() throws Exception {
        Transaction request = this.builder().email(new Email.Builder().domain("test.com").build()).build();
        assertEquals("test.com", request.getEmail().getDomain());
    }

    @Test
    public void testEvent() throws Exception {
        Transaction request = this.builder().event(new Event.Builder().shopId("1").build()).build();
        assertEquals("1", request.getEvent().getShopId());
    }

    @Test
    public void testOrder() throws Exception {
        Transaction request = this.builder().order(new Order.Builder().affiliateId("af1").build()).build();
        assertEquals("af1", request.getOrder().getAffiliateId());
    }

    @Test
    public void testPayment() throws Exception {
        Transaction request = this.builder().payment(new Payment.Builder().declineCode("d").build()).build();
        assertEquals("d", request.getPayment().getDeclineCode());
    }

    @Test
    public void testShipping() throws Exception {
        Transaction request = this.builder().shipping(new Shipping.Builder().lastName("l").build()).build();
        assertEquals("l", request.getShipping().getLastName());
    }

    @Test
    public void testShoppingCart() throws Exception {
        Transaction request = this.builder().addShoppingCartItem(new ShoppingCartItem.Builder().itemId("1").build()).build();
        assertEquals("1", request.getShoppingCart().get(0).getItemId());
    }
}
