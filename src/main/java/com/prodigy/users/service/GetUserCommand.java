package com.prodigy.users.service;

import com.prodigy.common.service.AbstractServiceCommand;
import com.prodigy.users.User;
import com.prodigy.users.database.UserRepository;

public class GetUserCommand extends AbstractServiceCommand<User, GetUserRequest> {

    private final UserRepository repository;

    public GetUserCommand(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    protected User doExecute(GetUserRequest request) throws Exception {
        return repository.get(request.getId());
    }
}
