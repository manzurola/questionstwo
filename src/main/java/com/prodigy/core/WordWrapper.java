package com.prodigy.core;

import java.util.Objects;

public class WordWrapper {
    private final edu.stanford.nlp.ling.Word word;

    public WordWrapper(edu.stanford.nlp.ling.Word word) {
        this.word = word;
    }

    public edu.stanford.nlp.ling.Word getWord() {
        return word;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WordWrapper that = (WordWrapper) o;
        return Objects.equals(word.word(), that.word.word());
    }

    @Override
    public int hashCode() {
        return Objects.hash(word.word());
    }

    @Override
    public String toString() {
        return "WordWrapper{" +
                "word=" + word +
                '}';
    }
}