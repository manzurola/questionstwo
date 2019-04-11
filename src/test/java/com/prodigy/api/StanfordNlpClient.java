package com.prodigy.api;

import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;

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
}
