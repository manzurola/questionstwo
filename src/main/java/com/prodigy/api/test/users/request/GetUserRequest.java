package com.prodigy.api.test.users.request;

import com.prodigy.api.test.common.Id;
import com.prodigy.api.test.common.service.ServiceRequest;
import com.prodigy.api.test.users.User;

public class GetUserRequest implements ServiceRequest {

    private Id<User> id;

    public GetUserRequest(Id<User> id) {
        this.id = id;
    }

    public Id<User> getId() {
        return id;
    }
}
