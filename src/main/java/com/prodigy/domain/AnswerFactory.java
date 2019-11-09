package com.prodigy.domain;


public interface AnswerFactory {
    Answer build(Id<Question> questionId, String input);
}
