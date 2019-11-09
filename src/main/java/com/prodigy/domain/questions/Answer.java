package com.prodigy.domain.questions;

import com.prodigy.domain.Id;
import com.prodigy.domain.diff.SentenceDiff;
import com.prodigy.domain.nlp.Sentence;

public class Answer {

    private final Id<Answer> id;
    private final Id<Question> questionId;
    private final Sentence value;
    private final Sentence solution;
    private final SentenceTransform sentenceTransform;
    private final Score score;

    public Answer(Id<Answer> id,
                  Id<Question> questionId,
                  Sentence value,
                  Sentence solution,
                  SentenceTransform sentenceTransform,
                  Score score) {
        this.id = id;
        this.questionId = questionId;
        this.value = value;
        this.solution = solution;
        this.sentenceTransform = sentenceTransform;
        this.score = score;
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

    public Sentence solution() {
        return solution;
    }

    public SentenceTransform sentenceTransform() {
        return sentenceTransform;
    }

    public Score score() {
        return score;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "id=" + id +
                ", questionId=" + questionId +
                ", value=" + value +
                ", sentenceTransform=" + sentenceTransform +
                ", score=" + score +
                '}';
    }
}
