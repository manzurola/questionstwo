package com.prodigy.core;

import com.prodigy.core.diff.DMPDiffCalculator;
import com.prodigy.core.diff.Diff;
import com.prodigy.core.diff.SentenceDiffChecker;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class SentenceDiffCheckTest {

    @Test
    public void diffCheck() {

        List<Word> source = Arrays.asList(new Word("Hello"), new Word("World."));
        List<Word> target = Arrays.asList(new Word("Hello"), new Word("dear"), new Word("World."));

        List<Diff<Word>> expected = Arrays.asList(
                new Diff<>(Diff.Operation.EQUAL, new Word("Hello")),
                new Diff<>(Diff.Operation.INSERT, new Word("dear")),
                new Diff<>(Diff.Operation.EQUAL, new Word("World."))
        );

        SentenceDiffChecker diffChecker = new SentenceDiffChecker(new DMPDiffCalculator());
        List<Diff<Word>> actual = diffChecker.checkDiff(source, target);

        Assert.assertEquals(expected, actual);
    }
}
