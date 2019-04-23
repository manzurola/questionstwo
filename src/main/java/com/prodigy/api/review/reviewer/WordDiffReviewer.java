package com.prodigy.api.review.reviewer;

import com.prodigy.api.answers.Answer;
import com.prodigy.api.questions.Question;
import com.prodigy.api.questions.data.QuestionRepository;
import com.prodigy.api.review.request.AddReviewRequest;
import com.prodigy.nlp.BasicSentenceParser;
import com.prodigy.nlp.Sentence;
import com.prodigy.nlp.SentenceParser;
import com.prodigy.nlp.Word;
import com.prodigy.nlp.diff.DMPTextDiffCalculator;
import com.prodigy.nlp.diff.TextDiff;
import com.prodigy.nlp.diff.TextDiffCalculator;
import name.fraser.neil.plaintext.diff_match_patch;

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

        for (Sentence target : answerKey) {
            List<ReviewStep> steps = diff(input, target);
        }

        return null;
    }

    private List<ReviewStep> diff(Sentence source, Sentence target) {
        List<TextDiff> diff = diffCalculator.diff(
                source.getWords().stream()
                        .map(Word::word)
                        .collect(Collectors.toList()),
                target.getWords().stream()
                        .map(Word::word)
                        .collect(Collectors.toList())
        );

        return diff.stream()
                .map(s-> new ReviewStep(getResult(s.getOperation()), new Word(s.getText())))
                .collect(Collectors.toList());
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
