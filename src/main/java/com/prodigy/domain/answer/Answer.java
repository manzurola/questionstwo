package com.prodigy.domain.answer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.prodigy.common.Id;
import com.prodigy.domain.questions.domain.Question;
import com.prodigy.domain.review.Review;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(builder = Answer.Builder.class)
public class Answer {

    private final Id<Answer> id;
    private final Id<Question> questionId;
    private final String input;
    private final Review review;

    public Answer(Builder builder) {
        this.id = builder.id;
        this.questionId = builder.questionId;
        this.input = builder.input;
        this.review = builder.review;
    }

    public static Builder builder() {
        return new Builder();
    }

    public Id<Answer> getId() {
        return id;
    }

    public Id<Question> getQuestionId() {
        return questionId;
    }

    public String getInput() {
        return input;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "id=" + id +
                ", questionId=" + questionId +
                ", input='" + input + '\'' +
                ", review=" + review +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Answer answer = (Answer) o;
        return Objects.equals(id, answer.id) &&
                Objects.equals(questionId, answer.questionId) &&
                Objects.equals(input, answer.input) &&
                Objects.equals(review, answer.review);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, questionId, input, review);
    }

    @JsonPOJOBuilder(withPrefix = "")
    public static class Builder {
        private Id<Answer> id = Id.next();
        private Id<Question> questionId;
        private String input;
        private Review review;

        public Builder id(Id<Answer> id) {
            this.id = id;
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

        public Builder review(Review review) {
            this.review = review;
            return this;
        }

        public Answer build() {
            return new Answer(this);
        }
    }

}
