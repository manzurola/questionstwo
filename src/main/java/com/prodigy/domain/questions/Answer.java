package com.prodigy.domain.questions;

import com.prodigy.domain.Id;
import com.prodigy.domain.diff.SentenceDiff;
import com.prodigy.domain.nlp.Sentence;

public class Answer {

    private final Id<Answer> id;
    private final Id<Question> questionId;
    private final Sentence value;
    private final SentenceDiff diff;
    private final Score score;
    private final String feedback;

    public Answer(Id<Answer> id,
                  Id<Question> questionId,
                  Sentence value,
                  SentenceDiff diff,
                  Score score,
                  String feedback) {
        this.id = id;
        this.questionId = questionId;
        this.value = value;
        this.diff = diff;
        this.score = score;
        this.feedback = feedback;
    }

    public Id<Answer> id() {
        return id;
    }

    public Id<Question> questionId() {
        return questionId;
    }

    public Sentence value() {
        return value;
    }

    public SentenceDiff diff() {
        return diff;
    }

    public Score score() {
        return score;
    }

    public String feedback() {
        return feedback;
    }
}
