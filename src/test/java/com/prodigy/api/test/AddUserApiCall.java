package com.prodigy.api.test;

import com.prodigy.api.users.User;
import com.prodigy.api.users.request.AddUserRequest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.net.URL;

public class AddUserApiCall implements ApiCall<User, AddUserRequest> {

    @Override
    public ResponseEntity<User> run(AddUserRequest request, TestRestTemplate template, URL baseUrl) {
        return template.exchange(
                baseUrl.toString() + "/users",
                HttpMethod.POST,
                new HttpEntity<>(request),
                new ParameterizedTypeReference<User>() {
                });
    }
}