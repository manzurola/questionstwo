package com.prodigy.readers;

import com.prodigy.domain.Question;

import java.util.Collections;

public class QuestionCSVParserV1 implements QuestionCSVParser {
    @Override
    public Question parseValues(String[] values) {
        String body = values[0].trim();
        String answer = values[1].trim();
//        String subject = values[2].trim(); // unused but exists
        String instructions = values[3].trim();

        return new Question(body, Collections.singletonList(answer), instructions);
    }
}
