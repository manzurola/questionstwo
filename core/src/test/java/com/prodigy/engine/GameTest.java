package com.prodigy.engine;

import com.prodigy.engine.diff.DMPDiffCalculator;
import com.prodigy.engine.diff.Diff;
import com.prodigy.engine.diff.DiffCalculator;
import com.prodigy.engine.nlp.GrammaticalRelationsParser;
import com.prodigy.engine.nlp.StanfordGrammaticalRelationsParser;
import com.prodigy.engine.parse.SentenceParserImpl;
import com.prodigy.engine.tokenize.TaggingTokenizer;
import com.prodigy.game.*;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.List;

public class GameTest {

    @Test
    public void reviewAnswer() {
        TaggingTokenizer tokenizer = new TaggingTokenizer();
        GrammaticalRelationsParser parser = new StanfordGrammaticalRelationsParser();
        SentenceParserImpl sentenceParser = new SentenceParserImpl(tokenizer, parser);
        String source = "I am super focused.";
        String target = "Am I super focused?";
        String input = "Am I super focusing?";
        Id<User> userId = Id.next();

        Question question = Question.newBuilder()
                .description("Convert into the interrogative.")
                .source(sentenceParser.parse(source))
                .targets(sentenceParser.parse(target))
                .build();

        Answer answer = Answer.newBuilder()
                .input(sentenceParser.parse(input))
                .startTime(LocalDateTime.now())
                .endTime(LocalDateTime.now().plusMinutes(1))
                .userId(userId)
                .build();

        DiffCalculator diffCalculator = new DMPDiffCalculator();
        List<Diff<Word>> diff = diffCalculator.getDiff(answer.getInput().getWords(), question.getTargets().get(0).getWords());

        System.out.println(question);
        System.out.println(answer);
        System.out.println(diff);

    }
}
