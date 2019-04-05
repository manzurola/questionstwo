package com.prodigy.api.questions;

import com.prodigy.api.common.Id;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Exercise {

    private final Id<Exercise> id;
    private final String title;
    private final String source;
    private final List<Id<Question>> questionsIds;

    public Exercise(Id<Exercise> id, String title, String source, List<Id<Question>> questionsIds) {
        this.id = id;
        this.title = title;
        this.source = source;
        this.questionsIds = questionsIds;
    }

    public Id<Exercise> getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getSource() {
        return source;
    }

    public List<Id<Question>> getQuestionsIds() {
        return questionsIds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Exercise)) return false;
        Exercise exercise = (Exercise) o;
        return Objects.equals(id, exercise.id) &&
                Objects.equals(title, exercise.title) &&
                Objects.equals(source, exercise.source) &&
                Objects.equals(questionsIds, exercise.questionsIds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, source, questionsIds);
    }

    @Override
    public String toString() {
        return "Exercise{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", source='" + source + '\'' +
                ", questionsIds=" + questionsIds +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Id<Exercise> id= Id.next();
        private String title;
        private String source;
        private List<Id<Question>> questionIds = new ArrayList<>();

        public Builder id(Id<Exercise> id) {
            this.id = id;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder source(String source) {
            this.source = source;
            return this;
        }

        public Builder addQuestion(Id<Question> questionId) {
            questionIds.add(questionId);
            return this;
        }

        public Builder questions(List<Id<Question>> questionIds) {
            this.questionIds = questionIds;
            return this;
        }

        public Exercise build() {
            return new Exercise(id, title, source, questionIds);
        }
    }
}
