package com.maxmind.minfraud.request;

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
            () -> new Builder(null).maxmindId("123456789").build()
        );
    }

    @Test
    public void testBuildInvalidIdentifier() {
        assertThrows(
            IllegalArgumentException.class,
            () -> new Builder(tag).build()
        );
    }

    @Test
    public void testBuildValidIdentifier() {
        final var maxmindId = "12345678";
        final var minfraudId = UUID.fromString(
            "58fa38d8-4b87-458b-a22b-f00eda1aa20d");
        final var transactionId = "abc123";


        assertEquals(ip, new TransactionReport.Builder(tag)
            .ipAddress(ip).build().ipAddress());
        assertEquals(maxmindId, new TransactionReport.Builder(tag)
            .maxmindId(maxmindId).build().maxmindId());
        assertEquals(minfraudId, new TransactionReport.Builder(tag)
            .minfraudId(minfraudId).build().minfraudId());
        assertEquals(transactionId, new TransactionReport.Builder(tag)
            .transactionId(transactionId).build().transactionId());
    }

    @Test
    public void testIpAddress() {
        final var report = new Builder(tag).ipAddress(ip).build();
        assertEquals(ip, report.ipAddress());
    }

    @Test
    public void testTag() {
        final var report = new Builder(tag).ipAddress(ip).build();
        assertEquals(Tag.NOT_FRAUD, report.tag());
    }

    @Test
    public void testChargebackCode() {
        final var code = "foo";
        final var report =
            new Builder(tag).ipAddress(ip).chargebackCode(code).build();
        assertEquals(code, report.chargebackCode());
    }

    @Test
    public void testTooLongMaxmindId() {
        assertThrows(
            IllegalArgumentException.class,
            () -> new Builder(tag).maxmindId("123456789").build()
        );
    }

    @Test
    public void testTooShortMaxmindId() {
        assertThrows(
            IllegalArgumentException.class,
            () -> new Builder(tag).maxmindId("1234567").build()
        );
    }

    @Test
    public void testValidMaxmindId() {
        final var id = "12345678";
        final var report = new Builder(tag).maxmindId(id).build();
        assertEquals(id, report.maxmindId());
    }

    @Test
    public void testMinfraudId() {
        final var id = UUID.fromString("58fa38d8-4b87-458b-a22b-f00eda1aa20d");
        final var report = new Builder(tag).minfraudId(id).build();
        assertEquals(id, report.minfraudId());
    }

    @Test
    public void testNotes() {
        final var notes = "foo";
        final var report = new Builder(tag).ipAddress(ip).notes(notes).build();
        assertEquals(notes, report.notes());
    }

    @Test
    public void testTransactionID() {
        final var id = "foo";
        final var report = new Builder(tag).transactionId(id).build();
        assertEquals(id, report.transactionId());
    }

    // Test the example in the README
    @Test
    public void testAllFields() throws Exception {
        final var report = new TransactionReport.Builder(Tag.NOT_FRAUD)
            .chargebackCode("mycode")
            .ipAddress(InetAddress.getByName("1.1.1.1"))
            .maxmindId("12345678")
            .minfraudId(UUID.fromString("58fa38d8-4b87-458b-a22b-f00eda1aa20d"))
            .notes("notes go here")
            .transactionId("foo")
            .build();

        final var expectedJSON = "{" +
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
