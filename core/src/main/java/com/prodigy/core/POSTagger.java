package com.prodigy.core;

import java.util.List;

public interface POSTagger {

    List<TaggedWord> tagWords(List<Word> words);
}
