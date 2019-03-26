package com.prodigy.api.review.request;

import com.prodigy.api.answers.Answer;
import com.prodigy.api.common.Id;
import com.prodigy.api.common.service.ServiceRequest;
import com.prodigy.api.review.Comment;
import com.prodigy.api.review.Score;
import com.prodigy.api.users.User;

import java.util.Objects;

public class AddReviewRequest implements ServiceRequest {

    private final Id<Answer> answerId;
    private final Score score;
    private final Comment comment;
    private final Id<User> reviewerId;

    public AddReviewRequest(Id<Answer> answerId, Score score, Comment comment, Id<User> reviewerId) {
        this.answerId = answerId;
        this.score = score;
        this.comment = comment;
        this.reviewerId = reviewerId;
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
        if (!(o instanceof AddReviewRequest)) return false;
        AddReviewRequest that = (AddReviewRequest) o;
        return Objects.equals(answerId, that.answerId) &&
                Objects.equals(score, that.score) &&
                Objects.equals(comment, that.comment) &&
                Objects.equals(reviewerId, that.reviewerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(answerId, score, comment, reviewerId);
    }
}
