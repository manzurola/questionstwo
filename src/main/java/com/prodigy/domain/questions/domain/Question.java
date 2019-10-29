package com.prodigy.domain.questions.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.prodigy.common.Id;

import java.util.List;
import java.util.Objects;

/**
 * Created by guym on 16/05/2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(builder = Question.Builder.class)
public class Question {

    private final Id<Question> id;
    private final String body;
    private final List<String> answerKey;
    private final String instructions;

    private Question(Builder builder) {
        this.id = builder.id;
        this.body = builder.body;
        this.answerKey = builder.answerKey;
        this.instructions = builder.instructions;
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

    public static Builder newBuilder() {
        return new Builder();
    }

    public static class Builder {

        private Id<Question> id = Id.next();
        private String body;
        private List<String> answerKey;
        private String instructions;

        public Builder withId(Id<Question> id) {
            this.id = id;
            return this;
        }

        public Builder withBody(String body) {
            this.body = body;
            return this;
        }

        public Builder withAnswerKey(List<String> answerKey) {
            this.answerKey = answerKey;
            return this;
        }

        public Builder withInstructions(String instructions){
            this.instructions = instructions;
            return this;
        }

        public Question build() {
            return new Question(this);
        }

    }
}
