package com.prodigy.diff;

import com.prodigy.grammar.Word;

import java.util.List;

public interface WordDiffChecker {

    List<Diff<Word>> diff(List<Word> source, List<Word> target);
}
