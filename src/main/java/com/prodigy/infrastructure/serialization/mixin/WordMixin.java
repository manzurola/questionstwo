package com.prodigy.infrastructure.serialization.mixin;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.prodigy.domain.nlp.POS;
import com.prodigy.domain.nlp.Token;
import com.prodigy.domain.nlp.Word;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WordMixin extends Word {

    @JsonCreator
    public WordMixin(Token token, POS posTag) {
        super(token, posTag);
    }

    @Override
    @JsonProperty
    public String value() {
        return value();
    }

    @Override
    @JsonProperty
    public String original() {
        return original();
    }

    @Override
    @JsonProperty
    public int index() {
        return index();
    }

    @Override
    @JsonProperty
    public POS posTag() {
        return posTag();
    }
}
