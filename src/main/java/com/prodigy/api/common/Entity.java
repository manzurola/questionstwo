package com.prodigy.api.common;

import java.time.LocalDateTime;

public class Entity<T> {

    private String id;
    private LocalDateTime creationTime;
    private LocalDateTime lastUpdateTime;

    public Entity(String id, LocalDateTime creationTime, LocalDateTime lastUpdateTime) {
        this.id = id;
        this.creationTime = creationTime;
        this.lastUpdateTime = lastUpdateTime;
    }

    public Entity() {
    }

    public String getId() {
        return id;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public LocalDateTime getLastUpdateTime() {
        return lastUpdateTime;
    }
}
