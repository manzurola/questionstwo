package com.prodigy.domain.users.command;

import com.prodigy.domain.common.service.AbstractCommand;
import com.prodigy.domain.users.User;
import com.prodigy.domain.users.data.UserRepository;
import com.prodigy.domain.users.request.GetUserRequest;

public class GetUserCommand extends AbstractCommand<User, GetUserRequest> {

    private final UserRepository repository;

    public GetUserCommand(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    protected User doExecute(GetUserRequest request) throws Exception {
        return repository.get(request.getId());
    }
}
