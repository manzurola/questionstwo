package com.prodigy.api.exercises;

import com.prodigy.api.common.service.ServiceRequest;
import com.prodigy.api.questions.Question;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

public class AddExerciseRequest implements ServiceRequest {

    @NotEmpty
    private final String title;
    private final String source;
    @NotNull
    private final List<@NotNull Question> questions;

    public AddExerciseRequest(@NotEmpty String title, String source, @NotNull List<@NotNull Question> questions) {
        this.title = title;
        this.source = source;
        this.questions = questions;
    }

    public String getTitle() {
        return title;
    }

    public String getSource() {
        return source;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AddExerciseRequest)) return false;
        AddExerciseRequest that = (AddExerciseRequest) o;
        return Objects.equals(title, that.title) &&
                Objects.equals(source, that.source) &&
                Objects.equals(questions, that.questions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, source, questions);
    }
}
