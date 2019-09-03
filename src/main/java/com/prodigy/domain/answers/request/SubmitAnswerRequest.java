package com.prodigy.domain.answers.request;

import com.prodigy.domain.common.Id;
import com.prodigy.domain.common.service.ServiceRequest;
import com.prodigy.domain.questions.Question;

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
