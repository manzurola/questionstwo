package com.prodigy.diff;

import com.prodigy.grammar.Word;

import java.util.List;

public interface WordDiffCheck {

    List<Diff<Word>> diffSourceAndTarget(List<Word> source, List<Word> target);
}
