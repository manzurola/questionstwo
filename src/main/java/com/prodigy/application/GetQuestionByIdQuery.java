package com.prodigy.application;

import com.prodigy.application.query.Query;
import com.prodigy.domain.Id;
import com.prodigy.domain.Question;
import com.prodigy.application.command.Command;

import java.util.Objects;

public class GetQuestionByIdQuery implements Query<Question> {

    private Id<Question> id;

    public GetQuestionByIdQuery(Id<Question> id) {
        this.id = id;
    }

    public Id<Question> getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GetQuestionByIdQuery)) return false;
        GetQuestionByIdQuery that = (GetQuestionByIdQuery) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
