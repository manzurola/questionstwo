package com.prodigy.utils;

import com.prodigy.api.questions.request.AddQuestionRequest;
import com.prodigy.api.questions.utils.AddQuestionRequestCSVReader;
import com.prodigy.api.questions.utils.AddQuestionRequestReader;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

    public List<AddQuestionRequest> data() {
        return requests;
    }

    public AddQuestionRequest random() {
        return requests.get(new Random().nextInt(requests.size()));
    }

    @Test
    public void name() {
        System.out.println(requests);
    }
}
