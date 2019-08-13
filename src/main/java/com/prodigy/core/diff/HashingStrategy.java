package com.prodigy.core.diff;

public interface HashingStrategy<T> {
    int hashCode(T object);
}
