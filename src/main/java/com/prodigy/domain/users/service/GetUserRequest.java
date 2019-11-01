package com.prodigy.domain.users.service;

import com.prodigy.domain.Id;
import com.prodigy.service.ServiceRequest;
import com.prodigy.domain.users.User;

public class GetUserRequest implements ServiceRequest {

    private Id<User> id;

    public GetUserRequest(Id<User> id) {
        this.id = id;
    }

    public Id<User> getId() {
        return id;
    }
}
