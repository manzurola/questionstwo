package com.prodigy.webapp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.prodigy.domain.AnswerFactory;
import com.prodigy.domain.Id;
import com.prodigy.domain.Question;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Answer {

    private Id<com.prodigy.domain.Answer> id;
    private Id<Question> questionId;
    private String input;

    public Answer() {
    }

    public Answer(com.prodigy.domain.Answer domain) {
        this(domain.getId(), domain.getQuestionId(), domain.getInput());
    }

    public Answer(Id<Question> questionId, String input) {
        this.questionId = questionId;
        this.input = input;
    }

    public Answer(Id<com.prodigy.domain.Answer> id,
                  Id<Question> questionId,
                  String input) {
        this.id = id;
        this.questionId = questionId;
        this.input = input;
    }

    public Id<com.prodigy.domain.Answer> getId() {
        return id;
    }

    public Answer setId(Id<com.prodigy.domain.Answer> id) {
        this.id = id;
        return this;
    }

    public Id<Question> getQuestionId() {
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

    public com.prodigy.domain.Answer toDomain(AnswerFactory factory) {
        return factory.build(questionId, input);
    }
}
