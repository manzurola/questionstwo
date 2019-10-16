package com.prodigy.domain.common.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.prodigy.domain.common.Id;

import java.io.IOException;

public class IdSerializer extends JsonSerializer<Id> {

    @Override
    public void serialize(Id id, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(id.getId());
    }
}