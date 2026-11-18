package com.maxmind.minfraud;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.IOException;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

class Mapper {
    private static final ObjectMapper mapper = JsonMapper.builder()
        .addModule(new JavaTimeModule())
        // Added after JavaTimeModule so that it replaces its ZonedDateTime serializer.
        .addModule(new SimpleModule()
            .addSerializer(ZonedDateTime.class, new WholeMinuteOffsetSerializer()))
        .defaultDateFormat(new StdDateFormat().withColonInTimeZone(true))
        .enable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING)
        .enable(DeserializationFeature.READ_ENUMS_USING_TO_STRING)
        .enable(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL)
        .disable(MapperFeature.CAN_OVERRIDE_ACCESS_MODIFIERS)
        .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
        .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
        .serializationInclusion(JsonInclude.Include.NON_NULL)
        .serializationInclusion(JsonInclude.Include.NON_EMPTY)
        .build();

    public static ObjectMapper get() {
        return mapper;
    }

    /**
     * Writes a {@code ZonedDateTime} with {@code ISO_OFFSET_DATE_TIME}. RFC 3339 offsets have no
     * seconds, so an offset with seconds is rounded to the nearest minute, as in the last
     * example in RFC 3339 section 5.8. The instant does not change.
     */
    private static final class WholeMinuteOffsetSerializer
        extends StdSerializer<ZonedDateTime> {
        WholeMinuteOffsetSerializer() {
            super(ZonedDateTime.class);
        }

        @Override
        public void serialize(ZonedDateTime value, JsonGenerator gen, SerializerProvider provider)
            throws IOException {
            int offsetSeconds = value.getOffset().getTotalSeconds();
            if (offsetSeconds % 60 != 0) {
                var minutes = (int) Math.round(offsetSeconds / 60.0);
                var offset = ZoneOffset.ofTotalSeconds(minutes * 60);
                value = value.withZoneSameInstant(offset);
            }
            gen.writeString(DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(value));
        }
    }
}
