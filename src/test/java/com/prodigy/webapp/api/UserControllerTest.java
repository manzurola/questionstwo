package com.prodigy.webapp.api;

import com.prodigy.webapp.api.calls.AddUserApiCall;
import com.prodigy.webapp.api.env.EndToEndTest;
import com.prodigy.domain.users.User;
import com.prodigy.domain.users.service.AddUserRequest;
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