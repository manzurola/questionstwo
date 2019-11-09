package com.prodigy.application.command;

import com.prodigy.domain.Id;
import com.prodigy.domain.Question;

import java.util.List;
import java.util.Objects;

public class AddQuestionCommand implements Command {

    private final Id<Question> id;
    private final String body;
    private final List<String> answerKey;
    private final String instructions;

    public AddQuestionCommand(String body, List<String> answerKey, String instructions) {
        this(Id.next(), body, answerKey, instructions);
    }

    public AddQuestionCommand(Id<Question> id, String body, List<String> answerKey, String instructions) {
        this.id = id;
        this.body = body;
        this.answerKey = answerKey;
        this.instructions = instructions;
    }

    public AddQuestionCommand(Question question) {
        this(question.getId(), question.getBody(), question.getAnswerKey(), question.getInstructions());
    }

    public String body() {
        return body;
    }

    public List<String> answerKey() {
        return answerKey;
    }

    public String instructions() {
        return instructions;
    }

    public Question toQuestion() {
        return new Question(this.id, this.body, this.answerKey, this.instructions);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AddQuestionCommand)) return false;
        AddQuestionCommand that = (AddQuestionCommand) o;
        return Objects.equals(body, that.body) &&
                Objects.equals(answerKey, that.answerKey) &&
                Objects.equals(instructions, that.instructions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(body, answerKey, instructions);
    }
}
