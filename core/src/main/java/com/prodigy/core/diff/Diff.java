package com.prodigy.core.diff;

import java.util.Objects;

public class Diff<T> {

    public enum Operation {
        INSERT,
        EQUAL,
        DELETE
    }

    private final Operation operation;
    private final T element;

    public Diff(Operation operation, T element) {
        this.operation = operation;
        this.element = element;
    }

    public Operation getOperation() {
        return operation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Diff)) return false;
        Diff<?> diff = (Diff<?>) o;
        return operation == diff.operation &&
                Objects.equals(element, diff.element);
    }

    @Override
    public int hashCode() {
        return Objects.hash(operation, element);
    }

    @Override
    public String toString() {
        return "Diff{" +
                "operation=" + operation +
                ", element=" + element +
                '}';
    }
}
