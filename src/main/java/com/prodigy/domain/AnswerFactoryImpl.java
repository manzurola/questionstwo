package com.prodigy.domain;

public class AnswerFactoryImpl implements AnswerFactory {
    @Override
    public Answer build(Id<Question> questionId, String input) {
        return new Answer(Id.next(), questionId, input);
    }
}
