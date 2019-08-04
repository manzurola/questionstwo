package com.prodigy.game;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.prodigy.engine.Sentence;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Created by guym on 16/05/2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Question {

    private final Id<Question> id;
    private final Sentence source;
    private final List<Sentence> targets;
    private final String description;

    private Question(Builder builder) {
        this.id = builder.id;
        this.source = builder.source;
        this.targets = builder.targets;
        this.description = builder.description;
    }

    public Id<Question> getId() {
        return id;
    }

    public Sentence getSource() {
        return source;
    }

    public List<Sentence> getTargets() {
        return targets;
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
                Objects.equals(targets, question.targets);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, source, targets);
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", body='" + source + '\'' +
                ", answerKey=" + targets +
                '}';
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static class Builder {

        private Id<Question> id = Id.next();
        private Sentence source;
        private List<Sentence> targets;
        private String description;

        public Builder id(Id<Question> id) {
            this.id = id;
            return this;
        }

        public Builder source(Sentence source) {
            this.source = source;
            return this;
        }

        public Builder targets(List<Sentence> targets) {
            this.targets = targets;
            return this;
        }

        public Builder targets(Sentence... targets) {
            this.targets = Arrays.asList(targets);
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
