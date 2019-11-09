package com.prodigy.application.query;

import com.prodigy.domain.Id;
import com.prodigy.domain.Question;

public class GetQuestionByIdQuery implements Query<Question> {

    private final Id<Question> questionId;

    public GetQuestionByIdQuery(Id<Question> questionId) {
        this.questionId = questionId;
    }

    public Id<Question> questionId() {
        return questionId;
    }
}
