package com.prodigy.api;


import com.prodigy.api.common.service.Result;
import com.prodigy.api.common.service.ServiceExecutor;
import com.prodigy.api.questions.Question;
import com.prodigy.api.questions.command.AddQuestionCommand;
import com.prodigy.api.questions.command.GetAllQuestionsCommand;
import com.prodigy.api.questions.request.AddQuestionRequest;
import com.prodigy.api.questions.request.GetAllQuestionsRequest;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * Created by guym on 17/05/2017.
 */
@RestController
@RequestMapping("/questions")
public class QuestionController {

    private final ServiceExecutor serviceExecutor;

    public QuestionController(ServiceExecutor serviceExecutor) {
        this.serviceExecutor = serviceExecutor;
    }

//    @RequestMapping(method = RequestMethod.GET)
//    public List<Question> searchQuestions(@RequestParam("type") String type, @RequestParam("answer") String answer) throws Exception {
//        List<? extends Question> questions = questionService.searchByAnswer(answer);
//        List<Question> result = new ArrayList<>();
//        for (Question question : questions) {
//            result.add(question);
//        }
//        return result;
//    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Question> getAllQuestions() {
        Result<List<Question>> result = serviceExecutor.execute(GetAllQuestionsCommand.class, new GetAllQuestionsRequest());
        return result.payload();
    }

    @RequestMapping(method = RequestMethod.POST)
    public Question addQuestion(@RequestBody AddQuestionRequest requestBody) {
        Result<Question> result = serviceExecutor.execute(AddQuestionCommand.class, requestBody);
        return result.payload();
    }

}
