package com.prodigy.api.review.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.prodigy.api.answers.Answer;
import com.prodigy.api.common.Id;
import com.prodigy.api.common.service.ServiceRequest;

import java.util.Objects;

public class GetReviewRequest implements ServiceRequest {

    private Id<Answer> answerId;

    @JsonCreator
    public GetReviewRequest(Id<Answer> answerId) {
        this.answerId = answerId;
    }

    public Id<Answer> getAnswerId() {
        return answerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GetReviewRequest)) return false;
        GetReviewRequest that = (GetReviewRequest) o;
        return Objects.equals(answerId, that.answerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(answerId);
    }
}
