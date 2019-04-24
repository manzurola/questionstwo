package com.prodigy.nlp.diff;

import java.util.Objects;

public class TextDiff {

    public enum Operation {
        INSERT,
        EQUAL,
        DELETE
    }

    private final String text;
    private final Operation operation;

    public TextDiff(String text, Operation operation) {
        this.text = text;
        this.operation = operation;
    }

    public TextDiff(TextDiff other) {
        this(other.getText(), other.getOperation());
    }

    public String getText() {
        return text;
    }

    public Operation getOperation() {
        return operation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TextDiff)) return false;
        TextDiff textDiff = (TextDiff) o;
        return Objects.equals(text, textDiff.text) &&
                operation == textDiff.operation;
    }

    @Override
    public int hashCode() {
        return Objects.hash(text, operation);
    }

    @Override
    public String toString() {
        return String.format("[%s -> %s]", operation, text);
    }
}
