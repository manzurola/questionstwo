package com.prodigy.api.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.CaseFormat;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentType;

import java.io.IOException;

public class ElasticsearchDataStore implements DataStore {

    private final TransportClient client;
    private final ObjectMapper mapper;
    private final Mappings mappings;

    public ElasticsearchDataStore(TransportClient client, ObjectMapper mapper, Mappings mappings) {
        this.client = client;
        this.mapper = mapper;
        this.mappings = mappings;
    }

    @Override
    public <T extends Entity<T>> T get(Id<T> id, Class<T> clazz) {
        byte[] sourceAsBytes = client.prepareGet(
                mappings.indexOf(clazz),
                mappings.mappedTypeOf(clazz),
                id.getId()
        ).get().getSourceAsBytes();
        try {
            return mapper.readValue(sourceAsBytes, clazz);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T extends Entity<T>> void add(T data) {
        IndexRequestBuilder requestBuilder = client.prepareIndex(
                mappings.indexOf(data.getClass()),
                mappings.mappedTypeOf(data.getClass())
        );
        try {
            requestBuilder.setSource(mapper.writeValueAsBytes(data), XContentType.JSON).get();
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to add data", e);
        }
    }

    @Override
    public <T extends Entity<T>> void update(Id<T> id, T data) {
        client.prepareUpdate(mappings.indexOf(data.getClass()), mappings.mappedTypeOf(data.getClass()), id.getId()).get();
    }

    @Override
    public <T extends Entity<T>> void delete(Id<T> id, Class<T> clazz) {
        client.prepareDelete(mappings.indexOf(clazz), mappings.mappedTypeOf(clazz), id.getId()).get();
    }

    public static class Mappings {

        public <T> String mappedTypeOf(Class<T> clazz) {
            return CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, clazz.getSimpleName());

        }

        public <T> String indexOf(Class<T> clazz) {
            return CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, clazz.getSimpleName());

        }
    }
}
