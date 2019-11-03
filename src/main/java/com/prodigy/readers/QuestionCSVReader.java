package com.prodigy.readers;

import com.opencsv.CSVReader;
import com.prodigy.domain.Question;
import com.prodigy.service.AddQuestionRequest;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by guym on 21/05/2017.
 */

public class QuestionCSVReader {

    private final QuestionCSVParser parser;

    public QuestionCSVReader(QuestionCSVParser parser) {
        this.parser = parser;
    }

    public List<Question> readAll(Reader reader) throws IOException {
        CSVReader csv = new CSVReader(reader);
        List<Question> requests = new ArrayList<>();
        for (String[] values : csv) {
            Question request = parser.parseValues(values);
            requests.add(request);
        }
        return requests;
    }
}
