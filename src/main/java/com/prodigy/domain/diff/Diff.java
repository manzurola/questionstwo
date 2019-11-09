package com.prodigy.domain.diff;

import java.util.Objects;

public class Diff<T> {

    private final Operation operation;
    private final T item;

    public Diff(Operation operation, T item) {
        this.operation = operation;
        this.item = item;
    }

    public Operation operation() {
        return operation;
    }

    public T item() {
        return item;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Diff)) return false;
        Diff<?> diff = (Diff<?>) o;
        return operation == diff.operation &&
                Objects.equals(item, diff.item);
    }

    @Override
    public int hashCode() {
        return Objects.hash(operation, item);
    }

    @Override
    public String toString() {
        return "Diff{" +
                "operation=" + operation +
                ", element=" + item +
                '}';
    }
}
