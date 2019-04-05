package com.prodigy.api.exercises;

import com.prodigy.api.common.service.AbstractCommand;
import com.prodigy.api.questions.Exercise;
import com.prodigy.api.questions.Question;
import com.prodigy.api.questions.data.QuestionRepository;
import com.prodigy.api.questions.request.AddQuestionRequest;
import org.springframework.stereotype.Component;

@Component
public class AddExerciseCommand extends AbstractCommand<Exercise, AddExerciseRequest> {

    private final ExerciseRepository repository;

    public AddExerciseCommand(ExerciseRepository repository) {
        this.repository = repository;
    }

    @Override
    protected Exercise doExecute(AddExerciseRequest request) throws Exception {
        Exercise exercise = Exercise.builder()
                .title(request.getTitle())
                .source(request.getSource())
                .questions(request.getQuestionIds())
                .build();
        return repository.add(exercise);
    }
}
