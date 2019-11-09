package com.prodigy.domain.diff;

public interface HashingStrategy<T> {
    int hashCode(T object);
}
