package com.prodigy.users.command;

import com.prodigy.common.service.AbstractCommand;
import com.prodigy.users.data.UserRepository;
import com.prodigy.users.request.AddUserRequest;
import com.prodigy.users.User;

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
