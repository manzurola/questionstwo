package com.prodigy.api.questions.domain;

import java.util.List;

public interface QuestionFactory {

    Question create(String body, List<String> answerKey, String instructions, String subject, String source);
}
