package com.prodigy.api.review;

import com.prodigy.api.answers.Answer;
import com.prodigy.api.common.Id;
import com.prodigy.api.users.User;
import com.prodigy.nlp.diff.SentenceDiff;
import com.prodigy.nlp.diff.TextDiff;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

public class Review {

    @NotNull
    private final Id<Review> id;
    @NotNull
    private final Id<Answer> answerId;
    @NotNull
    private final Score score;
    private final Comment comment;
    private final Id<User> reviewerId;
    private final SentenceDiff answerDiff;

    public Review(Id<Review> id, Id<Answer> answerId, Score score, Comment comment, Id<User> reviewerId, SentenceDiff answerDiff) {
        this.id = id;
        this.answerId = answerId;
        this.score = score;
        this.comment = comment;
        this.reviewerId = reviewerId;
        this.answerDiff = answerDiff;
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

    public SentenceDiff getAnswerDiff() {
        return answerDiff;
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
        private Id<Review> id = Id.next();
        private Id<Answer> answerId;
        private Score score;
        private Comment comment;
        private Id<User> reviewerId;
        private SentenceDiff sentenceDiff;

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

        public Builder reviewerId(Id<User> reviewerId) {
            this.reviewerId = reviewerId;
            return this;
        }

        public Builder sentenceDiff(SentenceDiff sentenceDiff) {
            this.sentenceDiff = sentenceDiff;
            return this;
        }

        public Review build() {
            return new Review(id, answerId, score, comment, reviewerId, sentenceDiff);
        }
    }
}
