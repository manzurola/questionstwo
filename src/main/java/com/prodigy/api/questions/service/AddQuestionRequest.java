package com.prodigy.api.questions.service;

import com.prodigy.api.common.service.ServiceRequest;

import java.util.List;
import java.util.UUID;

public class AddQuestionRequest implements ServiceRequest {

    private final String id = UUID.randomUUID().toString();
    private final String body;
    private final List<String> answerKey;
    private final String instructions;
    private final String subject;
    private final String source;
    private final String version; // to reference the parser version


    public AddQuestionRequest(String body, List<String> answerKey, String instructions, String subject, String source, String version) {
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
}
