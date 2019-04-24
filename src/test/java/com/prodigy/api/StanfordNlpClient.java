package com.prodigy.api;

import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.semgraph.SemanticGraph;
import edu.stanford.nlp.trees.Tree;

import java.util.List;

public class StanfordNlpClient {

    private final StanfordCoreNLP pipeline;

    public StanfordNlpClient(StanfordCoreNLP pipeline) {
        this.pipeline = pipeline;
    }

    public CoreDocument annotate(String text) {
        CoreDocument document = new CoreDocument(text);
        pipeline.annotate(document);
        return document;
    }

    public List<String> posTags(CoreDocument document) {
        return document.sentences().get(0).posTags();
    }

    public List<String> nerTags(CoreDocument document) {
        return document.sentences().get(0).nerTags();
    }

    public Tree constituencyParse(CoreDocument document) {
        return document.sentences().get(0).constituencyParse();
    }

    public SemanticGraph dependencyGraph(CoreDocument document) {
        return document.sentences().get(0).dependencyParse();
    }
}
