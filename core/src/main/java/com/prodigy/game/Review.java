package com.prodigy.game;

import javax.validation.constraints.NotNull;
import java.util.Objects;

public class Review {

    @NotNull
    private final Id<Review> id;
    @NotNull
    private final Id<Answer> answerId;
    @NotNull
    private final Score score;
    private final Comment comment;
    private final Explain explain;

    private Review(Builder builder) {
        this.id = builder.id;
        this.answerId = builder.answerId;
        this.score = builder.score;
        this.comment = builder.comment;
        this.explain = builder.explain;
    }

    public Id<Review> getId() {
        return id;
    }

    public Id<Answer> getAnswerId() {
        return answerId;
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
        return Objects.equals(id, review.id) &&
                Objects.equals(answerId, review.answerId) &&
                Objects.equals(score, review.score) &&
                Objects.equals(comment, review.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, answerId, score, comment);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static class Builder {
        private Id<Review> id = Id.next();
        private Id<Answer> answerId;
        private Score score;
        private Comment comment;
        private Explain explain;

        public Builder id(Id<Review> id) {
            this.id = id;
            return this;
        }

        public Builder answerId(Id<Answer> answerId) {
            this.answerId = answerId;
            return this;
        }

        public Builder score(Score score) {
            this.score = score;
            return this;
        }

        public Builder comment(Comment comment) {
            this.comment = comment;
            return this;
        }

        public Builder breakdown(Explain breakdown) {
            this.explain = breakdown;
            return this;
        }

        public Review build() {
            return new Review(this);
        }
    }
}
