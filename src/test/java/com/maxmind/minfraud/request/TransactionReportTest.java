package com.maxmind.minfraud.request;

import com.maxmind.minfraud.request.TransactionReport.Builder;
import com.maxmind.minfraud.request.TransactionReport.Tag;
import org.junit.Test;

import org.skyscreamer.jsonassert.JSONAssert;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

public class TransactionReportTest {

    private final InetAddress ip;
    private final Tag tag;

    public TransactionReportTest() throws UnknownHostException {
        ip = InetAddress.getByName("1.1.1.1");
        tag = Tag.NOT_FRAUD;
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidIPAddress() throws Exception {
        new Builder(null, tag).maxmindId("123456789").build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidTag() throws Exception {
        new Builder(ip, null).maxmindId("123456789").build();
    }

    @Test
    public void testIpAddress() throws Exception {
        final TransactionReport report = new Builder(ip, tag).build();
        assertEquals(ip, report.getIpAddress());
    }

    @Test
    public void testTag() throws Exception {
        final TransactionReport report = new Builder(ip, tag).build();
        assertEquals(Tag.NOT_FRAUD, report.getTag());
    }

    @Test
    public void testChargebackCode() throws Exception {
        final String code = "foo";
        final TransactionReport report = new Builder(ip, tag).chargebackCode(code).build();
        assertEquals(code, report.getChargebackCode());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTooLongMaxmindId() throws Exception {
        new Builder(ip, tag).maxmindId("123456789").build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTooShortMaxmindId() throws Exception {
        new Builder(ip, tag).maxmindId("1234567").build();
    }

    @Test
    public void testValidMaxmindId() throws Exception {
        final String id = "12345678";
        final TransactionReport report = new Builder(ip, tag).maxmindId(id).build();
        assertEquals(id, report.getMaxmindId());
    }

    @Test
    public void testMinfraudId() throws Exception {
        final UUID id = UUID.fromString("58fa38d8-4b87-458b-a22b-f00eda1aa20d");
        final TransactionReport report = new Builder(ip, tag).minfraudId(id).build();
        assertEquals(id, report.getMinfraudId());
    }

    @Test
    public void testNotes() throws Exception {
        final String notes = "foo";
        final TransactionReport report = new Builder(ip, tag).notes(notes).build();
        assertEquals(notes, report.getNotes());
    }

    @Test
    public void testTransactionID() throws Exception {
        final String id = "foo";
        final TransactionReport report = new Builder(ip, tag).transactionId(id).build();
        assertEquals(id, report.getTransactionId());
    }

    // Test the example in the README
    @Test
    public void testAllFields() throws Exception {
        final TransactionReport report = new TransactionReport.Builder(
            InetAddress.getByName("1.1.1.1"), Tag.NOT_FRAUD
        )
        .chargebackCode("mycode")
        .maxmindId("12345678")
        .minfraudId(UUID.fromString("58fa38d8-4b87-458b-a22b-f00eda1aa20d"))
        .notes("notes go here")
        .transactionId("foo")
        .build();

        final String expectedJSON = new StringBuilder()
        .append("{")
        .append("ip_address:'1.1.1.1',")
        .append("tag:'not_fraud',")
        .append("chargeback_code:'mycode',")
        .append("maxmind_id:'12345678',")
        .append("minfraud_id:'58fa38d8-4b87-458b-a22b-f00eda1aa20d',")
        .append("notes:'notes go here',")
        .append("transaction_id:'foo'")
        .append("}")
        .toString();

        JSONAssert.assertEquals(expectedJSON, report.toJson(), true);
    }
}
