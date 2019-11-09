package com.prodigy.domain.nlp;

import java.util.Objects;

public final class Token {

    private final String value;
    private final String originalValue;
    private final String before;
    private final String after;
    private final int index;

    public Token(String value, String before, String after, int index) {
        this.value = value;
        this.originalValue = value.concat(after);
        this.before = before;
        this.after = after;
        this.index = index;
    }

    public final String value() {
        return value;
    }

    public final Token setValue(String value) {
        return new Token(value, before, after, index);
    }

    public final String original() {
        return originalValue;
    }

    public final String before() {
        return before;
    }

    public final Token setBefore(String before) {
        return new Token(value, before, after, index);
    }

    public final String after() {
        return after;
    }

    public final Token setAfter(String after) {
        return new Token(value, before, after, index);
    }

    public final int index() {
        return index;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Token token = (Token) o;
        return index == token.index &&
                Objects.equals(value, token.value) &&
                Objects.equals(originalValue, token.originalValue) &&
                Objects.equals(before, token.before) &&
                Objects.equals(after, token.after);
    }

    @Override
    public final int hashCode() {
        return Objects.hash(value, originalValue, before, after, index);
    }

    @Override
    public final String toString() {
        return "Token{" +
                "value='" + value + '\'' +
                ", originalValue='" + originalValue + '\'' +
                ", before='" + before + '\'' +
                ", after='" + after + '\'' +
                ", index=" + index +
                '}';
    }
}
