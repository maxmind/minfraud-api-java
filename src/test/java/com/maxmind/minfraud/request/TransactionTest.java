package com.maxmind.minfraud.request;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.net.InetAddress;
import java.net.UnknownHostException;
import org.junit.jupiter.api.Test;

public class TransactionTest {
    private Transaction.Builder builder() throws UnknownHostException {
        return new Transaction.Builder(
            new Device.Builder(InetAddress.getByName("152.216.7.110")).build());
    }

    @Test
    public void testConstructorWithoutDevice() {
        var request = new Transaction.Builder().build();
        assertNull(request.device());
    }

    @Test
    public void testAccount() throws Exception {
        var request =
            this.builder().account(new Account.Builder().userId("1").build()).build();
        assertEquals("1", request.account().userId());
    }

    @Test
    public void testBilling() throws Exception {
        var request =
            this.builder().billing(new Billing.Builder().address("add").build()).build();
        assertEquals("add", request.billing().address());
    }

    @Test
    public void testCreditCard() throws Exception {
        var request =
            this.builder().creditCard(new CreditCard.Builder().bankName("name").build()).build();
        assertEquals("name", request.creditCard().bankName());
    }

    @Test
    public void testCustomInputs() throws Exception {
        var request = this.builder().customInputs(
            new CustomInputs.Builder().put("key", "value").build()).build();
        assertEquals("value", request.customInputs().inputs().get("key"));
    }

    @Test
    public void testDevice() throws Exception {
        var request = this.builder().build();
        assertEquals(InetAddress.getByName("152.216.7.110"), request.device().ipAddress());
    }

    @Test
    public void testDeviceThroughMethod() throws Exception {
        var ip = InetAddress.getByName("152.216.7.110");

        var device = new Device.Builder().ipAddress(ip).build();

        var request = new Transaction.Builder()
            .device(device)
            .build();

        assertEquals(ip, request.device().ipAddress());
    }

    @Test
    public void testEmail() throws Exception {
        var request =
            this.builder().email(new Email.Builder().domain("test.com").build()).build();
        assertEquals("test.com", request.email().domain());
    }

    @Test
    public void testEvent() throws Exception {
        var request = this.builder().event(new Event.Builder().shopId("1").build()).build();
        assertEquals("1", request.event().shopId());
    }

    @Test
    public void testOrder() throws Exception {
        var request =
            this.builder().order(new Order.Builder().affiliateId("af1").build()).build();
        assertEquals("af1", request.order().affiliateId());
    }

    @Test
    public void testPayment() throws Exception {
        var request =
            this.builder().payment(new Payment.Builder().declineCode("d").build()).build();
        assertEquals("d", request.payment().declineCode());
    }

    @Test
    public void testShipping() throws Exception {
        var request =
            this.builder().shipping(new Shipping.Builder().lastName("l").build()).build();
        assertEquals("l", request.shipping().lastName());
    }

    @Test
    public void testShoppingCart() throws Exception {
        var request =
            this.builder().addShoppingCartItem(new ShoppingCartItem.Builder().itemId("1").build())
                .build();
        assertEquals("1", request.shoppingCart().get(0).itemId());
    }
}
