package com.prodigy.common.data;

import java.util.List;

public interface DataStore {

    <T> T get(String index, String type, Id<T> id, Class<T> clazz);

    <T> List<T> getAll(String index, String type, Class<T> clazz);

    <T> void add(String index, String type, Id<T> id, T data);

    <T> void update(String index, String type, Id<T> id, T data);

    <T> void delete(String index, String type, Id<T> id, Class<T> clazz);

    <T> List<T> getByProperty(String index, String type, Class<T> clazz, String propName, Object propValue);
}
