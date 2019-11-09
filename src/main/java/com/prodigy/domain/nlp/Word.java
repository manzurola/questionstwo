package com.prodigy.domain.nlp;

import java.util.Objects;

public class Word {

    private final Token token;
    private final POS posTag;

    public Word(Token token, POS posTag) {
        this.token = token;
        this.posTag = posTag;
    }

    public Token token() {
        return token;
    }

    public String value() {
        return token.value();
    }

    public String original() {
        return token.original();
    }

    public int index() {
        return token.index();
    }

    public POS posTag() {
        return posTag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Word word = (Word) o;
        return Objects.equals(token, word.token) &&
                posTag == word.posTag;
    }

    @Override
    public int hashCode() {
        return Objects.hash(token, posTag);
    }

    @Override
    public String toString() {
        return "Word{" +
                "token=" + token +
                ", posTag=" + posTag +
                '}';
    }
}
