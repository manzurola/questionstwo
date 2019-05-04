package com.prodigy.api.questions.utils;

import com.prodigy.api.questions.Question;
import com.prodigy.api.questions.request.AddQuestionRequest;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Component
public class QuestionUtils {

    private final List<AddQuestionRequest> requests = new ArrayList<>();

    public QuestionUtils() throws IOException {

    }

    public QuestionUtils(List<AddQuestionRequestReader> readers) throws IOException {
        for (AddQuestionRequestReader reader : readers) {
            requests.addAll(reader.readAll());
            reader.close();
        }
    }

    public List<AddQuestionRequest> getQuestions() {
        return requests;
    }

    public AddQuestionRequest randomAddQuestionRequest() {
        return requests.get(new Random().nextInt(requests.size()));
    }

    public Question.Builder newQuestionFromRequest(AddQuestionRequest request) {
        return Question.builder()
                .answerKey(request.getAnswerKey())
                .instructions(request.getInstructions())
                .body(request.getBody());
    }
}
