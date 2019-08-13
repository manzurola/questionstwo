package com.prodigy.game;

import java.util.Objects;

public class Score {

    private final int total;
    private final int accuracy;
    private final int confidence;
    private final int speed;


    public Score(int total, int accuracy, int confidence, int speed) {
        this.total = total;
        this.accuracy = accuracy;
        this.confidence = confidence;
        this.speed = speed;
    }

    public int total() {
        return total;
    }

    public int accuracy() {
        return accuracy;
    }

    public int confidence() {
        return confidence;
    }

    public int speed() {
        return speed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Score score = (Score) o;
        return total == score.total &&
                accuracy == score.accuracy &&
                confidence == score.confidence &&
                speed == score.speed;
    }

    @Override
    public int hashCode() {
        return Objects.hash(total, accuracy, confidence, speed);
    }
}
