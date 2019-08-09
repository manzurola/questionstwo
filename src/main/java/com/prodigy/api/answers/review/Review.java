package com.prodigy.api.answers.review;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(builder = Review.Builder.class)
public class Review {

    private final Score score;
    private final Comment comment;
    private final Explain explain;

    private Review(Builder builder) {
        this.score = builder.score;
        this.comment = builder.comment;
        this.explain = builder.explain;
    }

    public Score getScore() {
        return score;
    }

    public Comment getComment() {
        return comment;
    }

    public Explain getExplain() {
        return explain;
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

        public Review build() {
            return new Review(this);
        }
    }
}
