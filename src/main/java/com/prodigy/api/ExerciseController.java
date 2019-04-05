package com.prodigy.api;


import com.prodigy.api.common.service.Result;
import com.prodigy.api.common.service.ServiceExecutor;
import com.prodigy.api.exercises.AddExerciseCommand;
import com.prodigy.api.exercises.AddExerciseRequest;
import com.prodigy.api.exercises.Exercise;
import com.prodigy.api.exercises.GetAllExercisesCommand;
import com.prodigy.api.exercises.GetAllExercisesRequest;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * Created by guym on 17/05/2017.
 */
@RestController
@RequestMapping("/exercises")
public class ExerciseController {

    private final ServiceExecutor serviceExecutor;

    public ExerciseController(ServiceExecutor serviceExecutor) {
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
    public List<Exercise> getAllExercises() {
        Result<List<Exercise>> result = serviceExecutor.execute(GetAllExercisesCommand.class, new GetAllExercisesRequest());
        return result.payload();
    }

    @RequestMapping(method = RequestMethod.POST)
    public Exercise addExercise(@RequestBody AddExerciseRequest requestBody) {
        Result<Exercise> result = serviceExecutor.execute(AddExerciseCommand.class, requestBody);
        return result.payload();
    }

}
