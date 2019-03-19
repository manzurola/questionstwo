package com.prodigy.api;


import com.prodigy.api.answers.Answer;
import com.prodigy.api.answers.SubmitAnswerCommand;
import com.prodigy.api.answers.SubmitAnswerRequest;
import com.prodigy.api.common.Id;
import com.prodigy.api.common.service.Result;
import com.prodigy.api.common.service.ServiceExecutor;
import com.prodigy.api.users.User;
import com.prodigy.api.users.command.GetUserCommand;
import com.prodigy.api.users.request.GetUserRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


/**
 * Created by guym on 17/05/2017.
 */
@RestController
@RequestMapping("/answers")
public class AnswerController {

    private final ServiceExecutor serviceExecutor;

    public AnswerController(ServiceExecutor serviceExecutor) {
        this.serviceExecutor = serviceExecutor;
    }

    @RequestMapping(method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public Answer submitAnswer(@RequestBody SubmitAnswerRequest request) {
        Result<Answer> result = serviceExecutor.execute(SubmitAnswerCommand.class, request);
        return result.payload();
    }


    @RequestMapping(value = "{userId}", method = RequestMethod.GET)
    public User getUser(@PathVariable Id<User> userId) {
        return serviceExecutor.execute(GetUserCommand.class, new GetUserRequest(userId)).payload();
    }

}
