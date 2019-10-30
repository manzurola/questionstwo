package com.prodigy.testdata;

import com.prodigy.domain.Question;
import com.prodigy.domain.questions.service.AddQuestionRequest;
import com.prodigy.domain.questions.readers.CSVQuestionReader;
import com.prodigy.domain.questions.readers.QuestionReader;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class TestQuestions {

    private final List<AddQuestionRequest> requests;

    public TestQuestions() {
        try {
            File file = new File(this.getClass().getClassLoader().getResource("questions-en.csv").getFile());
            QuestionReader reader = new CSVQuestionReader(file);
            requests = new ArrayList<>(reader.readAll());
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }

    }

    public List<Question> questions() {
        return requests.stream()
                .map(AddQuestionRequest::toQuestion)
                .map(Question.Builder::build)
                .collect(Collectors.toList());
    }

    public AddQuestionRequest randomRequest() {
        return requests.get(new Random().nextInt(requests.size()));
    }

}
