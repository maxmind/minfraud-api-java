package com.maxmind.minfraud;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.maxmind.minfraud.request.Email;
import com.maxmind.minfraud.request.Event;
import com.maxmind.minfraud.request.Payment;
import com.maxmind.minfraud.request.Shipping;
import com.maxmind.minfraud.request.TransactionReport;
import com.maxmind.minfraud.response.EmailDomain;
import com.maxmind.minfraud.response.EmailDomainVisit;
import java.util.List;
import java.util.Locale;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Checks that the default locale does not change serialized values. In the tr-TR locale,
 * {@code "I".toLowerCase()} is a dotless "ı".
 */
public class LocaleTest {
    private Locale originalLocale;
    private Locale originalDisplayLocale;
    private Locale originalFormatLocale;

    @BeforeEach
    public void setTurkishLocale() {
        originalLocale = Locale.getDefault();
        originalDisplayLocale = Locale.getDefault(Locale.Category.DISPLAY);
        originalFormatLocale = Locale.getDefault(Locale.Category.FORMAT);
        Locale.setDefault(Locale.forLanguageTag("tr-TR"));
    }

    @AfterEach
    public void restoreLocale() {
        // Locale.setDefault(Locale) also sets the category defaults, so restore them last.
        Locale.setDefault(originalLocale);
        Locale.setDefault(Locale.Category.DISPLAY, originalDisplayLocale);
        Locale.setDefault(Locale.Category.FORMAT, originalFormatLocale);
    }

    @Test
    public void testEnumToString() {
        var values = List.<Enum<?>[]>of(
            Shipping.DeliverySpeed.values(),
            Payment.Processor.values(),
            Payment.Method.values(),
            TransactionReport.Tag.values(),
            Event.Type.values(),
            Event.Party.values(),
            EmailDomain.Classification.values(),
            EmailDomainVisit.Status.values()
        );
        for (var enumValues : values) {
            for (var value : enumValues) {
                assertEquals(
                    value.name().toLowerCase(Locale.ROOT),
                    value.toString(),
                    value.getClass().getName() + "." + value.name()
                );
            }
        }
    }

    @Test
    public void testEmailAddressLowercasing() {
        var email = new Email.Builder().address("INFO@TEST.org").hashAddress().build();
        assertEquals("fad0cdbc3804b804116ea8972221d2a1", email.address());
    }
}
