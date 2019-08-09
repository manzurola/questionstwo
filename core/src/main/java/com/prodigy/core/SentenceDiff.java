package com.prodigy.core;

import com.prodigy.core.diff.Diff;

import java.util.List;

public interface SentenceDiff {

    Sentence target();

    Sentence source();

    Sentence origin();

    List<Diff<Word>> sourceToTargetDiff();

    List<Diff<Word>> originToTargetDiff();



}
