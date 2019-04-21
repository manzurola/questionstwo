package com.prodigy.ml;

import com.prodigy.api.questions.Question;
import com.prodigy.nlp.POS;
import com.prodigy.nlp.SentenceParser;
import com.prodigy.nlp.TaggedWord;
import com.prodigy.nlp.diff.SentenceDiff;
import com.prodigy.nlp.diff.SentenceDiffCheck;
import com.prodigy.nlp.diff.TextDiff;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class QuestionFeatureExtractor implements FeatureExtractor<Question> {

    private final SentenceParser sentenceParser;
    private final SentenceDiffCheck diffCheck;

    public QuestionFeatureExtractor(SentenceParser sentenceParser, SentenceDiffCheck diffCheck) {
        this.sentenceParser = sentenceParser;
        this.diffCheck = diffCheck;
    }

    public FeatureVector<Question> extract(Question question) {

        SentenceDiff diff = diffCheck.check(sentenceParser.parse(question.getBody()), sentenceParser.parse(question.getAnswerKey().get(0)));

        return new FeatureVector<>(calcVector(diff), question);
    }

    private double[] calcVector(SentenceDiff diff) {

        double[] vector = new double[POS.values().length];
        Arrays.fill(vector, 0);

        // for POS i=0 to n
        for (int point = 0; point < vector.length; point++) {

            // for each diff in wordDiff

            for (int j = 0; j < diff.getDiff().size(); j++) {

                TextDiff textDiff = diff.getDiff().get(j).diff();
                TaggedWord tagged = diff.getDiff().get(j).target();

                // if diff.pos equals current pos

                if (POS.values()[point].equals(tagged.tag())) {

                    // score = op.equal ? 0 : 1

                    int score = TextDiff.Operation.EQUAL.equals(textDiff.getOperation()) ? 0 : 1;
                    vector[point] += score;
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
