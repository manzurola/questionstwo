package com.prodigy.webapp.api;

import com.prodigy.application.command.CommandProcessor;
import com.prodigy.webapp.Answer;
import com.prodigy.webapp.AnswerAPI;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/answers")
public class AnswerController implements AnswerAPI {

    CommandProcessor processor;

    @Override
    @PostMapping("/")
    public Answer addAnswer(@RequestBody Answer request) {
//        processor.process(new AddAnswerCommand(request.toDomain()));
//        com.prodigy.domain.Answer answer = request.setId(id).toDomain(answerFactory);
//        com.prodigy.domain.Answer submitted = submitAnswer.submitAnswer(answer);
//        return new Answer(submitted);
        return null;
    }
}
