package com.prodigy.users.database;

import com.prodigy.common.data.Id;
import com.prodigy.users.User;

public interface UserRepository {

    User add(User user) throws Exception;

    User get(Id<User> id) throws Exception;
}
