package com.prodigy.application.query;

public interface QueryHandler<QUERY, RESULT> {

    RESULT handle(QUERY query);
}
