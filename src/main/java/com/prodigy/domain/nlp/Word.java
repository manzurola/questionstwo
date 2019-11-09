package com.prodigy.domain.nlp;

import java.util.Objects;

public final class Word {

    private final Token token;
    private final POS posTag;

    public Word(Token token, POS posTag) {
        this.token = token;
        this.posTag = posTag;
    }

    final Token token() {
        return token;
    }

    public final String value() {
        return token.value();
    }

    public final String original() {
        return token.original();
    }

    public final int index() {
        return token.index();
    }

    public final POS posTag() {
        return posTag;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Word word = (Word) o;
        return Objects.equals(token, word.token) &&
                posTag == word.posTag;
    }

    @Override
    public final int hashCode() {
        return Objects.hash(token, posTag);
    }

    @Override
    public final String toString() {
        return "Word{" +
                "token=" + token +
                ", posTag=" + posTag +
                '}';
    }
}
