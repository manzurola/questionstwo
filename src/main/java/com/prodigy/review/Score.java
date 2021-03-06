package com.prodigy.review;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Objects;

public class Score {

    private final int value;

    @JsonCreator
    public Score(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Score)) return false;
        Score score = (Score) o;
        return value == score.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
