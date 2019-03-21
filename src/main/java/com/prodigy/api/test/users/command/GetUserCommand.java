package com.prodigy.api.test.users.command;

import com.prodigy.api.test.common.service.AbstractCommand;
import com.prodigy.api.test.users.User;
import com.prodigy.api.test.users.data.UserRepository;
import com.prodigy.api.test.users.request.GetUserRequest;
import org.springframework.stereotype.Component;

@Component
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
