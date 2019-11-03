package com.prodigy.webapp;

import com.prodigy.domain.AnswerFactory;
import com.prodigy.service.SubmitAnswerService;
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
//        com.prodigy.domain.Answer answer = request.setId(id).toDomain(answerFactory);
//        com.prodigy.domain.Answer submitted = submitAnswer.submitAnswer(answer);
//        return new Answer(submitted);
        return null;
    }
}
