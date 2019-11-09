package com.prodigy.domain.diff;

import com.prodigy.domain.diff.Diff;
import com.prodigy.domain.nlp.Word;

import java.util.List;

public class WordDiff {

    private final List<Diff<Word>> words;

    public WordDiff(List<Diff<Word>> words) {
        this.words = words;
    }
}
