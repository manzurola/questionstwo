package com.prodigy.api.answers;

import com.prodigy.api.common.Id;
import com.prodigy.api.questions.Question;
import com.prodigy.api.review.Review;
import com.prodigy.api.users.User;

import javax.validation.constraints.NotNull;
import java.util.Objects;

public class Answer {

    private final Id<Answer> id;
    private final Id<User> userId;
    private final Id<Question> questionId;
    private final String input;

    public Answer(Id<Answer> id, Id<User> userId, Id<Question> questionId, String input) {
        this.id = id;
        this.userId = userId;
        this.questionId = questionId;
        this.input = input;
    }

    public static Builder builder() {
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

    public String getInput() {
        return input;
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
        private String input;

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

        public Builder input(String input) {
            this.input = input;
            return this;
        }

        public Answer build() {
            return new Answer(id, userId, questionId, input);
        }
    }

}
