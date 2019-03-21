package com.prodigy.api.test.users.data;

import com.prodigy.api.test.common.ElasticsearchDataStore;
import com.prodigy.api.test.common.Id;
import com.prodigy.api.test.users.User;

public class ElasticsearchUserRepository implements UserRepository {

    private final String index = "users";
    private final String mappedType = "user";
    private final ElasticsearchDataStore dataStore;


    public ElasticsearchUserRepository(ElasticsearchDataStore dataStore) {
        this.dataStore = dataStore;
    }

    @Override
    public User add(User user) throws Exception {
        dataStore.add(index, mappedType, user.getId(), user);
        return user;
    }

    @Override
    public User get(Id<User> id) throws Exception {
        return dataStore.get(index, mappedType, id, User.class);
    }
}
