package com.prodigy.infrastructure.serialization;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import com.prodigy.domain.Answer;
import com.prodigy.domain.Id;
import com.prodigy.domain.Question;
import com.prodigy.infrastructure.serialization.mixin.AnswerMixin;
import com.prodigy.infrastructure.serialization.mixin.QuestionMixin;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;
import static com.fasterxml.jackson.annotation.PropertyAccessor.FIELD;

public class ObjectMapperFactory {

    public static ObjectMapper defaultObjectMapper() {
        SimpleModule module = new SimpleModule();
        module.addSerializer(Id.class, new IdSerializer());
        module.addDeserializer(Id.class, new IdDeserializer());

        return new ObjectMapper()
                .setVisibility(FIELD, ANY)
                .enable(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES)
                .registerModule(new ParameterNamesModule(JsonCreator.Mode.PROPERTIES))
                .registerModule(module)
                .addMixIn(Answer.class, AnswerMixin.class)
                .addMixIn(Question.class, QuestionMixin.class);
    }
}
