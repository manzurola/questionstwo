package com.prodigy.api.review.reviewer;

import com.prodigy.nlp.Word;

public class ReviewStep {

    public enum Result {
        EQUAL,
        DELETE,
        INSERT,
        REPLACE
    }

    private final Result result;
    private final Word word;

    public ReviewStep(Result result, Word word) {
        this.result = result;
        this.word = word;
    }
}
