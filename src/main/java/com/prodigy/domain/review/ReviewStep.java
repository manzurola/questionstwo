package com.prodigy.domain.review;

public class ReviewStep {

    public enum Result {
        EQUAL,
        DELETE,
        INSERT,
        REPLACE
    }

    private final Result result;
    private final String value;

    public ReviewStep(Result result, String value) {
        this.result = result;
        this.value = value;
    }

    public Result getResult() {
        return result;
    }

    public String getValue() {
        return value;
    }
}
