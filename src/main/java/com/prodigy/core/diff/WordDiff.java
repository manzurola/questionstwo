package com.prodigy.core.diff;

import com.prodigy.core.Word;

import java.util.Objects;

public class WordDiff {

    public enum Operation {
        INSERT,
        EQUAL,
        DELETE
    }

    private final Operation operation;
    private final Word word;

    public WordDiff(Operation operation, Word word) {
        this.operation = operation;
        this.word = word;
    }

    public Operation operation() {
        return operation;
    }

    public Word word() {
        return word;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WordDiff wordDiff = (WordDiff) o;
        return operation == wordDiff.operation &&
                Objects.equals(word, wordDiff.word);
    }

    @Override
    public int hashCode() {
        return Objects.hash(operation, word);
    }

    @Override
    public String toString() {
        return "WordDiff{" +
                "operation=" + operation +
                ", word=" + word +
                '}';
    }
}
