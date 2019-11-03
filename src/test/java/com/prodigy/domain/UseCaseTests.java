package com.prodigy.domain;

import org.junit.Test;

public class UseCaseTests {

    @Test
    public void addQuestion() {
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
