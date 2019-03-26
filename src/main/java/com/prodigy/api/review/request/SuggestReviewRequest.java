package com.prodigy.api.review.request;

import com.prodigy.api.answers.Answer;
import com.prodigy.api.common.service.ServiceRequest;
import com.prodigy.api.questions.Question;

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
