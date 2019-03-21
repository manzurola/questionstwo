package com.prodigy.api.test.users.data;

import com.prodigy.api.test.common.Id;
import com.prodigy.api.test.users.User;

public interface UserRepository {

    User add(User user) throws Exception;

    User get(Id<User> id) throws Exception;
}
