package com.prodigy.webapp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.prodigy.common.Id;
import com.prodigy.domain.answer.AnswerFactory;
import com.prodigy.domain.questions.domain.Question;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Answer {

    private Id<com.prodigy.domain.answer.Answer> id;
    private Id<com.prodigy.domain.questions.domain.Question> questionId;
    private String input;

    public Answer() {
    }

    public Answer(com.prodigy.domain.answer.Answer domain) {
        this(domain.getId(), domain.getQuestionId(), domain.getInput());
    }

    public Answer(Id<com.prodigy.domain.questions.domain.Question> questionId, String input) {
        this.questionId = questionId;
        this.input = input;
    }

    public Answer(Id<com.prodigy.domain.answer.Answer> id,
                  Id<com.prodigy.domain.questions.domain.Question> questionId,
                  String input) {
        this.id = id;
        this.questionId = questionId;
        this.input = input;
    }

    public Id<com.prodigy.domain.answer.Answer> getId() {
        return id;
    }

    public Answer setId(Id<com.prodigy.domain.answer.Answer> id) {
        this.id = id;
        return this;
    }

    public Id<com.prodigy.domain.questions.domain.Question> getQuestionId() {
        return questionId;
    }

    public Answer setQuestionId(Id<Question> questionId) {
        this.questionId = questionId;
        return this;
    }

    public String getInput() {
        return input;
    }

    public Answer setInput(String input) {
        this.input = input;
        return this;
    }

    com.prodigy.domain.answer.Answer toDomain(AnswerFactory factory) {
        return factory.create(questionId, input);
    }
}
