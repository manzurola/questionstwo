package com.prodigy.api.questions.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

public class AddQuestionRequest {

    @JsonUnwrapped
    private final Question.Builder question;

    @JsonCreator
    public AddQuestionRequest(Question.Builder question) {
        this.question = question;
    }

    public Question.Builder getQuestion() {
        return question;
    }
}
