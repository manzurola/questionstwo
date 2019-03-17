package com.prodigy.api.questions.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.prodigy.api.common.EntityFactory;

import java.util.List;
import java.util.Objects;

/**
 * Created by guym on 16/05/2017.
 */
public class Question {

    private final String id;
    private final String body;
    private final List<String> answerKey;
    private final String instructions;
    private final String subject;
    private final String source;
    private final String version; // to reference the parser version

    @JsonCreator
    private Question(String id,
                    String body,
                    List<String> answerKey,
                    String instructions,
                    String subject,
                    String source,
                    String version) {
        this.id = id;
        this.body = body;
        this.answerKey = answerKey;
        this.instructions = instructions;
        this.subject = subject;
        this.source = source;
        this.version = version;
    }

    public String getId() {
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

    public String getSubject() {
        return subject;
    }

    public String getSource() {
        return source;
    }

    public String getVersion() {
        return version;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return Objects.equals(id, question.id) &&
                Objects.equals(body, question.body) &&
                Objects.equals(answerKey, question.answerKey) &&
                Objects.equals(instructions, question.instructions) &&
                Objects.equals(subject, question.subject) &&
                Objects.equals(source, question.source) &&
                Objects.equals(version, question.version);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, body, answerKey, instructions, subject, source, version);
    }

    @Override
    public String toString() {
        return "Question{" +
                "id='" + id + '\'' +
                ", body='" + body + '\'' +
                ", answerKey=" + answerKey +
                ", instructions='" + instructions + '\'' +
                ", subject='" + subject + '\'' +
                ", source='" + source + '\'' +
                ", version='" + version + '\'' +
                '}';
    }

    public static class Builder implements EntityFactory<Question> {

        private final String id;
        private String body;
        private List<String> answerKey;
        private String instructions;
        private String subject;
        private String source;
        private String version; // to reference the parser version

        public Builder() {
            this.id = nextId();
        }

        public Builder(Question question) {
            this.id = question.getId();
            merge(question);
        }

        public Builder merge(Question other) {
            this.body = other.getBody();
            this.answerKey = other.getAnswerKey();
            this.instructions = other.getInstructions();
            this.subject = other.getSubject();
            this.source = other.getSource();
            this.version = other.getVersion();
            return this;
        }

        public Builder setBody(String body) {
            this.body = body;
            return this;
        }

        public Builder setAnswerKey(List<String> answerKey) {
            this.answerKey = answerKey;
            return this;
        }

        public Builder setInstructions(String instructions) {
            this.instructions = instructions;
            return this;
        }

        public Builder setSubject(String subject) {
            this.subject = subject;
            return this;
        }

        public Builder setSource(String source) {
            this.source = source;
            return this;
        }

        public Builder setVersion(String version) {
            this.version = version;
            return this;
        }

        public Question build() {
            return new Question(id, body, answerKey, instructions, subject, source, version);
        }

    }
}
