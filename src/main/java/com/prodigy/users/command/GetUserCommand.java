package com.prodigy.users.command;

import com.prodigy.common.service.AbstractCommand;
import com.prodigy.users.User;
import com.prodigy.users.data.UserRepository;
import com.prodigy.users.request.GetUserRequest;

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
