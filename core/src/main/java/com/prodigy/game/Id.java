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

    public final boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && o instanceof Id) {
            Id guid1 = (Id) o;
            if (this.id != null) {
                if (!this.id.equals(guid1.id)) {
                    return false;
                }
            } else if (guid1.id != null) {
                return false;
            }

            return true;
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