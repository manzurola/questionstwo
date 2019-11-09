package com.prodigy.application.command;

import com.prodigy.domain.Answer;
import com.prodigy.domain.Id;
import com.prodigy.domain.Question;

public class AddAnswerCommand implements Command {

    private final Id<Answer> answerId;
    private final Id<Question> questionId;
    private final String input;

    public AddAnswerCommand(Id<Question> questionId, String input) {
        this(Id.next(), questionId, input);
    }

    public AddAnswerCommand(Id<Answer> answerId, Id<Question> questionId, String input) {
        this.answerId = answerId;
        this.questionId = questionId;
        this.input = input;
    }

    public Id<Question> questionId() {
        return questionId;
    }

    public String input() {
        return input;
    }

}
