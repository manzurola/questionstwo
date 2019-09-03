package com.prodigy.domain.questions;

public interface QuestionPreprocessor {

    Question.Builder preprocess(Question.Builder question);
}
