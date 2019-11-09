package com.prodigy.application.test;

import com.prodigy.domain.Answer;
import com.prodigy.domain.Id;
import com.prodigy.domain.Question;

public interface AddAnswerService {

    Answer execute(Id<Question> question, String input);
}
