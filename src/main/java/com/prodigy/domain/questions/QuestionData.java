package com.prodigy.domain.questions;

import com.prodigy.domain.Id;

public class QuestionData {

    private final Id<Question> id;
    private final String body;
    private final String instructions;
    private final String solution;

    public QuestionData(Id<Question> id, String body, String instructions, String solution) {
        this.id = id;
        this.body = body;
        this.instructions = instructions;
        this.solution = solution;
    }

    public Id<Question> id() {
        return id;
    }

    public String body() {
        return body;
    }

    public String instructions() {
        return instructions;
    }

    public String solution() {
        return solution;
    }
}
