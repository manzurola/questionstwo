package com.prodigy.domain.questions;

public interface QuestionPreprocessorHandler {

    Question.Builder handle(Question.Builder question);
}
