package com.prodigy.api.test.users.command;

import com.prodigy.api.test.common.service.AbstractCommand;
import com.prodigy.api.test.users.data.UserRepository;
import com.prodigy.api.test.users.request.AddUserRequest;
import com.prodigy.api.test.users.User;
import org.springframework.stereotype.Component;

@Component
public class AddUserCommand extends AbstractCommand<User, AddUserRequest> {

    private final UserRepository repository;

    public AddUserCommand(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    protected User doExecute(AddUserRequest request) throws Exception {
        User user = User.builder()
                .setEmail(request.getEmail())
                .build();
        return repository.add(user);
    }
}
