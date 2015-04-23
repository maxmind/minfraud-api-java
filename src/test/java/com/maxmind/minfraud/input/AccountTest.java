package com.maxmind.minfraud.input;

import org.junit.Test;

import static org.junit.Assert.*;

public class AccountTest {

    @Test
    public void testUserId() throws Exception {
        Account account = new Account.Builder().userId("usr").build();
        assertEquals("usr", account.getUserId());
    }

    @Test
    public void testUsername() throws Exception {
        Account account = new Account.Builder().username("username").build();
        assertEquals("14c4b06b824ec593239362517f538b29", account.getUsernameMd5());
    }
}