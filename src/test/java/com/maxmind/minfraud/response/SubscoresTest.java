package com.maxmind.minfraud.response;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fasterxml.jackson.jr.ob.JSON;
import org.junit.jupiter.api.Test;

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

        assertEquals(Double.valueOf(0.01), subscores.getAvsResult(), "avs_result");
        assertEquals(Double.valueOf(0.02), subscores.getBillingAddress(), "billing_address");
        assertEquals(
            Double.valueOf(0.03),
            subscores.getBillingAddressDistanceToIpLocation(),
            "billing_address_distance_to_ip_location"
        );
        assertEquals(Double.valueOf(0.04), subscores.getBrowser(), "browser");
        assertEquals(Double.valueOf(0.05), subscores.getChargeback(), "chargeback");
        assertEquals(Double.valueOf(0.06), subscores.getCountry(), "country");
        assertEquals(Double.valueOf(0.07), subscores.getCountryMismatch(), "country_mismatch");
        assertEquals(Double.valueOf(0.08), subscores.getCvvResult(), "cvv_result");
        assertEquals(Double.valueOf(0.09), subscores.getEmailAddress(), "email_address");
        assertEquals(Double.valueOf(0.10), subscores.getEmailDomain(), "email_domain");
        assertEquals(Double.valueOf(0.13), subscores.getIssuerIdNumber(), "issuer_id_number");
        assertEquals(Double.valueOf(0.14), subscores.getOrderAmount(), "order_amount");
        assertEquals(Double.valueOf(0.15), subscores.getPhoneNumber(), "phone_number");
        assertEquals(
            Double.valueOf(0.16),
            subscores.getShippingAddressDistanceToIpLocation(),
            "shipping_address_distance_to_ip_location"
        );
        assertEquals(Double.valueOf(0.17), subscores.getTimeOfDay(), "time_of_day");
    }
}