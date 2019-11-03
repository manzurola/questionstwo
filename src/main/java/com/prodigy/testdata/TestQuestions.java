package com.prodigy.testdata;

import com.prodigy.domain.Question;
import com.prodigy.readers.QuestionCSVParserV1;
import com.prodigy.readers.QuestionCSVReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class TestQuestions {

    private final List<Question> requests;

    public TestQuestions() {
        try {
            URL resource = this.getClass().getClassLoader().getResource("questions-en.csv");
            QuestionCSVReader reader = new QuestionCSVReader(new QuestionCSVParserV1());
            requests = Collections.unmodifiableList(reader.readAll(new InputStreamReader(resource.openStream())));
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public List<Question> getAll() {
        return requests;
    }

    public Question random() {
        return requests.get(new Random().nextInt(requests.size()));
    }

}
