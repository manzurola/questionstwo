package com.prodigy.domain.users.database;

import com.prodigy.domain.Id;
import com.prodigy.domain.users.User;

public interface UserRepository {

    User add(User user) throws Exception;

    User get(Id<User> id) throws Exception;
}
