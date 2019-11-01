package com.prodigy.domain;

import org.junit.Test;

public class UseCaseTests {

    @Test
    public void testSolveQuestion() {
        // get a question
        // submit an answer

        // the score of each word in the solution should be predetermined before passing it on to the reviewer
    }

    @Test
    public void testCreateQuestion() {

    }

    @Test
    public void testAnswerReview() {
        // evaluation pipeline (get evaluator per question as question might have specific context needed to evaluate an answer):
        // 1. get sentence diff between user answer and target sentence
        // 2. assign weights to diffs (eg low weight for missing punct, high weight for critical words)
        // 3. score diff (average score of user answer, can be assigned a threshold to determine if pass or not)
        // 4. get feedback on diff violations (textual feedback on erroneous diffs)
        //
    }
}
