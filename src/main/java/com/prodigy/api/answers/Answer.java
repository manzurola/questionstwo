package com.prodigy.api.answers;

import com.prodigy.api.common.Id;
import com.prodigy.api.questions.Question;
import com.prodigy.api.users.User;

import java.util.Objects;

public class Answer {

    private Id<Answer> id;
    private Id<User> userId;
    private Id<Question> questionId;
    private String answer;

    public Answer(Id<Answer> id, Id<User> userId, Id<Question> questionId, String answer) {
        this.id = id;
        this.userId = userId;
        this.questionId = questionId;
        this.answer = answer;
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

    public String getAnswer() {
        return answer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Answer)) return false;
        Answer answer1 = (Answer) o;
        return Objects.equals(id, answer1.id) &&
                Objects.equals(userId, answer1.userId) &&
                Objects.equals(questionId, answer1.questionId) &&
                Objects.equals(answer, answer1.answer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, questionId, answer);
    }

    public static class Builder {
        private Id<Answer> id = Id.next();
        private Id<User> userId;
        private Id<Question> questionId;
        private String answer;

        public Builder setId(Id<Answer> id) {
            this.id = id;
            return this;
        }

        public Builder setUserId(Id<User> userId) {
            this.userId = userId;
            return this;
        }

        public Builder setQuestionId(Id<Question> questionId) {
            this.questionId = questionId;
            return this;
        }

        public Builder setAnswer(String answer) {
            this.answer = answer;
            return this;
        }

        public Answer build() {
            return new Answer(id, userId, questionId, answer);
        }
    }
}
