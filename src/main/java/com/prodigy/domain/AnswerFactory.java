package com.prodigy.domain;


public interface AnswerFactory {
    Answer create(Id<Question> questionId, String input);
    Answer create(Id<Answer> id, Id<Question> questionId, String input);
}
