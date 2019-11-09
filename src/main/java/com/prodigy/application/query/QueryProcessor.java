package com.prodigy.application.query;

public interface QueryProcessor {

    <T> T process(Query<T> query);
}
