package com.prodigy.api;


import com.prodigy.api.answers.Answer;
import com.prodigy.api.answers.SubmitAnswerCommand;
import com.prodigy.api.answers.SubmitAnswerRequest;
import com.prodigy.api.common.service.ServiceExecutor;
import com.prodigy.api.questions.Question;
import com.prodigy.api.questions.command.GetQuestionCommand;
import com.prodigy.api.questions.request.GetQuestionRequest;
import com.prodigy.api.review.command.AddReviewCommand;
import com.prodigy.api.review.request.AddReviewRequest;
import com.prodigy.api.review.command.SuggestReviewCommand;
import com.prodigy.api.review.request.SuggestReviewRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


/**
 * Created by guym on 17/05/2017.
 */
@RestController
public class AnswerController {

    private final ServiceExecutor serviceExecutor;

    public AnswerController(ServiceExecutor serviceExecutor) {
        this.serviceExecutor = serviceExecutor;
    }

    @PostMapping("/answers")
    public Answer submitAnswer(@RequestBody SubmitAnswerRequest request) {
        Answer answer = serviceExecutor.execute(
                SubmitAnswerCommand.class,
                request
        ).payload();
        Question question = serviceExecutor.execute(
                GetQuestionCommand.class,
                new GetQuestionRequest(answer.getQuestionId())
        ).payload();
        AddReviewRequest addReviewRequest = serviceExecutor.execute(
                SuggestReviewCommand.class,
                new SuggestReviewRequest(question, answer)
        ).payload();
        serviceExecutor.execute(
                AddReviewCommand.class,
                addReviewRequest
        );
        return answer;
    }

}