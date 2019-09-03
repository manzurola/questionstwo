package com.prodigy.api;

import com.prodigy.api.calls.AddUserApiCall;
import com.prodigy.api.env.EndToEndTest;
import com.prodigy.domain.users.User;
import com.prodigy.domain.users.request.AddUserRequest;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;

public class UserControllerTest extends EndToEndTest {

    @Test
    public void addUser() {
        AddUserRequest request = new AddUserRequest("guym@guy.com");

        ResponseEntity<User> response = new AddUserApiCall().run(request, template, baseUrl);

        User actual = response.getBody();

        User expected = User.builder()
                .setId(actual.getId())
                .setEmail(request.getEmail())
                .build();

        assertEquals(expected, actual);
    }
}