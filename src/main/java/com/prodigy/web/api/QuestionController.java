package com.prodigy.web.api;


import com.prodigy.answers.domain.Answer;
import com.prodigy.answers.service.SubmitAnswerCommand;
import com.prodigy.answers.service.SubmitAnswerRequest;
import com.prodigy.common.data.Id;
import com.prodigy.common.service.Result;
import com.prodigy.common.service.ServiceExecutor;
import com.prodigy.questions.domain.Question;
import com.prodigy.questions.service.AddQuestionCommand;
import com.prodigy.questions.service.GetAllQuestionsCommand;
import com.prodigy.questions.service.AddQuestionRequest;
import com.prodigy.questions.service.GetAllQuestionsRequest;
import org.springframework.web.bind.annotation.*;

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
        return result.getData();
    }

//    @RequestMapping(method = RequestMethod.GET)
//    public List<Question> getNextQuestions() {
//        Result<List<Question>> result = serviceExecutor.execute(GetAllQuestionsCommand.class, new GetAllQuestionsRequest());
//        return result.getData();
//    }

    @RequestMapping(method = RequestMethod.POST)
    public Question addQuestion(@RequestBody AddQuestionRequest requestBody) {
        Result<Question> result = serviceExecutor.execute(AddQuestionCommand.class, requestBody);
        return result.getData();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/{questionId}/answer")
    public Answer submitAnswer(@PathVariable Id<Question> questionId, @RequestBody SubmitAnswerRequest request) {
        return serviceExecutor.execute(SubmitAnswerCommand.class, request.withQuestionId(questionId)).getData();
    }



}
