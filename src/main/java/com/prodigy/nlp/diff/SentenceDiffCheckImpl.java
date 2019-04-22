package com.prodigy.nlp.diff;

import com.prodigy.nlp.Sentence;
import com.prodigy.nlp.TaggedWord;
import com.prodigy.nlp.Word;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SentenceDiffCheckImpl implements SentenceDiffCheck {

    private final TextDiffCalculator diffCalculator;

    public SentenceDiffCheckImpl(TextDiffCalculator diffCalculator) {
        this.diffCalculator = diffCalculator;
    }

    @Override
    public SentenceDiff check(String source, String target) {

        List<TextDiff> diff = diffCalculator.diff(source, target);

        SentenceDiff sentenceDiff = new SentenceDiff(null, null, null);
        sentenceDiff.setTextDiffs(diff);
        return sentenceDiff;
    }

    @Override
    public SentenceDiff check(Sentence source, Sentence target) {

        List<TaggedWord> sourceWords = source.getWords();
        List<TaggedWord> targetWords = target.getWords();

        List<TextDiff> wordDiff = diffCalculator.diff(
                sourceWords.stream().map(Word::word).collect(Collectors.toList()),
                targetWords.stream().map(Word::word).collect(Collectors.toList())
        );

        List<WordDiff> taggedDiff = tagDiff(wordDiff, sourceWords, targetWords);

        return new SentenceDiff(source, target, taggedDiff);

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
