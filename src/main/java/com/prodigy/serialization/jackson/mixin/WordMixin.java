package com.prodigy.serialization.jackson.mixin;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.prodigy.nlp.POS;
import com.prodigy.nlp.Word;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WordMixin extends Word {

    @JsonCreator
    public WordMixin(String value, int index, POS posTag, String originalValue) {
        super(value, index, posTag, originalValue);
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
