package com.prodigy.application.query;

import com.google.common.collect.ImmutableMap;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DefaultQueryHandlerProvider implements QueryHandlerProvider {

    private final Map<Class, QueryHandler> handlers;

    private DefaultQueryHandlerProvider(Builder builder) {
        this.handlers = ImmutableMap.<Class, QueryHandler>builder().putAll(builder.handlers).build();
    }

    @Override
    public <QUERY extends Query<RESULT>, RESULT> QueryHandler<QUERY, RESULT> getHandlerForQuery(Query<RESULT> query) {
        return (QueryHandler<QUERY, RESULT>) handlers.get(query);
    }

    public static class Builder {
        private final Map<Class, QueryHandler> handlers = new ConcurrentHashMap<>();

        public <QUERY extends Query<RESULT>, RESULT> Builder register(Class<QUERY> resultType, QueryHandler<QUERY, RESULT> handler) {
            handlers.put(resultType, handler);
            return this;
        }

        public DefaultQueryHandlerProvider build() {
            return new DefaultQueryHandlerProvider(this);
        }

    }
}
