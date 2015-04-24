package com.maxmind.minfraud.input;

import com.maxmind.minfraud.input.Account.Builder;
import org.junit.Test;

import static org.junit.Assert.*;

public class AccountTest {

    @Test
    public void testUserId() throws Exception {
        Account account = new Builder().userId("usr").build();
        assertEquals("usr", account.getUserId());
    }

    @Test
    public void testUsername() throws Exception {
        Account account = new Builder().username("username").build();
        assertEquals("14c4b06b824ec593239362517f538b29", account.getUsernameMd5());
    }
}