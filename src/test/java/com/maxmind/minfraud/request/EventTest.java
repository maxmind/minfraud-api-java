package com.maxmind.minfraud.request;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.maxmind.minfraud.request.Event.Builder;
import com.maxmind.minfraud.request.Event.Party;
import com.maxmind.minfraud.request.Event.Type;
import java.time.ZonedDateTime;
import java.util.Date;
import org.junit.jupiter.api.Test;

public class EventTest {

    @Test
    public void testParty() {
        Event event = new Builder().party(Party.AGENT).build();
        assertEquals(Party.AGENT, event.party());

        event = new Builder().party(Party.CUSTOMER).build();
        assertEquals(Party.CUSTOMER, event.party());
    }

    @Test
    public void testTransactionId() {
        Event event = new Builder().transactionId("t12").build();
        assertEquals("t12", event.transactionId());
    }

    @Test
    public void testShopId() {
        Event event = new Builder().shopId("s12").build();
        assertEquals("s12", event.shopId());
    }

    @Test
    public void testTimeWithDate() {
        Date date = new Date();
        Event event = new Builder().time(date).build();
        assertEquals(date, event.time());
    }

    @Test
    public void testTimeWithZonedDateTime() {
        ZonedDateTime date = ZonedDateTime.now();
        Event event = new Builder().time(date).build();
        assertEquals(date, event.dateTime());
    }

    @Test
    public void testType() {
        Event event = new Builder().type(Type.ACCOUNT_CREATION).build();
        assertEquals(Type.ACCOUNT_CREATION, event.type());

        event = new Builder().type(Type.PAYOUT_CHANGE).build();
        assertEquals(Type.PAYOUT_CHANGE, event.type());

        event = new Builder().type(Type.CREDIT_APPLICATION).build();
        assertEquals(Type.CREDIT_APPLICATION, event.type());

        event = new Builder().type(Type.FUND_TRANSFER).build();
        assertEquals(Type.FUND_TRANSFER, event.type());
    }
}
