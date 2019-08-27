package com.prodigy.domain.users.data;

import com.prodigy.domain.common.Id;
import com.prodigy.domain.users.User;

public interface UserRepository {

    User add(User user) throws Exception;

    User get(Id<User> id) throws Exception;
}
