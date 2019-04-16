package com.prodigy.nlp.diff;

import com.prodigy.nlp.TaggedWord;

import java.util.Objects;

public class WordDiff {

    private final TaggedWord target;
    private final TextDiff diff;

    public WordDiff(TextDiff diff, TaggedWord target) {
        this.target = target;
        this.diff = diff;
    }

    public TaggedWord target() {
        return target;
    }

    public TextDiff diff() {
        return diff;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WordDiff)) return false;
        WordDiff wordDiff = (WordDiff) o;
        return Objects.equals(target, wordDiff.target) &&
                Objects.equals(diff, wordDiff.diff);
    }

    @Override
    public int hashCode() {
        return Objects.hash(target, diff);
    }

    @Override
    public String toString() {
        return "WordDiff{" +
                "target=" + target +
                ", diff=" + diff +
                '}';
    }
}
