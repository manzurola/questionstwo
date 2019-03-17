package com.prodigy.api.common;

public interface DataStore {

    <T extends Entity<T>> T get(Id<T> id, Class<T> clazz);

    <T extends Entity<T>> void add(T data);

    <T extends Entity<T>> void update(Id<T> id, T data);

    <T extends Entity<T>> void delete(Id<T> id, Class<T> clazz);
}
