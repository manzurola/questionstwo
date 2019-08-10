package com.prodigy.api.answers.review;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.prodigy.core.Word;
import com.prodigy.core.diff.Diff;

import java.util.List;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(builder = Review.Builder.class)
public class Review {

    private final Score score;
    private final Comment comment;
    private final Explain explain;
    private final List<Diff<Word>> diff;
    private final boolean isCorrect;

    private Review(Builder builder) {
        this.score = builder.score;
        this.comment = builder.comment;
        this.explain = builder.explain;
        this.diff = builder.diff;
        this.isCorrect = builder.isCorrect;
    }

    public Score score() {
        return score;
    }

    public Comment comment() {
        return comment;
    }

    public Explain explain() {
        return explain;
    }

    public List<Diff<Word>> diff() {
        return diff;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Review)) return false;
        Review review = (Review) o;
        return Objects.equals(score, review.score) &&
                Objects.equals(comment, review.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(score, comment);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    @JsonPOJOBuilder(withPrefix = "")
    public static class Builder {
        private Score score;
        private Comment comment;
        private Explain explain;
        private List<Diff<Word>> diff;
        private boolean isCorrect;

        public Builder score(Score score) {
            this.score = score;
            return this;
        }

        public Builder comment(Comment comment) {
            this.comment = comment;
            return this;
        }

        public Builder explain(Explain breakdown) {
            this.explain = breakdown;
            return this;
        }

        public Builder diff(List<Diff<Word>> diff) {
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
