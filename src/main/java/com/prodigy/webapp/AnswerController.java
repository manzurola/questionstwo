package com.prodigy.webapp;

import com.prodigy.domain.answer.AnswerFactory;
import com.prodigy.domain.answer.SubmitAnswerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/answers")
public class AnswerController implements AnswerAPI {

    AnswerFactory answerFactory;
    SubmitAnswerService submitAnswer;

    @Override
    @PostMapping("/")
    public Answer submitAnswer(@RequestBody Answer request) {
        com.prodigy.domain.answer.Answer answer = request.setId(id).toDomain(answerFactory);
        com.prodigy.domain.answer.Answer submitted = submitAnswer.submitAnswer(answer);
        return new Answer(submitted);
    }
}
