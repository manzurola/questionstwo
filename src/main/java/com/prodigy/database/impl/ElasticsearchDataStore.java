package com.prodigy.common.data;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.prodigy.domain.Id;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ElasticsearchDataStore implements DataStore {

    private final TransportClient client;
    private final ObjectMapper mapper;

    public ElasticsearchDataStore(TransportClient client, ObjectMapper mapper) {
        this.client = client;
        this.mapper = mapper;
    }

    @Override
    public <T> T get(String index, String type, Id<T> id, Class<T> clazz) {
        try {
            GetResponse response = client.prepareGet(
                    index,
                    type,
                    id.getId()
            ).get();
            return mapper.readValue(response.getSourceAsString(), clazz);
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
            IndexResponse indexResponse = requestBuilder.setSource(mapper.writeValueAsString(data)).get();
            RestStatus status = indexResponse.status();
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

    @Override
    public <T> List<T> getByProperty(String index, String type, Class<T> clazz, String propName, Object propValue) {
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(QueryBuilders.matchQuery(propName, propValue));

        SearchResponse response = client.prepareSearch(index)
                .setTypes(type)
                .setSource(sourceBuilder)
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

}
