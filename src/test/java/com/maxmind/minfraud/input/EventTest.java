package com.maxmind.minfraud.input;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class EventTest {

    @Test
    public void testTransactionId() throws Exception {
        Event event = new Event.Builder().transactionId("t12").build();
        assertEquals("t12", event.getTransactionId());
    }

    @Test
    public void testShopId() throws Exception {
        Event event = new Event.Builder().shopId("s12").build();
        assertEquals("s12", event.getShopId());
    }

    @Test
    public void testTime() throws Exception {
        Date date = new Date();
        Event event = new Event.Builder().time(date).build();
        assertEquals(date, event.getTime());
    }

    @Test
    public void testType() throws Exception {
        Event event = new Event.Builder().type(Event.Type.ACCOUNT_CREATION).build();
        assertEquals(Event.Type.ACCOUNT_CREATION, event.getType());
    }
}