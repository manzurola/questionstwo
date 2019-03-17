package com.prodigy.api.questions.domain;

import com.prodigy.api.common.EntityFactory;

import java.util.List;

public class QuestionFactoryImpl extends EntityFactory implements QuestionFactory {

    @Override
    public Question create(String body, List<String> answerKey, String instructions, String subject, String source) {
        return new Question(nextId(), body, answerKey, instructions, subject, source, "1");
    }

}
