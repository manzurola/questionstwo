package com.prodigy.domain.nlp;

import java.util.Objects;

public class Token {

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

    public String value() {
        return value;
    }

    public String original() {
        return originalValue;
    }

    public String before() {
        return before;
    }

    public String after() {
        return after;
    }

    public int index() {
        return index;
    }

    public boolean hasAfter() {
        return !after.isEmpty();
    }

    public boolean hasBefore() {
        return !before.isEmpty();
    }

    public Token clearAfter() {
        return new Token(value, before, "", index);
    }

    public Token setAfter(String after) {
        return new Token(value, before, after, index);
    }

    public Token setBefore(String before) {
        return new Token(value, before, after, index);
    }

    public Token clearBefore() {
        return new Token(value, "", after, index);
    }

    public Token setValue(String value) {
        return new Token(value, before, after, index);
    }

    @Override
    public boolean equals(Object o) {
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
    public int hashCode() {
        return Objects.hash(value, originalValue, before, after, index);
    }

    @Override
    public String toString() {
        return "Token{" +
                "value='" + value + '\'' +
                ", originalValue='" + originalValue + '\'' +
                ", before='" + before + '\'' +
                ", after='" + after + '\'' +
                ", index=" + index +
                '}';
    }
}
