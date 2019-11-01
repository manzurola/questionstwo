package com.prodigy.questions.readers;

import com.opencsv.CSVReader;
import com.prodigy.service.AddQuestionRequest;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by guym on 21/05/2017.
 */

public class AddQuestionRequestCSVReader implements AddQuestionRequestReader {

    private CSVReader reader;

    public AddQuestionRequestCSVReader(File file) throws IOException {
        System.out.println("AddQuestionRequestCSVReader: " + file);
        this.reader = new CSVReader(Files.newBufferedReader(file.toPath()));
    }

    public List<AddQuestionRequest> readAll() throws IOException {
        List<AddQuestionRequest> requests = new ArrayList<>();
        for (String[] values : reader) {
            AddQuestionRequest request = parseValues(values);
            requests.add(request);
        }
        return requests;
    }

    @Override
    public AddQuestionRequest readNext() throws IOException {
        return parseValues(reader.readNext());
    }

    private List<AddQuestionRequest> readAll(boolean skipParseErrors) throws IOException {
        List<AddQuestionRequest> requests = new ArrayList<>();
        for (String[] values : reader) {
            AddQuestionRequest request = parseValues(values);
            requests.add(request);
        }
        return requests;
    }

    @Override
    public void close() throws IOException {
        reader.close();
    }

    protected AddQuestionRequest parseValues(String[] values) {
        String body = values[0].trim();
        String answer = values[1].trim();
        String subject = values[2].trim();
        String instructions = values[3].trim();

        return new AddQuestionRequest(body, Arrays.asList(answer), instructions, subject, null);
    }

}
