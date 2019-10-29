package com.prodigy.common.jackson;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import com.prodigy.common.Id;
import com.prodigy.common.jackson.mixin.WordMixin;
import com.prodigy.grammar.Word;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;
import static com.fasterxml.jackson.annotation.PropertyAccessor.FIELD;

public class ObjectMapperConfig {

    public static ObjectMapper getObjectMapper() {
        SimpleModule module = new SimpleModule();
        module.addSerializer(Id.class, new IdSerializer());
        module.addDeserializer(Id.class, new IdDeserializer());

        ObjectMapper mapper = new ObjectMapper()
                .setVisibility(FIELD, ANY)
                .enable(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES)
                .registerModule(new ParameterNamesModule(JsonCreator.Mode.PROPERTIES))
                .registerModule(module)
                .addMixIn(Word.class, WordMixin.class);

        return mapper;
    }
}
