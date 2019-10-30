package com.prodigy.database.impl;

import com.prodigy.common.Id;

import java.util.List;

public class InMemoryDataStore implements DataStore {
    @Override
    public <T> T get(String index, String type, Id<T> id, Class<T> clazz) {
        return null;
    }

    @Override
    public <T> List<T> getAll(String index, String type, Class<T> clazz) {
        return null;
    }

    @Override
    public <T> void add(String index, String type, Id<T> id, T data) {

    }

    @Override
    public <T> void update(String index, String type, Id<T> id, T data) {

    }

    @Override
    public <T> void delete(String index, String type, Id<T> id, Class<T> clazz) {

    }

    @Override
    public <T> List<T> getByProperty(String index, String type, Class<T> clazz, String propName, Object propValue) {
        return null;
    }
}
