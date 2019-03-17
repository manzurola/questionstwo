package com.prodigy.api.questions.domain;

import java.util.List;
import java.util.Objects;

/**
 * Created by guym on 16/05/2017.
 */
public class Question {

    private String id;
    private String body;
    private List<String> answerKey;
    private String instructions;
    private String subject;
    private String source;
    private String version; // to reference the parser version

    public Question() {
    }

    public Question(String id, String body, List<String> answerKey, String instructions, String subject, String source, String version) {
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

    public void setId(String id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public List<String> getAnswerKey() {
        return answerKey;
    }

    public void setAnswerKey(List<String> answerKey) {
        this.answerKey = answerKey;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
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
}
