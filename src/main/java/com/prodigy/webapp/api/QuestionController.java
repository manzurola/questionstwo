package com.prodigy.webapp.api;


import com.prodigy.application.command.*;
import com.prodigy.domain.Question;
import com.prodigy.application.query.GetQuestionsQuery;
import com.prodigy.application.query.QueryProcessor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Created by guym on 17/05/2017.
 */
@RestController
@RequestMapping("/questions")
public class QuestionController {

    private final CommandProcessor commandProcessor;
    private final QueryProcessor queryProcessor;

    public QuestionController(CommandProcessor commandProcessor, QueryProcessor queryProcessor) {
        this.commandProcessor = commandProcessor;
        this.queryProcessor = queryProcessor;
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
        List<Question> result = queryProcessor.process(new GetQuestionsQuery());
        return result;
    }

//    @RequestMapping(method = RequestMethod.GET)
//    public List<Question> getNextQuestions() {
//        Result<List<Question>> result = serviceExecutor.execute(GetAllQuestionsCommand.class, new GetAllQuestionsRequest());
//        return result.getData();
//    }

//    @RequestMapping(method = RequestMethod.POST, value = "/{questionId}/answer")
//    public Answer submitAnswer(@PathVariable Id<Question> questionId, @RequestBody SubmitAnswerRequest request) {
////        return commandProcessor.process(SubmitAnswerCommandHandler.class, request.withQuestionId(questionId)).getData();
//        return
//    }


}
