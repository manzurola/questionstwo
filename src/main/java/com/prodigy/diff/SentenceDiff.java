package com.prodigy.diff;

import com.prodigy.nlp.Sentence;
import com.prodigy.nlp.Word;

import java.util.List;
import java.util.Objects;

public class SentenceDiff {

    private final Sentence source;
    private final Sentence target;
    private final List<Diff<Word>> words;

    public SentenceDiff(Sentence source, Sentence target, List<Diff<Word>> words) {
        this.source = source;
        this.target = target;
        this.words = words;
    }

    public Sentence source() {
        return source;
    }

    public Sentence target() {
        return target;
    }

    public List<Diff<Word>> words() {
        return words;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SentenceDiff that = (SentenceDiff) o;
        return Objects.equals(source, that.source) &&
                Objects.equals(target, that.target) &&
                Objects.equals(words, that.words);
    }

    @Override
    public int hashCode() {
        return Objects.hash(source, target, words);
    }

    @Override
    public String toString() {
        return "SentenceDiff{" +
                "source=" + source +
                ", target=" + target +
                ", words=" + words +
                '}';
    }
}

