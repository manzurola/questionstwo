package com.prodigy.domain.questions;

public interface QuestionFactory {

    Question create(String body, String instructions, String solution);

    Question create(QuestionData data);
}
