package com.prodigy.api.questions;

public interface QuestionPreprocessor {

    Question.Builder preprocess(Question.Builder question);
}
