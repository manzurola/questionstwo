package com.prodigy.domain;

public interface QuestionFactory {

    Question createQuestion(String sourceSentence, String targetSentence, String instructions);
}
