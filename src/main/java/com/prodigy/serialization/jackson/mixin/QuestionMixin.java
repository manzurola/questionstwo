package com.prodigy.serialization.jackson.mixin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.prodigy.domain.Id;
import com.prodigy.domain.Question;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class QuestionMixin extends Question {

    public QuestionMixin(Id<Question> id, String body, List<String> answerKey, String instructions) {
        super(id, body, answerKey, instructions);
    }

    @Override
    public Id<Question> getId() {
        return super.getId();
    }

    @Override
    public String getBody() {
        return super.getBody();
    }

    @Override
    public List<String> getAnswerKey() {
        return super.getAnswerKey();
    }

    @Override
    public String getInstructions() {
        return super.getInstructions();
    }
}
