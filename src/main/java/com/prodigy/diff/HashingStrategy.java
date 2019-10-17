package com.prodigy.diff;

public interface HashingStrategy<T> {
    int hashCode(T object);
}
