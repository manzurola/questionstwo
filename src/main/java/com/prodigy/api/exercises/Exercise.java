package com.prodigy.api.exercises;

import com.prodigy.api.common.Id;
import com.prodigy.api.questions.Question;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Exercise {

    private final Id<Exercise> id;
    private final String title;
    private final List<Question> questions;

    public Exercise(Id<Exercise> id, String title, List<Question> questions) {
        this.id = id;
        this.title = title;
        this.questions = questions;
    }

    public Id<Exercise> getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }


    public List<Question> getQuestions() {
        return questions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Exercise)) return false;
        Exercise exercise = (Exercise) o;
        return Objects.equals(id, exercise.id) &&
                Objects.equals(title, exercise.title) &&
                Objects.equals(questions, exercise.questions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, questions);
    }

    @Override
    public String toString() {
        return "Exercise{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", questions=" + questions +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Id<Exercise> id= Id.next();
        private String title;
        private List<Question> questions = new ArrayList<>();

        public Builder id(Id<Exercise> id) {
            this.id = id;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder questions(List<Question> questions) {
            this.questions = questions;
            return this;
        }

        public Builder addQuestion(Question question) {
            this.questions.add(question);
            return this;
        }

        public Exercise build() {
            return new Exercise(id, title, questions);
        }
    }
}
