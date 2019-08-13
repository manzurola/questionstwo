package com.prodigy.core.nlp;

import com.prodigy.core.POS;
import com.prodigy.core.Word;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.ling.TaggedWord;
import edu.stanford.nlp.process.CoreLabelTokenFactory;
import edu.stanford.nlp.process.PTBTokenizer;
import edu.stanford.nlp.tagger.maxent.MaxentTagger;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class SentenceParser {

    private final MaxentTagger tagger;

    public SentenceParser(MaxentTagger tagger) {
        this.tagger = tagger;
    }

    /**
     * Tokenizer and POS tagger combined. Preprocceses sentence by trimming and collapsing redundant spaces.
     * @param sentence
     * @return
     */
    public List<Word> parse(String sentence) {
        sentence = sentence.trim().replaceAll(" +", " ");

        List<CoreLabel> tokens = new PTBTokenizer<>(new StringReader(sentence), new CoreLabelTokenFactory(true), "invertible").tokenize();

        List<TaggedWord> taggedTokens = tagger.tagSentence(tokens);

        List<Word> words = new ArrayList<>();
        for (int i = 0; i < tokens.size(); i++) {
            CoreLabel token = tokens.get(i);
            TaggedWord taggedToken = taggedTokens.get(i);
            String value = taggedToken.word();
            POS pos = POS.ofValue(taggedToken.tag()).orElse(null);
            String original = value.concat(token.after());
            words.add(new Word(value, pos, original));
        }
        return words;
    }
}
