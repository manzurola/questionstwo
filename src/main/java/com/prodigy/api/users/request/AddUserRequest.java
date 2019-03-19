package com.prodigy.api.users.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.prodigy.api.common.service.ServiceRequest;

public class AddUserRequest implements ServiceRequest {

    private String email;

    @JsonCreator
    public AddUserRequest(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
