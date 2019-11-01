package com.prodigy.domain.questions.service;

import com.prodigy.domain.Id;
import com.prodigy.service.ServiceRequest;
import com.prodigy.domain.Question;

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
