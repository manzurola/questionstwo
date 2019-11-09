package com.prodigy.domain;

import com.prodigy.application.command.AddQuestionCommand;
import com.prodigy.application.command.AddQuestionCommandHandler;
import com.prodigy.domain.repository.QuestionRepository;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;

public class UseCaseTests {

    @Test
    public void addQuestion() {
        Question expected = new Question("a body", Arrays.asList("an answer"), "instructions");
        AddQuestionCommand command = new AddQuestionCommand(expected);
        QuestionRepository repository = Mockito.mock(QuestionRepository.class);
        AddQuestionCommandHandler handler = new AddQuestionCommandHandler(repository);

        handler.handle(command);
        Mockito.verify(repository).add(expected);
    }

    @Test
    public void addAnswer() {

    }

    @Test
    public void getAnswerWordDiff() {
        // given and answer, get the difference in words between the answer and the actual solution for the question
    }

    @Test
    public void scoreAnswer() {
        // given an answer, get a score as a double within [0. 1]
        // computation is done based on the word difference between the input and the target sentence,
        // and may include additional context such as the diff between source and target or custom rules
    }

    @Test
    public void getAnswerViolations() {
        // given and answer, calculate a set of violations (mistakes) with their respective explanations
        // explanations can be custom based on a regex match on the input or diff
    }

    @Test
    public void cleanAndMergeDiff() {
        // given an answer, create a clean and merged diff, collapsing consecutive diffs with the same operation into one
        // e.g. del-I del-know -> del- I know
        // to simplify this, the clean diff handles strings and not Word objects
    }


}
