package com.prodigy.api.review.reviewer;

import com.prodigy.api.answers.Answer;
import com.prodigy.api.questions.Question;
import com.prodigy.api.questions.data.QuestionRepository;
import com.prodigy.api.review.Score;
import com.prodigy.api.review.request.AddReviewRequest;
import com.prodigy.nlp.BasicSentenceParser;
import com.prodigy.nlp.Sentence;
import com.prodigy.nlp.SentenceParser;
import com.prodigy.nlp.Word;
import com.prodigy.nlp.diff.DMPTextDiffCalculator;
import com.prodigy.nlp.diff.TextDiff;
import com.prodigy.nlp.diff.TextDiffCalculator;

import java.util.List;
import java.util.stream.Collectors;

public class WordDiffReviewer implements Reviewer {

    private final SentenceParser parser = new BasicSentenceParser();
    private final TextDiffCalculator diffCalculator = new DMPTextDiffCalculator();

    private final QuestionRepository questionRepository;

    public WordDiffReviewer(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public AddReviewRequest review(Answer answer) {
        // get answer key for question
        // input as tokenize answer.input
        // for each answer in answerKeyPermuations
        //      if input equals answer return

        // input permutations
        Sentence input = parser.parse(answer.getInput());
        Question question;
        try {
            question = questionRepository.get(answer.getQuestionId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        List<Sentence> answerKey = question.getAnswerKey().stream()
                .map(parser::parse)
                .collect(Collectors.toList());

        Breakdown breakdown = null;
        for (Sentence target : answerKey) {
            breakdown = breakdown(input, target);
            break;
        }
        return new AddReviewRequest(answer.getId(), score(breakdown), null, null, null, breakdown);
    }

    private Breakdown breakdown(Sentence source, Sentence target) {
        List<TextDiff> diff = diffCalculator.diff(
                source.getWords().stream()
                        .map(Word::word)
                        .collect(Collectors.toList()),
                target.getWords().stream()
                        .map(Word::word)
                        .collect(Collectors.toList())
        );

        return new Breakdown(source.getValue(), target.getValue(), diff.stream()
                .map(s -> new ReviewStep(getResult(s.getOperation()), s.getText()))
                .collect(Collectors.toList())
        );
    }

    private Score score(Breakdown breakdown) {
        return new Score(1);
    }

    private ReviewStep.Result getResult(TextDiff.Operation source) {
        switch (source) {
            case EQUAL:
                return ReviewStep.Result.EQUAL;
            case DELETE:
                return ReviewStep.Result.DELETE;
            case INSERT:
                return ReviewStep.Result.INSERT;
            default:
                throw new RuntimeException("Invalid operation mapping " + source);
        }
    }

}
