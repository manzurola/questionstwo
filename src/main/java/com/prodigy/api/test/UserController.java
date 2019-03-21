package com.prodigy.api.test;


import com.prodigy.api.test.common.Id;
import com.prodigy.api.test.common.service.Result;
import com.prodigy.api.test.common.service.ServiceExecutor;
import com.prodigy.api.test.users.User;
import com.prodigy.api.test.users.command.AddUserCommand;
import com.prodigy.api.test.users.request.AddUserRequest;
import com.prodigy.api.test.users.request.GetUserRequest;
import com.prodigy.api.test.users.command.GetUserCommand;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


/**
 * Created by guym on 17/05/2017.
 */
@RestController
@RequestMapping("/users")
public class UserController {

    private final ServiceExecutor serviceExecutor;

    public UserController(ServiceExecutor serviceExecutor) {
        this.serviceExecutor = serviceExecutor;
    }

    @RequestMapping(method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public User addUser(@RequestBody AddUserRequest request) {
        Result<User> result = serviceExecutor.execute(AddUserCommand.class, request);
        return result.payload();
    }

    @RequestMapping(value = "{userId}", method = RequestMethod.GET)
    public User getUser(@PathVariable Id<User> userId) {
        return serviceExecutor.execute(GetUserCommand.class, new GetUserRequest(userId)).payload();
    }

}
