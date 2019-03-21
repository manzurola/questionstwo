package com.prodigy.api.test.review;

import com.prodigy.api.test.answers.Answer;
import com.prodigy.api.test.common.service.ServiceRequest;
import com.prodigy.api.test.questions.Question;

public class SuggestReviewRequest implements ServiceRequest {

    private Question question;
    private Answer answer;

    public SuggestReviewRequest(Question question, Answer answer) {
        this.question = question;
        this.answer = answer;
    }

    public Question getQuestion() {
        return question;
    }

    public Answer getAnswer() {
        return answer;
    }
}
