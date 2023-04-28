package com.hellobirdie.chatflow.data.converter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.time.OffsetDateTime;

public class DateTimeSerializer extends StdSerializer<OffsetDateTime> {

    public DateTimeSerializer() {
        this(null);
    }

    public DateTimeSerializer(Class<OffsetDateTime> t) {
        super(t);
    }

    @Override
    public void serialize(OffsetDateTime offsetDateTime, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(DateTimeDeserializer.convertDateTime(offsetDateTime));
    }

}
