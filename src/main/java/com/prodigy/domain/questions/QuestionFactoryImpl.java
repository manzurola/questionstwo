package com.prodigy.domain.questions;

import com.prodigy.domain.Id;

public class QuestionFactoryImpl implements QuestionFactory {

    private final AnswerFactoryBuilder answerFactoryBuilder;

    public QuestionFactoryImpl(AnswerFactoryBuilder answerFactoryBuilder) {
        this.answerFactoryBuilder = answerFactoryBuilder;
    }

    @Override
    public Question create(String body, String instructions, String solution) {
        return create(new QuestionData(Id.next(), body, instructions, solution));
    }

    @Override
    // question data may contain information needed to instantiate specific evaluation strategies, such as weights, feedback etc
    public Question create(QuestionData data) {
        return new Question(
                data,
                answerFactoryBuilder.forQuestion(data)
        );
    }
}
