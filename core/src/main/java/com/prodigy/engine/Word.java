package com.prodigy.engine;

import java.util.Objects;


public class Word {

    private final String value;
    private final POS pos;

    public Word(String value, POS pos) {
        this.pos = pos;
        this.value = value;
    }

    public String value() {
        return value;
    }

    public POS pos() {
        return pos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Word word = (Word) o;
        return Objects.equals(value, word.value) &&
                pos == word.pos;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, pos);
    }

    @Override
    public String toString() {
        return "Word{" +
                "value='" + value + '\'' +
                ", pos=" + pos +
                '}';
    }
}
