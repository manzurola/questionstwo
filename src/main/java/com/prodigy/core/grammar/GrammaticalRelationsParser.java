package com.prodigy.core.grammar;

import com.prodigy.core.Word;

import java.util.List;

public interface GrammaticalRelationsParser {

    List<GrammaticalRelation> parse(List<Word> sentence);
}
