package com.prodigy.api.questions;

public interface QuestionPreprocessorHandler {

    Question.Builder handle(Question.Builder question);
}
