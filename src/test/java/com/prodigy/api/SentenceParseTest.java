package com.prodigy.api;

import com.google.common.io.Files;
import com.prodigy.api.questions.request.AddQuestionRequest;
import com.prodigy.api.test.AddQuestionRequestCSVReader;
import com.prodigy.api.test.AddQuestionRequestReader;
import edu.stanford.nlp.ling.HasWord;
import edu.stanford.nlp.ling.TaggedWord;
import edu.stanford.nlp.parser.nndep.DependencyParser;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.JSONOutputter;
import edu.stanford.nlp.process.DocumentPreprocessor;
import edu.stanford.nlp.semgraph.SemanticGraph;
import edu.stanford.nlp.tagger.maxent.MaxentTagger;
import edu.stanford.nlp.trees.GrammaticalStructure;
import org.junit.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

public class SentenceParseTest {

    AddQuestionRequestReader reader;
    StanfordNlpClientFactory nlpClientFactory;

    public SentenceParseTest() throws IOException {
//        reader = new AddQuestionRequestCSVReader();
//        nlpClientFactory = new StanfordNlpClientFactory();
    }

    @Test
    public void nndp() {
        String modelPath = DependencyParser.DEFAULT_MODEL;
        String taggerPath = "edu/stanford/nlp/models/pos-tagger/english-left3words/english-left3words-distsim.tagger";

        String text = "I can almost always tell when movies use fake dinosaurs.";

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
    public void name() {
        StanfordNlpClient nlpClient = nlpClientFactory.create();
        CoreDocument document = nlpClient.annotate("a dog is happy");
        SemanticGraph dependencyGraph = nlpClient.dependencyGraph(document);
        List<String> posTags = nlpClient.posTags(document);
    }

    @Test
    public void parse() throws IOException {
        BufferedWriter writer = Files.newWriter(new File("parsed_questions.txt"), Charset.defaultCharset());
        List<AddQuestionRequest> requests = reader.readAll();
        StanfordNlpClient nlpClient = nlpClientFactory.create();
        CoreDocument document;
        AddQuestionRequest request;
        for (int i = 0; i<requests.size(); i++) {
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
