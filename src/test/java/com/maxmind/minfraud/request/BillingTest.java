package com.maxmind.minfraud.request;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import com.maxmind.minfraud.request.Billing.Builder;
import com.maxmind.minfraud.request.Billing.PhoneVerificationMethod;
import java.time.ZonedDateTime;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;

public class BillingTest extends AbstractLocationTest {

    Builder builder() {
        return new Builder();
    }

    @Test
    public void testPhoneVerificationMethod() {
        var loc = this.builder()
            .phoneVerificationMethod(PhoneVerificationMethod.DELIVERED_CODE)
            .build();
        assertEquals(PhoneVerificationMethod.DELIVERED_CODE, loc.phoneVerificationMethod());

        loc = this.builder().phoneVerificationMethod(PhoneVerificationMethod.NETWORK).build();
        assertEquals(PhoneVerificationMethod.NETWORK, loc.phoneVerificationMethod());

        loc = this.builder().phoneVerificationMethod(PhoneVerificationMethod.OTHER).build();
        assertEquals(PhoneVerificationMethod.OTHER, loc.phoneVerificationMethod());
    }

    @Test
    public void testPhoneVerificationMethodToString() {
        assertEquals("delivered_code", PhoneVerificationMethod.DELIVERED_CODE.toString());
        assertEquals("network", PhoneVerificationMethod.NETWORK.toString());
        assertEquals("other", PhoneVerificationMethod.OTHER.toString());
    }

    @Test
    public void testPhoneWasVerificationSuccessful() {
        var loc = this.builder().phoneWasVerificationSuccessful(false).build();
        assertFalse(loc.phoneWasVerificationSuccessful());
    }

    @Test
    public void testPhoneVerificationTime() {
        var time = ZonedDateTime.now();
        var loc = this.builder().phoneVerificationTime(time).build();
        assertEquals(time, loc.phoneVerificationTime());
    }

    @Test
    public void testPhoneVerificationSerialization() throws Exception {
        var loc = this.builder()
            .phoneVerificationMethod(PhoneVerificationMethod.NETWORK)
            .phoneWasVerificationSuccessful(false)
            .phoneVerificationTime(ZonedDateTime.parse("2026-10-01T14:30:00Z"))
            .build();

        var expectedJSON = "{phone_verification_method:'network',"
            + "phone_was_verification_successful:false,"
            + "phone_verification_time:'2026-10-01T14:30:00Z'}";
        JSONAssert.assertEquals(expectedJSON, loc.toJson(), true);
    }
}
