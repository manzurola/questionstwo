package com.prodigy.common.jackson.mixin;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.prodigy.grammar.POS;
import com.prodigy.grammar.Word;

public interface WordMixin extends Word {

    @Override
    @JsonProperty("value") String value();

    @Override
    @JsonProperty("original") String original();

    @Override
    @JsonProperty("index") int index();

    @Override
    @JsonProperty("posTag") POS posTag();
}
