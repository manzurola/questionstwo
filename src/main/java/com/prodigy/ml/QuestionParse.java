package com.prodigy.ml;

import com.prodigy.api.common.Id;
import com.prodigy.api.questions.Question;
import com.prodigy.nlp.Sentence;
import com.prodigy.nlp.diff.WordDiff;

import java.util.List;

public interface QuestionParse {

    Id<Question> questionId();

    Sentence source();

    Sentence target();

    List<WordDiff> wordDiff();
}
