package com.prodigy.domain.review;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.prodigy.grammar.SentenceDiff;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(builder = Review.Builder.class)
public class Review {

    private final double score;
    private final SentenceDiff diff;
    private final boolean isCorrect;

    private Review(Builder builder) {
        this.score = builder.score;
        this.diff = builder.diff;
        this.isCorrect = builder.isCorrect;
    }

    public double score() {
        return score;
    }

    public SentenceDiff diff() {
        return diff;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Review)) return false;
        Review review = (Review) o;
        return Objects.equals(score, review.score);
    }

    @Override
    public int hashCode() {
        return Objects.hash(score);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    @JsonPOJOBuilder(withPrefix = "")
    public static class Builder {
        private double score;
        private SentenceDiff diff;
        private boolean isCorrect;

        public Builder score(double score) {
            this.score = score;
            return this;
        }

        public Builder diff(SentenceDiff diff) {
            this.diff = diff;
            return this;
        }

        public Builder isCorrect(boolean isCorrect) {
            this.isCorrect = isCorrect;
            return this;
        }

        public Review build() {
            return new Review(this);
        }
    }
}
