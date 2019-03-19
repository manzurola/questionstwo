package com.prodigy.api.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.CaseFormat;
import com.prodigy.api.questions.Question;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.search.SearchHit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ElasticsearchDataStoreImpl implements ElasticsearchDataStore {

    private final TransportClient client;
    private final ObjectMapper mapper;

    public ElasticsearchDataStoreImpl(TransportClient client, ObjectMapper mapper) {
        this.client = client;
        this.mapper = mapper;
    }

    @Override
    public <T> T get(String index, String type, Id<T> id, Class<T> clazz) {
        byte[] sourceAsBytes = client.prepareGet(
                index,
                type,
                id.getId()
        ).get().getSourceAsBytes();
        try {
            return mapper.readValue(sourceAsBytes, clazz);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> List<T> getAll(String index, String type, Class<T> clazz) {
        SearchResponse response = client.prepareSearch(index)
                .setTypes(type)
                .get();
        List<T> results = new ArrayList<>();
        for (SearchHit searchHit : response.getHits()) {
            try {
                results.add(mapper.readValue(searchHit.getSourceAsString(), clazz));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return results;
    }

    @Override
    public <T> void add(String index, String type, Id<T> id, T data) {
        IndexRequestBuilder requestBuilder = client.prepareIndex(
                index,
                type,
                id.getId()
        );
        try {
            requestBuilder.setSource(mapper.writeValueAsBytes(data), XContentType.JSON).get();
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to add data", e);
        }
    }

    @Override
    public <T> void update(String index, String type, Id<T> id, T data) {
        client.prepareUpdate(index, type, id.getId()).get();
    }

    @Override
    public <T> void delete(String index, String type, Id<T> id, Class<T> clazz) {
        client.prepareDelete(index, type, id.getId()).get();
    }

}
