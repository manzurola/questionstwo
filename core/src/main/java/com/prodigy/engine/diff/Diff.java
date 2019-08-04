package com.prodigy.engine.diff;

import java.util.Objects;

public class Diff<T> {

    public enum Operation {
        INSERT,
        EQUAL,
        DELETE
    }

    private final Operation operation;
    private final T object;

    public Diff(Operation operation, T object) {
        this.operation = operation;
        this.object = object;
    }

    public Operation operation() {
        return operation;
    }

    public T object() {
        return object;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Diff)) return false;
        Diff<?> diff = (Diff<?>) o;
        return operation == diff.operation &&
                Objects.equals(object, diff.object);
    }

    @Override
    public int hashCode() {
        return Objects.hash(operation, object);
    }

    @Override
    public String toString() {
        return "Diff{" +
                "operation=" + operation +
                ", element=" + object +
                '}';
    }
}
