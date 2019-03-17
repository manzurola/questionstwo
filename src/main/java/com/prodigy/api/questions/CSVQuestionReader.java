package com.prodigy.api.questions;

import com.opencsv.CSVReader;
import com.prodigy.api.questions.domain.Question;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by guym on 21/05/2017.
 */
public class CSVQuestionReader<T extends Question> implements QuestionReader {

    private final CSVReader reader;
    private final QuestionParser<T> parser;

    public CSVQuestionReader(Reader reader, QuestionParser<T> parser) {
        this.reader = new CSVReader(reader);
        this.parser = parser;
    }

    public List<T> readAll() throws IOException {
        List<T> questions = new ArrayList<>();
        for (String[] values : reader) {
            T question = parser.parseQuestion(values);
            questions.add(question);
        }
        return questions;
    }

    @Override
    public Question readNext() throws IOException {
        return parser.parseQuestion(reader.readNext());
    }

    @Override
    public String getParserVersion() {
        return parser.getVersion();
    }


    @Override
    public void close() throws IOException {
        reader.close();
    }

    @Override
    public Iterator<Question> iterator() {
        return new CsvQuestionIterator(reader.iterator(), parser);
    }

}
