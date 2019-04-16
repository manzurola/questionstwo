package com.prodigy.api.test;

import com.prodigy.api.questions.Question;
import com.prodigy.api.questions.request.AddQuestionRequest;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Random;

@Component
public class QuestionUtils {

    private final AddQuestionRequestReader reader;
    private final List<AddQuestionRequest> requests;

    public QuestionUtils() throws IOException {
        this(new AddQuestionRequestCSVReader());
    }

    public QuestionUtils(AddQuestionRequestReader reader) throws IOException {
        this.reader = reader;
        requests = reader.readAll();
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
                .body(request.getBody());
    }
}
