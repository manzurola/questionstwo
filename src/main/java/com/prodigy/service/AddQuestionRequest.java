package com.prodigy.service;

import com.prodigy.domain.Question;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class AddQuestionRequest implements ServiceRequest {

    @NotEmpty
    private final String body;
    @NotNull
    private final List<@NotEmpty String> answerKey;
    @NotEmpty
    private final String instructions;

    public AddQuestionRequest(String body, List<String> answerKey, String instructions) {
        this.body = body;
        this.answerKey = answerKey;
        this.instructions = instructions;
    }

    public AddQuestionRequest(Question question) {
        this.body = question.getBody();
        this.answerKey = question.getAnswerKey();
        this.instructions = question.getInstructions();
    }

    public String getBody() {
        return body;
    }

    public List<String> getAnswerKey() {
        return answerKey;
    }

    public String getInstructions() {
        return instructions;
    }

    public Question toQuestion() {
        return new Question(this.getBody(), this.getAnswerKey(), this.getInstructions());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AddQuestionRequest)) return false;
        AddQuestionRequest that = (AddQuestionRequest) o;
        return Objects.equals(body, that.body) &&
                Objects.equals(answerKey, that.answerKey) &&
                Objects.equals(instructions, that.instructions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(body, answerKey, instructions);
    }
}
