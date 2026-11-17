package com.maxmind.minfraud;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class MapperTest {

    static Stream<Arguments> zonedDateTimes() {
        return Stream.of(
            // Offsets with seconds are truncated to whole minutes.
            Arguments.of(
                ZonedDateTime.of(1971, 6, 1, 10, 0, 0, 0, ZoneId.of("Africa/Monrovia")),
                "1971-06-01T10:00:30-00:44"),
            Arguments.of(
                ZonedDateTime.of(2026, 10, 1, 10, 0, 0, 0,
                    ZoneOffset.ofHoursMinutesSeconds(5, 30, 15)),
                "2026-10-01T09:59:45+05:30"),
            Arguments.of(
                ZonedDateTime.of(2026, 10, 1, 10, 0, 0, 0,
                    ZoneOffset.ofHoursMinutesSeconds(-5, -30, -15)),
                "2026-10-01T10:00:15-05:30"),
            Arguments.of(
                ZonedDateTime.of(2026, 1, 1, 0, 0, 0, 0, ZoneOffset.ofTotalSeconds(-59)),
                "2026-01-01T00:00:59Z"),
            // Other values are unchanged.
            Arguments.of(
                ZonedDateTime.of(2026, 10, 1, 10, 30, 0, 0, ZoneId.of("America/New_York")),
                "2026-10-01T10:30:00-04:00"),
            Arguments.of(
                ZonedDateTime.of(2026, 10, 1, 10, 30, 0, 123456789, ZoneId.of("Asia/Kolkata")),
                "2026-10-01T10:30:00.123456789+05:30"),
            Arguments.of(
                ZonedDateTime.parse("2026-10-05T23:20:50.52Z"),
                "2026-10-05T23:20:50.52Z")
        );
    }

    @ParameterizedTest
    @MethodSource("zonedDateTimes")
    public void testZonedDateTimeSerialization(ZonedDateTime time, String expected)
        throws Exception {
        var json = Mapper.get().writeValueAsString(time);
        assertEquals("\"" + expected + "\"", json);
        assertEquals(time.toInstant(), ZonedDateTime.parse(expected).toInstant());
    }
}
