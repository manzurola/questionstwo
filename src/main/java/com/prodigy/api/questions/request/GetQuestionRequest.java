package com.prodigy.api.questions.request;

import com.prodigy.api.common.Id;
import com.prodigy.api.common.service.ServiceRequest;
import com.prodigy.api.questions.Question;

import java.util.Objects;

public class GetQuestionRequest implements ServiceRequest {

    private Id<Question> id;

    public GetQuestionRequest(Id<Question> id) {
        this.id = id;
    }

    public Id<Question> getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GetQuestionRequest)) return false;
        GetQuestionRequest that = (GetQuestionRequest) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}