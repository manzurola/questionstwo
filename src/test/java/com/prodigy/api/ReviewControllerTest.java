package com.prodigy.api;

import com.prodigy.api.answers.Answer;
import com.prodigy.api.answers.SubmitAnswerRequest;
import com.prodigy.api.env.EndToEndTest;
import com.prodigy.api.questions.Question;
import com.prodigy.api.review.Review;
import com.prodigy.api.review.Score;
import com.prodigy.api.review.request.GetReviewRequest;
import com.prodigy.api.test.*;
import com.prodigy.api.users.User;
import com.prodigy.api.users.request.AddUserRequest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;

public class ReviewControllerTest extends EndToEndTest {

    @Autowired
    private QuestionUtils questionUtils;

    @Test
    public void getReview() {
        // add question
        Question question = new AddQuestionApiCall().run(
                questionUtils.randomAddQuestionRequest(),
                template,
                baseUrl
        ).getBody();
        // add user
        User user = new AddUserApiCall().run(new AddUserRequest("guym@gmail.com"), template, baseUrl).getBody();
        // submit answer
        Answer answer = new SubmitAnswerApiCall().run(
                new SubmitAnswerRequest(user.getId(), question.getId(), question.getAnswerKey().get(0)),
                template,
                baseUrl
        ).getBody();
        // get review for answer
        Review actual = new GetReviewApiCall().run(
                new GetReviewRequest(answer.getId()),
                template,
                baseUrl
        ).getBody();
        Review expected = Review.builder()
                .answerId(answer.getId())
                .id(actual.getId())
                .score(new Score(100))
                .build();

        assertEquals(expected, actual);
    }
}
