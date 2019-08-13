package com.prodigy.core;

import java.util.Objects;


public class Word {

    private final String value;
    private final POS pos;
    private final String originalValue;

    public Word(String value, POS pos, String originalValue) {
        this.pos = pos;
        this.value = value;
        this.originalValue = originalValue;
    }

    public String value() {
        return value;
    }

    public POS pos() {
        return pos;
    }

    public String original() {
        return originalValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Word word = (Word) o;
        return Objects.equals(value, word.value) &&
                pos == word.pos &&
                Objects.equals(originalValue, word.originalValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, pos, originalValue);
    }

    @Override
    public String toString() {
        return "Word{" +
                "value='" + value + '\'' +
                ", pos=" + pos +
                ", original='" + originalValue + '\'' +
                '}';
    }
}
