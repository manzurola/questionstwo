package com.prodigy.nlp.diff;

import edu.stanford.nlp.ling.Word;
import edu.stanford.nlp.process.WhitespaceTokenizer;

import java.io.StringReader;
import java.util.List;
import java.util.stream.Collectors;

public class SimpleSentenceDiffCheck implements SentenceDiffCheck<SimpleSentenceDiff> {

    private final TextDiffCalculator diffCalculator;

    public SimpleSentenceDiffCheck(TextDiffCalculator diffCalculator) {
        this.diffCalculator = diffCalculator;
    }

    @Override
    public SimpleSentenceDiff check(String source, String target) {
        List<Word> sourceWords = WhitespaceTokenizer.newWordWhitespaceTokenizer(new StringReader(source)).tokenize();
        List<Word> targetWords = WhitespaceTokenizer.newWordWhitespaceTokenizer(new StringReader(target)).tokenize();
        List<TextDiff> diff = diffCalculator.diff(
                sourceWords.stream().map(Word::word).collect(Collectors.toList()),
                targetWords.stream().map(Word::word).collect(Collectors.toList())
        );
        return new SimpleSentenceDiff(source, target, diff, diffCalculator.distance(diff));
    }

}
