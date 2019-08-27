package com.prodigy.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Objects;

/**
 * Created by guym on 16/05/2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Question {

    private final Id<Question> id;
    private final Sentence source;
    private final Sentence target;
    private final String description;

    private Question(Builder builder) {
        this.id = builder.id;
        this.source = builder.source;
        this.target = builder.target;
        this.description = builder.description;
    }

    public Id<Question> getId() {
        return id;
    }

    public Sentence getSource() {
        return source;
    }

    public Sentence getTarget() {
        return target;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Question)) return false;
        Question question = (Question) o;
        return Objects.equals(id, question.id) &&
                Objects.equals(source, question.source) &&
                Objects.equals(target, question.target);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, source, target);
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", body='" + source + '\'' +
                ", answerKey=" + target +
                '}';
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static class Builder {

        private Id<Question> id = Id.next();
        private Sentence source;
        private Sentence target;
        private String description;

        public Builder id(Id<Question> id) {
            this.id = id;
            return this;
        }

        public Builder source(Sentence source) {
            this.source = source;
            return this;
        }

        public Builder target(Sentence target) {
            this.target = target;
            return this;
        }

        public Builder description(String instructions){
            this.description = instructions;
            return this;
        }


        public Question build() {
            return new Question(this);
        }

    }
}
