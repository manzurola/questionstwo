package com.prodigy.domain.users.service;

import com.prodigy.service.AbstractServiceCommand;
import com.prodigy.domain.users.database.UserRepository;
import com.prodigy.domain.users.User;

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
