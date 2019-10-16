package com.prodigy.domain.users.data;

import com.prodigy.domain.common.DataStore;
import com.prodigy.domain.common.Id;
import com.prodigy.domain.users.User;

public class ElasticsearchUserRepository implements UserRepository {

    private final String index = "users";
    private final String mappedType = "user";
    private final DataStore dataStore;


    public ElasticsearchUserRepository(DataStore dataStore) {
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