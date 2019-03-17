package com.prodigy.api.common;

import java.util.UUID;

public abstract class EntityFactory {

    public String nextId() {
        return UUID.randomUUID().toString();
    }
}
