package com.prodigy.ml;

import com.prodigy.api.questions.Question;
import com.prodigy.nlp.*;
import com.prodigy.nlp.diff.TextDiff;
import com.prodigy.nlp.diff.TextDiffCalculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class QuestionFeatureExtractor implements FeatureExtractor<Question> {

    private final SentenceParser sentenceParser;
    private final TextDiffCalculator diffCalculator;

    public QuestionFeatureExtractor(SentenceParser sentenceParser, TextDiffCalculator diffCalculator) {
        this.sentenceParser = sentenceParser;
        this.diffCalculator = diffCalculator;
    }

    public FeatureVector<Question> extract(Question question) {

        Sentence source = sentenceParser.parse(question.getBody());
        Sentence target = sentenceParser.parse(question.getAnswerKey().get(0));

        List<TaggedWord> sourceWords = source.getWords();
        List<TaggedWord> targetWords = target.getWords();

        List<TextDiff> wordDiff = diffCalculator.diff(
                sourceWords.stream().map(Word::word).collect(Collectors.toList()),
                targetWords.stream().map(Word::word).collect(Collectors.toList())
        );

        List<TaggedWord> taggedDiff = tagDiff(wordDiff, sourceWords, targetWords);
        System.out.println(wordDiff);
        System.out.println(taggedDiff);

        return new FeatureVector<>(calcVector(wordDiff, taggedDiff), question);
    }

    private double[] calcVector(List<TextDiff> wordDiff, List<TaggedWord> taggedDiff) {

        double[] vector = new double[POS.values().length];
        Arrays.fill(vector, 0);

        // for POS i=0 to n
        for (int i = 0; i < vector.length; i++) {

            // for each diff in wordDiff

            for (int j = 0; j < wordDiff.size(); j++) {

                TextDiff diff = wordDiff.get(j);
                TaggedWord tagged = taggedDiff.get(j);

                // if diff.pos equals current pos

                if (POS.values()[i].equals(tagged.tag())) {

                    // score = op.equal ? 0 : 1

                    int score = TextDiff.Operation.EQUAL.equals(diff.getOperation()) ? 0 : 1;
                    vector[i] += score;
                }
            }
        }

        return vector;
    }

    private List<TaggedWord> tagDiff(List<TextDiff> diffs, List<TaggedWord> source, List<TaggedWord> target) {
        LinkedList<TaggedWord> sourceQueue = new LinkedList<>(source);
        LinkedList<TaggedWord> targetQueue = new LinkedList<>(target);
        List<TaggedWord> taggedDiff = new ArrayList<>();

        for (TextDiff diff : diffs) {
            switch (diff.getOperation()) {
                case EQUAL:
                case INSERT:
                    taggedDiff.add(targetQueue.remove());
                    break;
                case DELETE:
                    taggedDiff.add(sourceQueue.remove());
                    break;
            }
        }

        return taggedDiff;
    }

}
