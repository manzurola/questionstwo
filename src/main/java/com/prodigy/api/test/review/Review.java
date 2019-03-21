package com.prodigy.api.test.review;

import com.prodigy.api.test.answers.Answer;
import com.prodigy.api.test.common.Id;
import com.prodigy.api.test.users.User;

import java.util.Objects;

public class Review {

    private final Id<Review> id;
    private final Id<Answer> answerId;
    private final Score score;
    private final Comment comment;
    private final Id<User> reviewerId;

    public Review(Id<Review> id, Id<Answer> answerId, Score score, Comment comment, Id<User> reviewerId) {
        this.id = id;
        this.answerId = answerId;
        this.score = score;
        this.comment = comment;
        this.reviewerId = reviewerId;
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

    public Id<User> getReviewerId() {
        return reviewerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Review)) return false;
        Review review = (Review) o;
        return Objects.equals(id, review.id) &&
                Objects.equals(answerId, review.answerId) &&
                Objects.equals(score, review.score) &&
                Objects.equals(comment, review.comment) &&
                Objects.equals(reviewerId, review.reviewerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, answerId, score, comment, reviewerId);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Id<Review> id;
        private Id<Answer> answerId;
        private Score score;
        private Comment comment;
        private Id<User> reviewerId;

        public Builder setId(Id<Review> id) {
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

        public Builder reviewerId(Id<User> reviewerId) {
            this.reviewerId = reviewerId;
            return this;
        }

        public Review build() {
            return new Review(id, answerId, score, comment, reviewerId);
        }
    }
}
