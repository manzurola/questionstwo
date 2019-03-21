package com.prodigy.api.test;


import com.prodigy.api.test.answers.Answer;
import com.prodigy.api.test.answers.SubmitAnswerCommand;
import com.prodigy.api.test.answers.SubmitAnswerRequest;
import com.prodigy.api.test.common.service.Result;
import com.prodigy.api.test.common.service.ServiceExecutor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * Created by guym on 17/05/2017.
 */
@RestController
@RequestMapping("/review")
public class ReviewController {

    private final ServiceExecutor serviceExecutor;

    public ReviewController(ServiceExecutor serviceExecutor) {
        this.serviceExecutor = serviceExecutor;
    }

    @RequestMapping(method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public Answer getReview(@RequestBody SubmitAnswerRequest request) {
        Result<Answer> result = serviceExecutor.execute(SubmitAnswerCommand.class, request);
        return result.payload();
    }

}
