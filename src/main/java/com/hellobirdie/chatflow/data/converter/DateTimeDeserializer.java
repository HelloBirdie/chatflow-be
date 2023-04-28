package com.hellobirdie.chatflow.data.converter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import io.micrometer.common.util.StringUtils;


import java.io.IOException;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class DateTimeDeserializer extends StdDeserializer<OffsetDateTime> {
    // DataTimeString converting columns, For ObjectMapper dealing with Non-JPA data source deserialization. Use
    // With @JsonDeserialize in your DataModel. Example in Elastic Search
    // Maxwell convert all timestamp into YYYY-MM-DD hh:mm::ss format: https://maxwells-daemon.io/dataformat/#datetime
    // during putting binLog rows to Kinesis stream.
    private static DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(
                    "[yyyy-MM-dd HH:mm:ss.SSSSSS]" +
                    "[yyyy-MM-dd HH:mm:ss.SSSSS]" +
                    "[yyyy-MM-dd HH:mm:ss.SSSS]" +
                    "[yyyy-MM-dd HH:mm:ss.SSS]" +
                    "[yyyy-MM-dd HH:mm:ss.SS]" +
                    "[yyyy-MM-dd HH:mm:ss.S]" +
                    "[yyyy-MM-dd HH:mm:ss]"+
                    "[yyyy-MM-dd'T'HH:mm:ss'Z']" +
                    "[yyyy-MM-dd'T'HH:mm'Z']"
    );

    public DateTimeDeserializer() {
        this(null);
    }

    public DateTimeDeserializer(Class<OffsetDateTime> t) {
        super(t);
    }

    @Override
    public OffsetDateTime deserialize(
            JsonParser jsonparser, DeserializationContext context)
            throws IOException {

        String date = jsonparser.getText();
        LocalDateTime ldt = LocalDateTime.parse(date, DATE_TIME_FORMATTER);
        return OffsetDateTime.of(ldt, ZoneOffset.UTC);
    }

    public static OffsetDateTime convertDateTime(String dateTime) {
        if (StringUtils.isBlank(dateTime)) {
            return null;
        }
        LocalDateTime ldt = LocalDateTime.parse(dateTime, DATE_TIME_FORMATTER);
        return OffsetDateTime.of(ldt, ZoneOffset.UTC);
    }

    public static String convertDateTime(OffsetDateTime offsetDateTime) {
        return offsetDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'"));
    }
}
