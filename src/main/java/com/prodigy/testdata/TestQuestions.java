package com.prodigy.utils;

import com.prodigy.domain.Question;
import com.prodigy.service.AddQuestionRequest;
import com.prodigy.readers.CSVQuestionReader;
import com.prodigy.readers.QuestionReader;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class QuestionTestData {

    private final List<AddQuestionRequest> requests;

    public QuestionTestData() {
        AddQuestionRequestReader reader = null;
        try {
            reader = new AddQuestionRequestCSVReader(new File(this.getClass().getClassLoader().getResource("questions-en.csv").getFile()));
            requests = new ArrayList<>(reader.readAll());
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }

    }

    public List<AddQuestionRequest> requests() {
        return requests;
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
