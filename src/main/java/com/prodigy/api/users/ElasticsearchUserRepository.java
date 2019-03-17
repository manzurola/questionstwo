package com.prodigy.api.users;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentType;

public class ElasticsearchUserRepository implements UserRepository {

    private final String index = "users";
    private final String mappedType = "user";
    private final TransportClient client;
    private final ObjectMapper mapper;


    public ElasticsearchUserRepository(TransportClient client, ObjectMapper mapper) {
        this.client = client;
        this.mapper = mapper;
    }

    @Override
    public void add(User user) throws Exception {
        client.prepareIndex(index, mappedType, user.getId())
                .setSource(mapper.writeValueAsBytes(user), XContentType.JSON)
                .get();
    }

    @Override
    public User get(String id) throws Exception {
        return mapper.readValue(client.prepareGet(index, mappedType, id).get().getSourceAsBytes(), User.class);
    }
}
