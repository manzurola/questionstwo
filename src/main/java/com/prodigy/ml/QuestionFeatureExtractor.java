package com.prodigy.ml;

import com.prodigy.api.questions.Question;
import com.prodigy.nlp.POS;
import com.prodigy.nlp.SentenceParser;
import com.prodigy.nlp.TaggedWord;
import com.prodigy.nlp.diff.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class QuestionFeatureExtractor implements FeatureExtractor<Question> {

    private final SentenceDiffCheck<GrammaticalSentenceDiff> diffCheck;

    public QuestionFeatureExtractor(SentenceDiffCheck<GrammaticalSentenceDiff> diffCheck) {
        this.diffCheck = diffCheck;
    }

    public FeatureVector<Question> extract(Question question) {

        GrammaticalSentenceDiff diff = diffCheck.check(question.getBody(), question.getAnswerKey().get(0));

        return new FeatureVector<>(calcVector(diff.getWordDiff()), question);
    }

    private double[] calcVector(List<WordDiff> diff) {

        double[] vector = new double[POS.values().length];
        Arrays.fill(vector, 0);

        // for POS i=0 to n
        for (int point = 0; point < vector.length; point++) {

            // for each diff in wordDiff

            for (int j = 0; j < diff.size(); j++) {

                TextDiff textDiff = diff.get(j).diff();
                TaggedWord tagged = diff.get(j).target();

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
