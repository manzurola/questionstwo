package com.prodigy.core.grammar;

import com.prodigy.core.Word;

import java.util.Objects;

public class IndexedWord {

    private final int index;
    private final Word word;

    public IndexedWord(int index, Word word) {
        this.index = index;
        this.word = word;
    }

    public int index() {
        return index;
    }

    public Word word() {
        return word;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof IndexedWord)) return false;
        IndexedWord that = (IndexedWord) o;
        return index == that.index &&
                Objects.equals(word, that.word);
    }

    @Override
    public int hashCode() {
        return Objects.hash(index, word);
    }

    @Override
    public String toString() {
        return "IndexedWord{" +
                "index=" + index +
                ", word=" + word +
                '}';
    }
}
