package com.prodigy.domain.users.command;

import com.prodigy.domain.common.service.AbstractCommand;
import com.prodigy.domain.users.data.UserRepository;
import com.prodigy.domain.users.request.AddUserRequest;
import com.prodigy.domain.users.User;

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
