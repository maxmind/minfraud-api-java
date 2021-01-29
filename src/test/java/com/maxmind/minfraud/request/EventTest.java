package com.maxmind.minfraud.request;

import com.maxmind.minfraud.request.Event.Builder;
import com.maxmind.minfraud.request.Event.Type;
import org.junit.Test;

import java.time.ZonedDateTime;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class EventTest {

    @Test
    public void testTransactionId() {
        Event event = new Builder().transactionId("t12").build();
        assertEquals("t12", event.getTransactionId());
    }

    @Test
    public void testShopId() {
        Event event = new Builder().shopId("s12").build();
        assertEquals("s12", event.getShopId());
    }

    @Test
    public void testTimeWithDate() {
        Date date = new Date();
        Event event = new Builder().time(date).build();
        assertEquals(date, event.getTime());
    }

    @Test
    public void testTimeWithZonedDateTime() {
        ZonedDateTime date = ZonedDateTime.now();
        Event event = new Builder().time(date).build();
        assertEquals(date, event.getDateTime());
    }

    @Test
    public void testType() {
        Event event = new Builder().type(Type.ACCOUNT_CREATION).build();
        assertEquals(Type.ACCOUNT_CREATION, event.getType());

        event = new Builder().type(Type.PAYOUT_CHANGE).build();
        assertEquals(Type.PAYOUT_CHANGE, event.getType());
    }
}
