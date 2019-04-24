package com.prodigy.nlp.diff;

import com.prodigy.nlp.TaggedWord;

import java.util.Objects;

public class WordDiff {

    private final TaggedWord word;
    private final TextDiff diff;

    public WordDiff(TextDiff diff, TaggedWord word) {
        this.word = word;
        this.diff = diff;
    }

    public TaggedWord target() {
        return word;
    }

    public TextDiff diff() {
        return diff;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WordDiff)) return false;
        WordDiff wordDiff = (WordDiff) o;
        return Objects.equals(word, wordDiff.word) &&
                Objects.equals(diff, wordDiff.diff);
    }

    @Override
    public int hashCode() {
        return Objects.hash(word, diff);
    }

    @Override
    public String toString() {
        return "WordDiff{" +
                "word=" + word +
                ", diff=" + diff +
                '}';
    }
}
