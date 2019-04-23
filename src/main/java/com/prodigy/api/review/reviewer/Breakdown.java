package com.prodigy.api.review.reviewer;

import java.util.List;

public class Breakdown {

    private final String input;
    private final String answer;
    private final List<ReviewStep> steps;

    public Breakdown(String input, String answer, List<ReviewStep> steps) {
        this.input = input;
        this.answer = answer;
        this.steps = steps;
    }

    public List<ReviewStep> getSteps() {
        return steps;
    }

    public String getInput() {
        return input;
    }

    public String getAnswer() {
        return answer;
    }
}
