package com.prodigy.ml;

import com.prodigy.api.common.Id;
import com.prodigy.api.questions.Question;
import com.prodigy.nlp.Sentence;
import com.prodigy.nlp.diff.WordDiff;

import java.util.List;
import java.util.Objects;

public class QuestionParseImpl implements QuestionParse {

    private Id<Question> questionId;
    private Sentence source;
    private Sentence target;
    private List<WordDiff> wordDiff;

    public QuestionParseImpl(Id<Question> questionId, Sentence source, Sentence target, List<WordDiff> wordDiff) {
        this.questionId = questionId;
        this.source = source;
        this.target = target;
        this.wordDiff = wordDiff;
    }

    @Override
    public Id<Question> questionId() {
        return questionId;
    }

    @Override
    public Sentence source() {
        return source;
    }

    @Override
    public Sentence target() {
        return target;
    }

    @Override
    public List<WordDiff> wordDiff() {
        return wordDiff;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof QuestionParseImpl)) return false;
        QuestionParseImpl that = (QuestionParseImpl) o;
        return Objects.equals(questionId, that.questionId) &&
                Objects.equals(source, that.source) &&
                Objects.equals(target, that.target) &&
                Objects.equals(wordDiff, that.wordDiff);
    }

    @Override
    public int hashCode() {
        return Objects.hash(questionId, source, target, wordDiff);
    }

    @Override
    public String toString() {
        return "QuestionParseImpl{" +
                "questionId=" + questionId +
                ", source=" + source +
                ", target=" + target +
                ", wordDiff=" + wordDiff +
                '}';
    }
}
