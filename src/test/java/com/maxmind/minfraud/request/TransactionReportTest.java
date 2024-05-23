package com.maxmind.minfraud.request;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.maxmind.minfraud.request.TransactionReport.Builder;
import com.maxmind.minfraud.request.TransactionReport.Tag;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;

public class TransactionReportTest {

    private final InetAddress ip;
    private final Tag tag;

    public TransactionReportTest() throws UnknownHostException {
        ip = InetAddress.getByName("1.1.1.1");
        tag = Tag.NOT_FRAUD;
    }

    @Test
    public void testInvalidTag() {
        assertThrows(
            IllegalArgumentException.class,
            () -> new Builder(ip, null).maxmindId("123456789").build()
        );
    }

    @Test
    public void testBuildInvalidIdentifier() {
        assertThrows(
            IllegalArgumentException.class,
            () -> new Builder(null, tag).build()
        );
    }

    @Test
    public void testBuildValidIdentifier() {
        final String maxmindId = "12345678";
        final UUID minfraudId = UUID.fromString(
            "58fa38d8-4b87-458b-a22b-f00eda1aa20d");
        final String transactionId = "abc123";

        assertEquals(ip, new TransactionReport.Builder(ip, tag)
            .build().getIpAddress());
        assertEquals(maxmindId, new TransactionReport.Builder(null, tag)
            .maxmindId(maxmindId).build().getMaxmindId());
        assertEquals(minfraudId, new TransactionReport.Builder(null, tag)
            .minfraudId(minfraudId).build().getMinfraudId());
        assertEquals(transactionId, new TransactionReport.Builder(null, tag)
            .transactionId(transactionId).build().getTransactionId());
    }

    @Test
    public void testIpAddress() {
        final TransactionReport report = new Builder(ip, tag).build();
        assertEquals(ip, report.getIpAddress());
    }

    @Test
    public void testTag() {
        final TransactionReport report = new Builder(ip, tag).build();
        assertEquals(Tag.NOT_FRAUD, report.getTag());
    }

    @Test
    public void testChargebackCode() {
        final String code = "foo";
        final TransactionReport report = new Builder(ip, tag).chargebackCode(code).build();
        assertEquals(code, report.getChargebackCode());
    }

    @Test
    public void testTooLongMaxmindId() {
        assertThrows(
            IllegalArgumentException.class,
            () -> new Builder(ip, tag).maxmindId("123456789").build()
        );
    }

    @Test
    public void testTooShortMaxmindId() {
        assertThrows(
            IllegalArgumentException.class,
            () -> new Builder(ip, tag).maxmindId("1234567").build()
        );
    }

    @Test
    public void testValidMaxmindId() {
        final String id = "12345678";
        final TransactionReport report = new Builder(ip, tag).maxmindId(id).build();
        assertEquals(id, report.getMaxmindId());
    }

    @Test
    public void testMinfraudId() {
        final UUID id = UUID.fromString("58fa38d8-4b87-458b-a22b-f00eda1aa20d");
        final TransactionReport report = new Builder(ip, tag).minfraudId(id).build();
        assertEquals(id, report.getMinfraudId());
    }

    @Test
    public void testNotes() {
        final String notes = "foo";
        final TransactionReport report = new Builder(ip, tag).notes(notes).build();
        assertEquals(notes, report.getNotes());
    }

    @Test
    public void testTransactionID() {
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

        final String expectedJSON = "{" +
            "ip_address:'1.1.1.1'," +
            "tag:'not_fraud'," +
            "chargeback_code:'mycode'," +
            "maxmind_id:'12345678'," +
            "minfraud_id:'58fa38d8-4b87-458b-a22b-f00eda1aa20d'," +
            "notes:'notes go here'," +
            "transaction_id:'foo'" +
            "}";

        JSONAssert.assertEquals(expectedJSON, report.toJson(), true);
    }
}
