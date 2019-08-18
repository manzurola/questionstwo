package com.prodigy.core;

import com.prodigy.core.nlp.POS;

import java.util.Objects;

public class Word {

    private final String value;
    private final String original;
    private final int index;
    private final POS posTag;

    private Word(Builder builder) {
        this.value = builder.value;
        this.original = builder.original;
        this.index = builder.index;
        this.posTag = builder.posTag;
    }

    public String value() {
        return value;
    }

    public String original() {
        return original;
    }

    public int index() {
        return index;
    }

    public POS posTag() {
        return posTag;
    }

    public static Word of(String value) {
        return newBuilder().value(value).build();
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Word word = (Word) o;
        return posTag == word.posTag;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), posTag);
    }

    @Override
    public String toString() {
        return "Word{" +
                "posTag=" + posTag +
                "} " + super.toString();
    }

    public static class Builder {
        private String value;
        private String original;
        private int index;
        private POS posTag;

        public Builder value(String value) {
            this.value = value;
            return this;
        }

        public Builder original(String original) {
            this.original = original;
            return this;
        }

        public Builder index(int index) {
            this.index = index;
            return this;
        }

        public Builder posTag(POS posTag) {
            this.posTag = posTag;
            return this;
        }

        public Word build() {
            return new Word(this);
        }
    }

}
