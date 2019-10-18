package com.prodigy.recommender.features;

import com.prodigy.diff.SentenceDiffCheckImpl;
import com.prodigy.diff.WordDiffCheckImpl;
import com.prodigy.questions.Question;
import com.prodigy.recommend.QuestionFeatureExtractor;
import com.prodigy.grammar.corenlp.CoreSentenceWrapperFactory;
import com.prodigy.recommend.DiffPOSFeatureExtractor;
import com.prodigy.recommend.FeatureExtractor;
import com.prodigy.recommend.Point;
import com.prodigy.recommend.clustering.ApacheMLClusterer;
import com.prodigy.recommend.clustering.Cluster;
import com.prodigy.recommend.clustering.Clusterable;
import com.prodigy.recommend.clustering.Clusterer;
import com.prodigy.utils.QuestionTestData;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ExtractorTest {

    @Test
    public void extractDiffPOSFeatures() throws Exception {
        // print vectors of question diffs, cluster and see results

        // extract features from source and target sentences
        // the input source and target, where source is the user answer, is actually the user features

        QuestionTestData testData = new QuestionTestData();
        List<Question> questions = testData.questions();

        FeatureExtractor<Question> features = new QuestionFeatureExtractor(
                new CoreSentenceWrapperFactory(),
                new DiffPOSFeatureExtractor(),
                new SentenceDiffCheckImpl(new WordDiffCheckImpl())
        );

        List<Clusterable<Question>> vectors = new ArrayList<>();
        for (Question question : questions) {

            try {
                Point point = features.extract(question);
                vectors.add(new Clusterable<>(point, question));
            } catch (Exception e) {
                System.out.println(String.format("Failed to parse question [%s], ignoring.", question.getId()));
            }

        }

        Clusterer<Question> clusterer = new ApacheMLClusterer<>();
        List<Cluster<Question>> cluster = clusterer.cluster(vectors);
        for (int i = 0; i < cluster.size(); i++) {
            Cluster<Question> questionCluster = cluster.get(i);
            printCluster(questionCluster, i);
        }
    }

    private void printCluster(Cluster<Question> cluster, int i) {
        System.out.println(String.format("----- Cluster #%d-----", i));
        List<Clusterable<Question>> points = cluster.getPoints();
        for (Clusterable<Question> point : points) {
            Question data = point.data();
            System.out.println(String.format("Source: '%s', Target:'%s', Instructions: '%s'", data.getBody(), data.getAnswerKey().get(0), data.getInstructions()));
        }
    }
}
