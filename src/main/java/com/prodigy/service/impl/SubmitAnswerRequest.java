package com.prodigy.service.impl;

import com.prodigy.common.Id;
import com.prodigy.common.service.ServiceRequest;
import com.prodigy.domain.Question;

import java.util.Objects;

public class SubmitAnswerRequest implements ServiceRequest {

    private Id<Question> questionId;
    private String answer;

    public SubmitAnswerRequest(Id<Question> questionId, String answer) {
        this.questionId = questionId;
        this.answer = answer;
    }

    public Id<Question> getQuestionId() {
        return questionId;
    }

    public SubmitAnswerRequest withQuestionId(Id<Question> questionId) {
        return new SubmitAnswerRequest(questionId, answer);
    }

    public String getAnswer() {
        return answer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SubmitAnswerRequest)) return false;
        SubmitAnswerRequest that = (SubmitAnswerRequest) o;
        return Objects.equals(questionId, that.questionId) &&
                Objects.equals(answer, that.answer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(questionId, answer);
    }
}
