package com.prodigy.nlp;

import java.util.Objects;

public class Word {

    private final String value;
    private final String originalValue;
    private final int index;
    private final POS posTag;

    public Word(String value, int index, POS posTag, String originalValue) {
        this.value = value;
        this.originalValue = originalValue;
        this.index = index;
        this.posTag = posTag;
    }

    public String value() {
        return value;
    }

    public String original() {
        return originalValue;
    }

    public int index() {
        return index;
    }

    public POS posTag() {
        return posTag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Word word = (Word) o;
        return index == word.index &&
                Objects.equals(value, word.value) &&
                Objects.equals(originalValue, word.originalValue) &&
                posTag == word.posTag;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, originalValue, index, posTag);
    }

    @Override
    public String toString() {
        return "Word{" +
                "value='" + value + '\'' +
                ", originalValue='" + originalValue + '\'' +
                ", index=" + index +
                ", posTag=" + posTag +
                '}';
    }
}
