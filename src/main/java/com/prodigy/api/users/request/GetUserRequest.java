package com.prodigy.api.users.request;

import com.prodigy.api.common.Id;
import com.prodigy.api.common.service.ServiceRequest;
import com.prodigy.api.users.User;

public class GetUserRequest implements ServiceRequest {

    private Id<User> id;

    public GetUserRequest(Id<User> id) {
        this.id = id;
    }

    public Id<User> getId() {
        return id;
    }
}
