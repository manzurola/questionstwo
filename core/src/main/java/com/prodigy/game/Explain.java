package com.prodigy.game;

import java.util.List;

public class Explain {

    private final List<ReviewStep> steps;

    public Explain(List<ReviewStep> steps) {
        this.steps = steps;
    }

    public List<ReviewStep> getSteps() {
        return steps;
    }

}
