package com.prodigy.questions;

public interface QuestionPreprocessor {

    Question.Builder preprocess(Question.Builder question);
}
