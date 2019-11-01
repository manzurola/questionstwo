package com.prodigy.domain.users.service;

import com.prodigy.service.AbstractServiceCommand;
import com.prodigy.domain.users.User;
import com.prodigy.domain.users.database.UserRepository;

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
