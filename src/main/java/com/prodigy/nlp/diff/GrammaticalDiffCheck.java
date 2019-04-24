package com.prodigy.nlp.diff;

import com.prodigy.nlp.Sentence;
import com.prodigy.nlp.SentenceParser;
import com.prodigy.nlp.TaggedWord;
import com.prodigy.nlp.Word;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class GrammaticalDiffCheck implements SentenceDiffCheck<GrammaticalSentenceDiff> {

    private final TextDiffCalculator diffCalculator;
    private final SentenceParser sentenceParser;

    public GrammaticalDiffCheck(TextDiffCalculator diffCalculator, SentenceParser sentenceParser) {
        this.diffCalculator = diffCalculator;
        this.sentenceParser = sentenceParser;
    }

    @Override
    public GrammaticalSentenceDiff check(String source, String target) {

        Sentence sourceParse = sentenceParser.parse(source);
        Sentence targetParse = sentenceParser.parse(target);

        List<TaggedWord> sourceWords = sourceParse.getTaggedWords();
        List<TaggedWord> targetWords = targetParse.getTaggedWords();

        List<TextDiff> wordDiff = diffCalculator.diff(
                sourceWords.stream().map(Word::value).collect(Collectors.toList()),
                targetWords.stream().map(Word::value).collect(Collectors.toList())
        );

        List<WordDiff> taggedDiff = tagDiff(wordDiff, sourceWords, targetWords);

        return new GrammaticalSentenceDiff(sourceParse, targetParse, taggedDiff, wordDiff);
    }

    private List<WordDiff> tagDiff(List<TextDiff> diffs, List<TaggedWord> source, List<TaggedWord> target) {
        LinkedList<TaggedWord> sourceQueue = new LinkedList<>(source);
        LinkedList<TaggedWord> targetQueue = new LinkedList<>(target);
        List<WordDiff> taggedDiff = new ArrayList<>();

        for (TextDiff diff : diffs) {
            switch (diff.getOperation()) {
                case EQUAL:
                case INSERT:
                    taggedDiff.add(new WordDiff(diff, targetQueue.remove()));
                    break;
                case DELETE:
                    taggedDiff.add(new WordDiff(diff, sourceQueue.remove()));
                    break;
            }
        }

        return taggedDiff;
    }


}
