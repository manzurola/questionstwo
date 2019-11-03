package com.prodigy.readers;

import com.prodigy.domain.Question;

public interface QuestionCSVParser {

    Question parseValues(String[] values);
}
