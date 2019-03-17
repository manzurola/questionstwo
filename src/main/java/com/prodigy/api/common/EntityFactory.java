package com.prodigy.api.common;

import java.util.UUID;

public interface EntityFactory<T> {

    default String nextId() {
        return UUID.randomUUID().toString();
    }

    T build();
}
