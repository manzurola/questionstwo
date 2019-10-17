package com.prodigy.questions;

public interface QuestionPreprocessorHandler {

    Question.Builder handle(Question.Builder question);
}
