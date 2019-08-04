package com.prodigy.engine.nlp;

import com.prodigy.engine.Word;

import java.util.List;

public interface GrammaticalRelationsParser {

    List<GrammaticalRelation> parse(List<Word> sentence);
}
