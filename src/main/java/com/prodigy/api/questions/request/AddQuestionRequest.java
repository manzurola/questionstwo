package com.prodigy.api.questions.request;

import com.prodigy.api.common.service.ServiceRequest;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

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

    public AddQuestionRequest(String body, List<String> answerKey, String instructions, String subject, String source) {
        this.body = body;
        this.answerKey = answerKey;
        this.instructions = instructions;
        this.subject = subject;
        this.source = source;
    }

    private AddQuestionRequest(AddQuestionRequest.Builder builder) {
        this.body = builder.body;
        this.answerKey = builder.answerKey;
        this.instructions = builder.instructions;
        this.subject = builder.subject;
        this.source = builder.source;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AddQuestionRequest)) return false;
        AddQuestionRequest that = (AddQuestionRequest) o;
        return Objects.equals(body, that.body) &&
                Objects.equals(answerKey, that.answerKey) &&
                Objects.equals(instructions, that.instructions) &&
                Objects.equals(subject, that.subject) &&
                Objects.equals(source, that.source);
    }

    @Override
    public int hashCode() {
        return Objects.hash(body, answerKey, instructions, subject, source);
    }

    public static Builder builder() {return new Builder();}

    public static class Builder {
        private String body;
        private List<String> answerKey;
        private String instructions;
        private String subject;
        private String source;

        public Builder body(String body) {
            this.body = body;
            return this;
        }

        public Builder answerKey(List<String> answerKey) {
            this.answerKey = answerKey;
            return this;
        }

        public Builder answerKey(String... answers) {
            this.answerKey = Arrays.asList(answers);
            return this;
        }

        public Builder instructions(String instructions) {
            this.instructions = instructions;
            return this;
        }

        public Builder subject(String subject) {
            this.subject = subject;
            return this;
        }

        public Builder source(String source) {
            this.source = source;
            return this;
        }

        public AddQuestionRequest build() {
            return new AddQuestionRequest(this);
        }
    }
}
