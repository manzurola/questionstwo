package com.prodigy.domain.questions;

import com.prodigy.domain.Id;

public class Question {

    private final QuestionData data;
    private final AnswerFactory answerFactory;

    public Question(QuestionData data,
                    AnswerFactory answerFactory) {
        this.data = data;
        this.answerFactory = answerFactory;
    }

    public Id<Question> id() {
        return data.id();
    }

    public String body() {
        return data.body();
    }

    public String instructions() {
        return data.instructions();
    }

    public QuestionData data() {
        return data;
    }

    public Answer newAnswer(String value) {
        return answerFactory.create(value);
    }
}
