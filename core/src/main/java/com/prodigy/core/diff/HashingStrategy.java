package com.prodigy.core.diff;

public interface HashingStrategy<T> {
    int hashCode(T object);
    boolean equals(T object1, T object2);
}
