package com.maxmind.minfraud.request;

import com.maxmind.minfraud.request.Account.Builder;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AccountTest {

    @Test
    public void testUserId() {
        Account account = new Builder().userId("usr").build();
        assertEquals("usr", account.getUserId());
    }

    @Test
    public void testUsername() {
        Account account = new Builder().username("username").build();
        assertEquals("14c4b06b824ec593239362517f538b29", account.getUsernameMd5());
    }
}