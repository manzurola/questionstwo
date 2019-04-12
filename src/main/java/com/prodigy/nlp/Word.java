package com.prodigy.nlp;

import java.util.Objects;

public class Word {

    private final String value;

    public Word(String value) {
        this.value = value;
    }

    public String getWord() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Word)) return false;
        Word word = (Word) o;
        return Objects.equals(value, word.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
