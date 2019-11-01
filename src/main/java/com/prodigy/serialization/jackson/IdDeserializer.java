package com.prodigy.common.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.prodigy.domain.Id;

import java.io.IOException;

public class IdDeserializer extends JsonDeserializer<Id> {

    @Override
    public Id deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        return Id.of(jsonParser.getText());
    }
}
