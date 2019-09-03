package com.prodigy.domain.users.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.prodigy.domain.common.service.ServiceRequest;

import javax.validation.constraints.Email;

public class AddUserRequest implements ServiceRequest {

    @Email
    private String email;

    @JsonCreator // jackson backwards comp requirement for single arg constructor
    public AddUserRequest(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
