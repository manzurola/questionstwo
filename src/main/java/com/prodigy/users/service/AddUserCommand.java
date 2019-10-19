package com.prodigy.users.service;

import com.prodigy.common.service.AbstractServiceCommand;
import com.prodigy.users.database.UserRepository;
import com.prodigy.users.User;

public class AddUserCommand extends AbstractServiceCommand<User, AddUserRequest> {

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
