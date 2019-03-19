package com.prodigy.api.questions.request;

import com.prodigy.api.common.service.ServiceRequest;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public class AddQuestionRequest implements ServiceRequest {

    @NotEmpty
    private final String body;
    @NotNull
    private final List<@NotEmpty String> answerKey;
    @NotEmpty
    private final String instructions;
    @NotEmpty
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
