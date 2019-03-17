package com.prodigy.api.questions.controller;


import com.prodigy.api.questions.service.AddQuestionRequest;
import com.prodigy.api.questions.service.Question;
import com.prodigy.api.questions.service.QuestionCommandFactory;
import com.prodigy.api.questions.service.QuestionService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * Created by guym on 17/05/2017.
 */
@RestController("/questions")
public class QuestionController {

    private final QuestionService questionService;
    private final QuestionCommandFactory questionCommandFactory;

    public QuestionController(QuestionService questionService, QuestionCommandFactory questionCommandFactory) {
        this.questionService = questionService;
        this.questionCommandFactory = questionCommandFactory;
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
    public List<Question> getAll() {
        return questionService.getAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    public Question add(@RequestBody AddQuestionRequest request) {
        return questionCommandFactory.createAdd().doExecute(request);
    }

}
