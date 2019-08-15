package com.prodigy.core;

import java.util.Objects;


public class Word {

    private final String value;
    private final String originalValue;

    public Word(String value, String originalValue) {
        this.value = value;
        this.originalValue = originalValue;
    }

    public Word(String value) {
        this(value, value);
    }

    public String value() {
        return value;
    }

    public String originalValue() {
        return originalValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Word word = (Word) o;
        return Objects.equals(value, word.value) &&
                Objects.equals(originalValue, word.originalValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, originalValue);
    }

    @Override
    public String toString() {
        return "Word{" +
                "value='" + value + '\'' +
                ", original='" + originalValue + '\'' +
                '}';
    }
}
