package com.prodigy.domain.questions;

public class Score {
    private final double percentage;

    public Score(double percentage) {
        this.percentage = percentage;
    }

    public double total() {
        return percentage;
    }
}
