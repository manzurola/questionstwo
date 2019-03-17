package com.prodigy.api.users;

public interface UserRepository {

    void add(User user) throws Exception;

    User get(String id) throws Exception;
}
