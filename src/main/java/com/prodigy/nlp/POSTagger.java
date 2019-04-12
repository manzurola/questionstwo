package com.prodigy.nlp;

import java.util.List;

public interface POSTagger {

    List<TaggedWord> tagSentence(List<Word> sentence);
}
