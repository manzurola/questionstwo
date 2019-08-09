package com.prodigy.api.answers.review;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.List;

public class Explain {

    private final List<ReviewStep> steps;

    @JsonCreator
    public Explain(List<ReviewStep> steps) {
        this.steps = steps;
    }

    public List<ReviewStep> getSteps() {
        return steps;
    }

}
