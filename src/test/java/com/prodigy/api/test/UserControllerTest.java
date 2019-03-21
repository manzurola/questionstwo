package com.prodigy.api.test;

import com.prodigy.api.test.env.EndToEndTest;
import com.prodigy.api.test.test.AddUserApiCall;
import com.prodigy.api.test.users.User;
import com.prodigy.api.test.users.request.AddUserRequest;
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

        assertEquals(actual, expected);
    }
}