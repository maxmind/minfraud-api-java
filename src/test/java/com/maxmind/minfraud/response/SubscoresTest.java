package com.maxmind.minfraud.response;

import com.fasterxml.jackson.jr.ob.JSON;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SubscoresTest extends AbstractOutputTest {

    @Test
    public void testSubscores() throws Exception {
        Subscores subscores = this.deserialize(
                Subscores.class,
                JSON.std
                        .composeString()
                        .startObject()
                        .put("avs_result", 0.01)
                        .put("billing_address", 0.02)
                        .put("billing_address_distance_to_ip_location", 0.03)
                        .put("browser", 0.04)
                        .put("chargeback", 0.05)
                        .put("country", 0.06)
                        .put("country_mismatch", 0.07)
                        .put("cvv_result", 0.08)
                        .put("email_address", 0.09)
                        .put("email_domain", 0.10)
                        .put("email_tenure", 0.11)
                        .put("ip_tenure", 0.12)
                        .put("issuer_id_number", 0.13)
                        .put("order_amount", 0.14)
                        .put("phone_number", 0.15)
                        .put("shipping_address_distance_to_ip_location", 0.16)
                        .put("time_of_day", 0.17).end()
                        .finish()
        );

        assertEquals("avs_result", Double.valueOf(0.01), subscores.getAvsResult());
        assertEquals("billing_address", Double.valueOf(0.02), subscores.getBillingAddress());
        assertEquals("billing_address_distance_to_ip_location", Double.valueOf(0.03), subscores.getBillingAddressDistanceToIpLocation());
        assertEquals("browser", Double.valueOf(0.04), subscores.getBrowser());
        assertEquals("chargeback", Double.valueOf(0.05), subscores.getChargeback());
        assertEquals("country", Double.valueOf(0.06), subscores.getCountry());
        assertEquals("country_mismatch", Double.valueOf(0.07), subscores.getCountryMismatch());
        assertEquals("cvv_result", Double.valueOf(0.08), subscores.getCvvResult());
        assertEquals("email_address", Double.valueOf(0.09), subscores.getEmailAddress());
        assertEquals("email_domain", Double.valueOf(0.10), subscores.getEmailDomain());
        assertEquals("issuer_id_number", Double.valueOf(0.13), subscores.getIssuerIdNumber());
        assertEquals("order_amount", Double.valueOf(0.14), subscores.getOrderAmount());
        assertEquals("phone_number", Double.valueOf(0.15), subscores.getPhoneNumber());
        assertEquals("shipping_address_distance_to_ip_location", Double.valueOf(0.16), subscores.getShippingAddressDistanceToIpLocation());
        assertEquals("time_of_day", Double.valueOf(0.17), subscores.getTimeOfDay());
    }
}