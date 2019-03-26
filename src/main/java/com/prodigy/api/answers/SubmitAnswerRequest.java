package com.prodigy.api.answers;

import com.prodigy.api.common.Id;
import com.prodigy.api.common.service.ServiceRequest;
import com.prodigy.api.questions.Question;
import com.prodigy.api.users.User;

import java.util.Objects;

public class SubmitAnswerRequest implements ServiceRequest {

    private Id<User> userId;
    private Id<Question> questionId;
    private String answer;

    public SubmitAnswerRequest(Id<User> userId, Id<Question> questionId, String answer) {
        this.userId = userId;
        this.questionId = questionId;
        this.answer = answer;
    }

    public Id<User> getUserId() {
        return userId;
    }

    public Id<Question> getQuestionId() {
        return questionId;
    }

    public String getAnswer() {
        return answer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SubmitAnswerRequest)) return false;
        SubmitAnswerRequest that = (SubmitAnswerRequest) o;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(questionId, that.questionId) &&
                Objects.equals(answer, that.answer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, questionId, answer);
    }
}
