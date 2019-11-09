package com.prodigy.domain;

import com.prodigy.domain.diff.*;
import com.prodigy.domain.diff.impl.DMPListDiffCheck;
import com.prodigy.domain.nlp.Sentence;
import com.prodigy.domain.nlp.SentenceFactory;
import com.prodigy.domain.nlp.Word;
import com.prodigy.domain.diff.SentenceDiff;
import com.prodigy.domain.diff.SentenceDiffChecker;
import com.prodigy.domain.nlp.impl.CoreNLPSentenceFactory;
import com.prodigy.domain.diff.impl.SentenceDiffCheckerImpl;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class EvaluationPolicyTest {

    @Test
    public void testPolicy() {

        // define source, target and input
        // define rule consisting of diff list and matching feedback and result
        // run policy - check diff between target and input
        // assert that feedback is equals to expected

        SentenceFactory sentenceFactory = new CoreNLPSentenceFactory();
        SentenceDiffChecker diffChecker = new SentenceDiffCheckerImpl(new DMPListDiffCheck());

        Sentence target = sentenceFactory.fromString("He is walking home.");
        Sentence input = sentenceFactory.fromString("He walking home.");

        SentenceDiff diff = diffChecker.diffSourceAndTarget(input, target);

        Rule rule = new Rule();
        rule.diffs = Arrays.asList(new Diff<>(Operation.INSERT, "is"));
        rule.feedback = "testing feedback on specific mistake";

        Assert.assertTrue(rule.isSatisfied(diff));
    }

    private static class Rule {
        private List<Diff<String>> diffs;
        private Action action;
        private String feedback;

        boolean isSatisfied(SentenceDiff sentenceDiff) {
            // check if sentence diff contains all diffs in consecutive order
            // (need to define more options, such as in beginning of diff, end, contains in general, at least one, etc)

            // from 0 to n diff in diffs
            // if sentenceDiff[i] equals diffs[

            List<Diff<Word>> words = sentenceDiff.words();

            for (Diff<Word> source : words) {
                boolean match = false;
                for (Diff<String> target : diffs) {
                    if (match = diffMatches(source, target)) {
                        break;
                    }
                }
                if (match) {
                    return true;
                }
            }

            return false;
        }

        private boolean diffMatches(Diff<Word> source, Diff<String> target) {
            return source.item().value().equals(target.item()) && source.operation().equals(target.operation());
        }
    }

    private enum Action {
        Pass,
        Warn,
        Fail
    }
}
