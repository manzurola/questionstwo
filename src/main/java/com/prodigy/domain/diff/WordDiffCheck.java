package com.prodigy.domain.diff;

import com.prodigy.domain.nlp.Word;

import java.util.List;

public interface WordDiffCheck {

    WordDiff diffWords(List<Word> source, List<Word> target);

}
