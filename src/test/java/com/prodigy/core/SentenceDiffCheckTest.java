package com.prodigy.core;

import com.prodigy.core.diff.DMPDiffCalculator;
import com.prodigy.core.diff.Diff;
import com.prodigy.core.diff.SentenceDiffChecker;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class SentenceDiffCheckTest {

//    @Test
//    public void diffCheck() {
//
//        List<Token> source = Arrays.asList(new Token("Hello"), new Token("World."));
//        List<Token> target = Arrays.asList(new Token("Hello"), new Token("dear"), new Token("World."));
//
//        List<Diff<Token>> expected = Arrays.asList(
//                new Diff<>(Diff.Operation.EQUAL, new Token("Hello")),
//                new Diff<>(Diff.Operation.INSERT, new Token("dear")),
//                new Diff<>(Diff.Operation.EQUAL, new Token("World."))
//        );
//
//        SentenceDiffChecker diffChecker = new SentenceDiffChecker(new DMPDiffCalculator());
//        List<Diff<Token>> actual = diffChecker.checkDiff(source, target);
//
//        Assert.assertEquals(expected, actual);
//    }
}
