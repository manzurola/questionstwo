package com.prodigy.domain;

import java.io.Serializable;
import java.util.UUID;

public class Id<T> implements Serializable, Comparable<Id<T>> {
    private final String id;

    public Id(String id) {
        this.id = id;
    }

    public static <R> Id<R> of(String id) {
        return new Id<>(id);
    }

    public static <R> Id<R> next() {
        return of(UUID.randomUUID().toString());
    }

    public String getId() {
        return id;
    }

    public final boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (other instanceof Id) {
            Id otherId = (Id) other;
            if (this.id != null) {
                return this.id.equals(otherId.id);
            } else return otherId.id == null;
        } else {
            return false;
        }
    }

    public int hashCode() {
        return this.id != null ? this.id.hashCode() : 0;
    }

    public int compareTo(Id<T> o) {
        return this.id.compareTo(o.id);
    }

    public String toString() {
        return this.id;
    }

}