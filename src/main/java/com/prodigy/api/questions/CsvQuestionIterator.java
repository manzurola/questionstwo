package com.prodigy.api.questions;

import com.prodigy.api.questions.domain.Question;

import java.util.Iterator;

/**
 * Created by guym on 25/05/2017.
 */
public class CsvQuestionIterator implements Iterator<Question> {
    private final Iterator<String[]> iterator;
    private final QuestionParser parser;

    public CsvQuestionIterator(Iterator<String[]> iterator, QuestionParser parser) {
        this.iterator = iterator;
        this.parser = parser;
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public Question next() {
        return parser.parseQuestion(iterator.next());
    }
}
