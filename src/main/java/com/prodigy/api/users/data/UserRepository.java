package com.prodigy.api.users.data;

import com.prodigy.api.common.Id;
import com.prodigy.api.users.User;

public interface UserRepository {

    User add(User user) throws Exception;

    User get(Id<User> id) throws Exception;
}
