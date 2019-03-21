package com.prodigy.api.test;


import com.prodigy.api.test.answers.Answer;
import com.prodigy.api.test.answers.SubmitAnswerCommand;
import com.prodigy.api.test.answers.SubmitAnswerRequest;
import com.prodigy.api.test.common.service.Result;
import com.prodigy.api.test.common.service.ServiceExecutor;
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

}
