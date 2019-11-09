package com.prodigy.application.query;

public interface QueryHandlerProvider {

    <QUERY extends Query<RESULT>, RESULT> QueryHandler<QUERY, RESULT> getHandlerForQuery(Query<RESULT> query);
}
