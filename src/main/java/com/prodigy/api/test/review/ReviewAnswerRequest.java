package com.prodigy.api.test.review;

import com.prodigy.api.test.answers.Answer;
import com.prodigy.api.test.common.Id;
import com.prodigy.api.test.users.User;

public class ReviewAnswerRequest {

    private Id<Answer> answerId;
    private Id<User> reviewrId;
}
