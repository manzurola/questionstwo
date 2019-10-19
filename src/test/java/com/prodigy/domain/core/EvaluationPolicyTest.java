package com.prodigy.domain.core;

import com.prodigy.diff.*;
import com.prodigy.grammar.corenlp.CoreSentenceWrapperFactory;
import com.prodigy.grammar.Sentence;
import com.prodigy.grammar.SentenceFactory;
import com.prodigy.grammar.Word;
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

        SentenceFactory sentenceFactory = new CoreSentenceWrapperFactory();
        SentenceDiffCheck diffChecker = new SentenceDiffCheckImpl(new WordDiffCheckImpl());

        Sentence target = sentenceFactory.getSentence("He is walking home.");
        Sentence input = sentenceFactory.getSentence("He walking home.");

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
