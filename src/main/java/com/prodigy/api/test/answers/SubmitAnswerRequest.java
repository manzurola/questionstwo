package com.prodigy.api.test.answers;

import com.prodigy.api.test.common.Id;
import com.prodigy.api.test.common.service.ServiceRequest;
import com.prodigy.api.test.questions.Question;
import com.prodigy.api.test.users.User;

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
}
