package com.prodigy.serialization.jackson.mixin;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.prodigy.common.Id;
import com.prodigy.domain.Answer;
import com.prodigy.domain.Question;
import com.prodigy.domain.review.Review;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AnswerMixin extends Answer {

    @JsonCreator
    public AnswerMixin(Id<Answer> id, Id<Question> questionId, String input, Review review) {
        super(id, questionId, input, review);
    }

    @Override
    public Id<Answer> getId() {
        return super.getId();
    }

    @Override
    public Id<Question> getQuestionId() {
        return super.getQuestionId();
    }

    @Override
    public String getInput() {
        return super.getInput();
    }
}
