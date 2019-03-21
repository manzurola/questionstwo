package com.prodigy.api.test.common;

import java.util.List;

public interface ElasticsearchDataStore {

    <T> T get(String index, String type, Id<T> id, Class<T> clazz);

    <T> List<T> getAll(String index, String type, Class<T> clazz);

    <T> void add(String index, String type, Id<T> id, T data);

    <T> void update(String index, String type, Id<T> id, T data);

    <T> void delete(String index, String type, Id<T> id, Class<T> clazz);
}
