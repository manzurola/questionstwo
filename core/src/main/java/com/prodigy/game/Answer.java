package com.prodigy.game;

import java.time.LocalDateTime;
import java.util.Objects;

public class Answer {

    private final Id<Answer> id;
    private final Id<User> userId;
    private final Id<Question> questionId;
    private final Sentence input;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;

    private Answer(Builder builder) {
        this.id = builder.id;
        this.userId = builder.userId;
        this.questionId = builder.questionId;
        this.input = builder.input;
        this.startTime = builder.startTime;
        this.endTime = builder.endTime;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public Id<Answer> getId() {
        return id;
    }

    public Id<User> getUserId() {
        return userId;
    }

    public Id<Question> getQuestionId() {
        return questionId;
    }

    public Sentence getInput() {
        return input;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Answer)) return false;
        Answer answer = (Answer) o;
        return Objects.equals(id, answer.id) &&
                Objects.equals(userId, answer.userId) &&
                Objects.equals(questionId, answer.questionId) &&
                Objects.equals(input, answer.input);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, questionId, input);
    }

    @Override
    public String toString() {
        return "Answer{" +
                "id=" + id +
                ", userId=" + userId +
                ", questionId=" + questionId +
                ", input='" + input + '\'' +
                '}';
    }

    public static class Builder {
        private Id<Answer> id = Id.next();
        private Id<User> userId;
        private Id<Question> questionId;
        private Sentence input;
        private LocalDateTime startTime;
        private LocalDateTime endTime;

        public Builder id(Id<Answer> id) {
            this.id = id;
            return this;
        }

        public Builder userId(Id<User> userId) {
            this.userId = userId;
            return this;
        }

        public Builder questionId(Id<Question> questionId) {
            this.questionId = questionId;
            return this;
        }

        public Builder input(Sentence input) {
            this.input = input;
            return this;
        }

        public Builder startTime(LocalDateTime time) {
            this.startTime = time;
            return this;
        }

        public Builder endTime(LocalDateTime time) {
            this.endTime = time;
            return this;
        }

        public Answer build() {
            return new Answer(this);
        }
    }

}
