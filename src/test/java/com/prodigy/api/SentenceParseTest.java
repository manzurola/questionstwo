package com.prodigy.api;

import com.google.common.io.Files;
import com.opencsv.CSVWriter;
import com.prodigy.api.questions.Question;
import com.prodigy.api.questions.request.AddQuestionRequest;
import com.prodigy.api.test.AddQuestionRequestCSVReader;
import com.prodigy.api.test.AddQuestionRequestReader;
import com.prodigy.api.test.QuestionUtils;
import com.prodigy.ml.FeatureVector;
import com.prodigy.ml.QuestionFeatureExtractor;
import com.prodigy.ml.QuestionParse;
import com.prodigy.nlp.*;
import com.prodigy.nlp.diff.DMPTextDiffCalculator;
import edu.stanford.nlp.ling.HasWord;
import edu.stanford.nlp.ling.TaggedWord;
import edu.stanford.nlp.parser.nndep.DependencyParser;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.process.DocumentPreprocessor;
import edu.stanford.nlp.tagger.maxent.MaxentTagger;
import edu.stanford.nlp.trees.GrammaticalStructure;
import edu.stanford.nlp.trees.TypedDependency;
import name.fraser.neil.plaintext.diff_match_patch;
import org.junit.Test;

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        String modelPath = DependencyParser.DEFAULT_MODEL;
        String taggerPath = "edu/stanford/nlp/models/pos-tagger/english-left3words/english-left3words-distsim.tagger";
        MaxentTagger tagger = new MaxentTagger(taggerPath);
        DependencyParser parser = DependencyParser.loadFromModelFile(modelPath);

        QuestionFeatureExtractor featureExtractor = new QuestionFeatureExtractor(
                new StanfordSentenceParser(tagger, parser),
                new DMPTextDiffCalculator()
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


    @Test
    public void cluster() {
        String modelPath = DependencyParser.DEFAULT_MODEL;
        String taggerPath = "edu/stanford/nlp/models/pos-tagger/english-left3words/english-left3words-distsim.tagger";
        MaxentTagger tagger = new MaxentTagger(taggerPath);
        DependencyParser parser = DependencyParser.loadFromModelFile(modelPath);

        String expectedString = "Do you sleep well?";
        String actualString = "You sleep well.";

//        String expectedString = "do they often go to the pictures?";
//        String actualString = "do they go to the pictures often?";

        List<HasWord> expectedWords = new DocumentPreprocessor(new StringReader(expectedString)).iterator().next();
        List<HasWord> actualWords = new DocumentPreprocessor(new StringReader(actualString)).iterator().next();

        List<TaggedWord> expectedTagged = tagger.tagSentence(expectedWords);
        List<TaggedWord> actualTagged = tagger.tagSentence(actualWords);

        System.out.println(expectedTagged);
        System.out.println(actualTagged);

        GrammaticalStructure expectedGrammar = parser.predict(expectedTagged);
        GrammaticalStructure actualGrammar = parser.predict(actualTagged);

        for (TypedDependency next : expectedGrammar.typedDependencies()) {
            System.out.println("Relation, ShortName: " + next.reln().getShortName() + ", LongName: " + next.reln().getLongName());
            Optional<POS> depPos = POS.ofValue(next.dep().tag());
            Optional<POS> govPos = POS.ofValue(next.gov().tag());
            System.out.println("Dependent: " + next.dep() + ", tag: " + depPos.orElse(null));
            System.out.println("Governor: " + next.gov() + ", tag: " + govPos.orElse(null));
        }

        for (TypedDependency next : actualGrammar.typedDependencies()) {
            System.out.println("Relation, ShortName: " + next.reln().getShortName() + ", LongName: " + next.reln().getLongName());
            System.out.println("Dependent: " + next.dep());
            System.out.println("Governor: " + next.gov());
        }

        System.out.println(expectedGrammar.typedDependencies());
        System.out.println(actualGrammar.typedDependencies());

        diff_match_patch dmp = new diff_match_patch();

        Map<String, String> wordToChar = new HashMap<>();
        Map<String, String> charToWord = new HashMap<>();

        StringBuilder expectedChars = new StringBuilder();
        StringBuilder actualChars = new StringBuilder();

        // convert words to chars for word diff
        char c = 'a';
        for (TaggedWord taggedWord : expectedTagged) {
            String word = taggedWord.word();
            if (!wordToChar.containsKey(word)) {
                wordToChar.put(word, String.valueOf(c));
                charToWord.put(String.valueOf(c), word);
                c++;
            }
            String key = wordToChar.get(word);
            expectedChars.append(key);
        }

        for (TaggedWord taggedWord : actualTagged) {
            String word = taggedWord.word();
            if (!wordToChar.containsKey(word)) {
                wordToChar.put(word, String.valueOf(c));
                charToWord.put(String.valueOf(c), word);
                c++;
            }
            String key = wordToChar.get(word);
            actualChars.append(key);
        }

        System.out.println(expectedChars.toString());
        System.out.println(actualChars.toString());

        // revert chars to words
        LinkedList<diff_match_patch.Diff> diffs = dmp.diff_main(actualChars.toString(), expectedChars.toString());
        dmp.diff_cleanupMerge(diffs);
        System.out.println(diffs);

        // expand diffs of more than one char to multiple single char diffs
        LinkedList<diff_match_patch.Diff> expanded = new LinkedList<>();
        for (diff_match_patch.Diff diff : diffs) {
            if (diff.text.length() > 1) {
                char[] chars = diff.text.toCharArray();
                for (char aChar : chars) {
                    expanded.add(new diff_match_patch.Diff(diff.operation, String.valueOf(aChar)));
                }
            } else {
                expanded.add(diff);
            }
        }

        // revert diff text to from chars to words
        for (diff_match_patch.Diff diff : expanded) {
            diff.text = charToWord.get(diff.text);
        }


        int distance = dmp.diff_levenshtein(expanded);
        System.out.println(expanded);
        System.out.println(distance);


    }

    @Test
    public void parse() throws IOException {
        BufferedWriter writer = Files.newWriter(new File("parsed_questions.txt"), Charset.defaultCharset());
        List<AddQuestionRequest> requests = reader.readAll();
        StanfordNlpClient nlpClient = nlpClientFactory.create();
        CoreDocument document;
        AddQuestionRequest request;
        for (int i = 0; i < requests.size(); i++) {
            request = requests.get(i);
            document = nlpClient.annotate(request.getBody());


            writer.write(String.format("[%d] --- %s", i, request.getInstructions()));
            writer.newLine();
            writer.write("-body");
            writer.newLine();
            writer.write(request.getBody());
            writer.newLine();
            writer.write("-body.postags");
            writer.newLine();
            writer.write(Arrays.toString(nlpClient.posTags(document).toArray()));
            writer.newLine();
            writer.write("-body.dependencies");
            writer.newLine();
            writer.write(nlpClient.dependencyGraph(document).toString());
            writer.newLine();

            for (int j = 0; j < request.getAnswerKey().size(); j++) {
                String answer = request.getAnswerKey().get(j);
                document = nlpClient.annotate(answer);

                String key = "--answer_" + j;

                writer.write(key);
                writer.newLine();
                writer.write(answer);
                writer.newLine();
                writer.write(key + ".postags");
                writer.newLine();
                writer.write(Arrays.toString(nlpClient.posTags(document).toArray()));
                writer.newLine();
                writer.write(key + ".dependencies");
                writer.newLine();
                writer.write(nlpClient.dependencyGraph(document).toString());
                writer.newLine();
            }

            writer.newLine();
            writer.newLine();
        }

        writer.close();
    }

}
