package com.prodigy.web.api;


import com.prodigy.common.data.Id;
import com.prodigy.common.service.Result;
import com.prodigy.common.service.ServiceExecutor;
import com.prodigy.users.User;
import com.prodigy.users.service.AddUserCommand;
import com.prodigy.users.service.AddUserRequest;
import com.prodigy.users.service.GetUserRequest;
import com.prodigy.users.service.GetUserCommand;
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
        return result.getData();
    }

    @RequestMapping(value = "{userId}", method = RequestMethod.GET)
    public User getUser(@PathVariable Id<User> userId) {
        return serviceExecutor.execute(GetUserCommand.class, new GetUserRequest(userId)).getData();
    }

}
