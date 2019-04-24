package com.prodigy.api.questions;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.prodigy.api.common.Id;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Created by guym on 16/05/2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Question {

    private final Id<Question> id;
    private final String body;
    private final List<String> answerKey;
    private final String instructions;

    public Question(Id<Question> id, String body, List<String> answerKey, String instructions) {
        this.id = id;
        this.body = body;
        this.answerKey = answerKey;
        this.instructions = instructions;
    }

    public Id<Question> getId() {
        return id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Question)) return false;
        Question question = (Question) o;
        return Objects.equals(id, question.id) &&
                Objects.equals(body, question.body) &&
                Objects.equals(answerKey, question.answerKey);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, body, answerKey);
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", body='" + body + '\'' +
                ", answerKey=" + answerKey +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private Id<Question> id = Id.next();
        private String body;
        private List<String> answerKey;
        private String instructions;

        public Builder id(Id<Question> id) {
            this.id = id;
            return this;
        }

        public Builder body(String body) {
            this.body = body;
            return this;
        }

        public String body() {
            return this.body;
        }

        public Builder answerKey(List<String> answerKey) {
            this.answerKey = answerKey;
            return this;
        }

        public List<String> answerKey() {
            return this.answerKey;
        }

        public Builder answerKey(String... answerKey) {
            this.answerKey = Arrays.asList(answerKey);
            return this;
        }

        public Builder instructions(String instructions){
            this.instructions = instructions;
            return this;
        }


        public Question build() {
            return new Question(id, body, answerKey, instructions);
        }

    }
}
