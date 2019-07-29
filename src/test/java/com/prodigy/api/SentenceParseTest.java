package com.prodigy.api;

import com.opencsv.CSVWriter;
import com.prodigy.api.questions.Question;
import com.prodigy.api.questions.request.AddQuestionRequest;
import com.prodigy.api.questions.utils.AddQuestionRequestCSVReader;
import com.prodigy.api.questions.utils.AddQuestionRequestReader;
import com.prodigy.api.questions.utils.QuestionUtils;
import com.prodigy.ml.FeatureVector;
import com.prodigy.ml.QuestionFeatureExtractor;
import com.prodigy.ml.clustering.ApacheMLClusterer;
import com.prodigy.ml.clustering.Cluster;
import com.prodigy.ml.clustering.Clusterer;
import com.prodigy.nlp.POS;
import com.prodigy.nlp.StanfordSentenceParser;
import com.prodigy.nlp.diff.DMPTextDiffCalculator;
import com.prodigy.nlp.diff.GrammaticalDiffCheck;
import edu.stanford.nlp.ling.HasWord;
import edu.stanford.nlp.ling.TaggedWord;
import edu.stanford.nlp.parser.nndep.DependencyParser;
import edu.stanford.nlp.process.DocumentPreprocessor;
import edu.stanford.nlp.tagger.maxent.MaxentTagger;
import edu.stanford.nlp.trees.GrammaticalStructure;
import org.apache.commons.math3.ml.clustering.Clusterable;
import org.apache.commons.math3.ml.distance.DistanceMeasure;
import org.apache.commons.math3.ml.distance.ManhattanDistance;
import org.junit.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.util.*;
import java.util.stream.Collectors;

public class SentenceParseTest {

    AddQuestionRequestReader reader;
    StanfordNlpClientFactory nlpClientFactory;

    public SentenceParseTest() throws IOException {
        reader = new AddQuestionRequestCSVReader();
//        nlpClientFactory = new StanfordNlpClientFactory();
    }

    @Test
    public void nndp() {
        String modelPath = DependencyParser.DEFAULT_MODEL;
        String taggerPath = "edu/stanford/nlp/models/pos-tagger/english-left3words/english-left3words-distsim.tagger";

//        String text = "I can almost always tell when movies use fake dinosaurs.";
        String text = "I";

        MaxentTagger tagger = new MaxentTagger(taggerPath);
        DependencyParser parser = DependencyParser.loadFromModelFile(modelPath);

        DocumentPreprocessor tokenizer = new DocumentPreprocessor(new StringReader(text));
        for (List<HasWord> sentence : tokenizer) {
            List<TaggedWord> tagged = tagger.tagSentence(sentence);
            GrammaticalStructure gs = parser.predict(tagged);
            // Print typed dependencies
            System.out.println(gs);
        }
    }

    @Test
    public void name() throws IOException {
        String taggerPath = "edu/stanford/nlp/models/pos-tagger/english-left3words/english-left3words-distsim.tagger";
        MaxentTagger tagger = new MaxentTagger(taggerPath);
        String modelPath = DependencyParser.DEFAULT_MODEL;
        DependencyParser parser = DependencyParser.loadFromModelFile(modelPath);

        QuestionFeatureExtractor featureExtractor = new QuestionFeatureExtractor(
                new GrammaticalDiffCheck(new DMPTextDiffCalculator(), new StanfordSentenceParser(tagger, parser))
        );

        CSVWriter writer = new CSVWriter(new FileWriter("data_features.csv"));

        String[] title = new String[POS.values().length];
        for (POS value : POS.values()) {
            title[value.ordinal()] = value.name();
        }

        writer.writeNext(title);

        QuestionUtils questionUtils = new QuestionUtils();
        List<AddQuestionRequest> requests = questionUtils.getQuestions();
        for (AddQuestionRequest request : requests) {
            Question question = questionUtils.newQuestionFromRequest(request).build();
            FeatureVector<Question> features = featureExtractor.extract(question);

            List<String> collect = Arrays.stream(features.getPoint()).boxed()
                    .map(String::valueOf)
                    .collect(Collectors.toList());

            String[] strings = collect.toArray(new String[collect.size()]);

            writer.writeNext(strings);
        }

        writer.close();


    }

    //    private Clusterable clusterable(Question question, MaxentTagger tagger, DependencyParser parser, diff_match_patch dmp) {
//
//        String source = question.getBody();
//        String target = question.getAnswerKey().get(0);
//
//        List<HasWord> sourceWords = new DocumentPreprocessor(new StringReader(source)).iterator().next();
//        List<HasWord> targetWords = new DocumentPreprocessor(new StringReader(target)).iterator().next();
//
//
//        List<TaggedWord> sourceTagged = tagger.tagSentence(sourceWords);
//        List<TaggedWord> targetTagged = tagger.tagSentence(targetWords);
//
//        GrammaticalStructure sourceGrammar = parser.predict(sourceTagged);
//        GrammaticalStructure targetGrammar = parser.predict(targetTagged);
//
//
//
//    }


    private static class ClusterableWrapper<T> implements Clusterable {

        private final FeatureVector<T> vector;

        private ClusterableWrapper(FeatureVector<T> vector) {
            this.vector = vector;
        }

        public FeatureVector<T> getVector() {
            return vector;
        }

        @Override
        public double[] getPoint() {
            return vector.getPoint();
        }
    }

    @Test
    public void testCommonsCluster() throws Exception {
        // initialize a new clustering algorithm.
// we use KMeans++ with 10 clusters and 10000 iterations maximum.
// we did not specify a distance measure; the default (euclidean distance) is used.

        String modelPath = DependencyParser.DEFAULT_MODEL;
        String taggerPath = "edu/stanford/nlp/models/pos-tagger/english-left3words/english-left3words-distsim.tagger";
        MaxentTagger tagger = new MaxentTagger(taggerPath);
        DependencyParser parser = DependencyParser.loadFromModelFile(modelPath);

        QuestionFeatureExtractor featureExtractor = new QuestionFeatureExtractor(
                new GrammaticalDiffCheck(new DMPTextDiffCalculator(), new StanfordSentenceParser(tagger, parser))
        );


        QuestionUtils questionUtils = new QuestionUtils();
        LinkedList<AddQuestionRequest> requests = new LinkedList<>(reader.readAll());
        requests.poll();

        List<FeatureVector<Question>> vectors = new ArrayList<>();
        for (AddQuestionRequest request : requests) {
            Question.Builder builder = questionUtils.newQuestionFromRequest(request);
            Question question = builder.build();
            FeatureVector<Question> features = featureExtractor.extract(question);
            System.out.println(features);
            vectors.add(features);
        }

        DistanceMeasure distance = new ManhattanDistance();
        Clusterer<Question> clusterer = new ApacheMLClusterer<>();

        List<Cluster<Question>> clusterResults = clusterer.cluster(vectors);
        for (int i = 0; i < clusterResults.size(); i++) {
            Cluster<Question> cluster = clusterResults.get(i);
            System.out.println("Cluster " + i + ": " + Arrays.toString(cluster.getCenter().getPoint()));
        }

        List<Question> realAnswers = vectors.stream().map(FeatureVector::getData).collect(Collectors.toList());
        List<Question> answers = new ArrayList<>();


        Question answer = Question.builder()
                .answerKey("I went home.")
                .body("I am going home.")
                .build();
        answers.add(answer);


        for (Question realAnswer : answers) {
            FeatureVector<Question> vector = featureExtractor.extract(realAnswer);
            System.out.println(vector);
            ClusterableWrapper<Question> input = new ClusterableWrapper<>(vector);
            double topScore = Integer.MAX_VALUE;
            int closest = 0;
            for (int i = 0; i < clusterResults.size(); i++) {
                Cluster<Question> cluster = clusterResults.get(i);
                FeatureVector<Question> center = cluster.getCenter();
                if (center.getPoint().length > 0) {
                    double compute = distance.compute(input.getVector().getPoint(), center.getPoint());
                    if (compute < topScore) {
                        topScore = compute;
                        closest = i;
                    }
//                    System.out.println("Distance from center " + i + " is " + compute);
                } else {
                    System.out.println("Empty cluster " + i);
                }
            }

            Cluster<Question> clusterMatch = clusterResults.get(closest);
            List<Question> matches = clusterMatch.getPoints()
                    .stream()
                    .map(FeatureVector::getData)
                    .collect(Collectors.toList());
            Question match1 = matches.get(0);
            Question match2 = matches.get(1);
            Question match3 = matches.get(2);


            System.out.println(String.format("Actual: source: '%s', target: '%s'", realAnswer.getBody(), realAnswer.getAnswerKey().get(0)));
            System.out.println(String.format("  Best match with score of [%4.3f]", topScore));
            System.out.println(String.format("  Match 1: source: '%s', target: '%s'", match1.getBody(), match1.getAnswerKey().get(0)));
            System.out.println(String.format("  Match 2: source: '%s', target: '%s'", match2.getBody(), match2.getAnswerKey().get(0)));
            System.out.println(String.format("  Match 3: source: '%s', target: '%s'", match3.getBody(), match3.getAnswerKey().get(0)));
        }


    }

}
