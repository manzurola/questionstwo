package com.prodigy.api.test.test;

import com.opencsv.CSVReader;
import com.prodigy.api.test.questions.request.AddQuestionRequest;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by guym on 21/05/2017.
 */
@Component
public class AddQuestionRequestCSVReader implements AddQuestionRequestReader {

    private final CSVReader reader;

    public AddQuestionRequestCSVReader() throws IOException {
        this.reader = new CSVReader(Files.newBufferedReader(new File(this.getClass().getClassLoader().getResource("questions_en.csv").getFile()).toPath()));
    }

    public List<AddQuestionRequest> readAll() throws IOException {
        List<AddQuestionRequest> requests = new ArrayList<>();
        for (String[] values : reader) {
            AddQuestionRequest request = parseCSVLine(values);
            requests.add(request);
        }
        return requests;
    }

    @Override
    public AddQuestionRequest readNext() throws IOException {
        return parseCSVLine(reader.readNext());
    }

    @Override
    public void close() throws IOException {
        reader.close();
    }

    private AddQuestionRequest parseCSVLine(String[] values) {
        String body = values[0].trim();
        String answer = values[1].trim();
        String subject = values[2].trim();
        String instructions = values[3].trim();

        return new AddQuestionRequest(body, Arrays.asList(answer), instructions, subject, null, null);
    }

}
