package com.prodigy.api.questions.controller;


import com.prodigy.api.questions.domain.Question;
import com.prodigy.api.questions.data.QuestionRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * Created by guym on 17/05/2017.
 */
@RestController("/questions")
public class QuestionController {

    private final QuestionRepository questionRepository;

    public QuestionController(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

//    @RequestMapping(method = RequestMethod.GET)
//    public List<Question> searchQuestions(@RequestParam("type") String type, @RequestParam("answer") String answer) throws Exception {
//        List<? extends Question> questions = questionRepository.searchByAnswer(answer);
//        List<Question> result = new ArrayList<>();
//        for (Question question : questions) {
//            result.add(question);
//        }
//        return result;
//    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Question> getAll() throws Exception {
        return questionRepository.getAll();
    }

}
