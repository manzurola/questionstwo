package com.prodigy.api;

import com.prodigy.api.env.EndToEndTest;
import com.prodigy.api.users.User;
import com.prodigy.api.users.request.AddUserRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

public class UserControllerTest extends EndToEndTest {

    @Test
    public void addUser() {
        AddUserRequest request = new AddUserRequest("guym@guy.com");

        ResponseEntity<User> response = template.exchange(
                baseUrl.toString() + "/users",
                HttpMethod.POST,
                new HttpEntity<>(request),
                new ParameterizedTypeReference<User>() {
                });

        User actual = response.getBody();

        User expected = User.builder()
                .setId(actual.getId())
                .setEmail(request.getEmail())
                .build();

        assertEquals(actual, expected);
    }
}