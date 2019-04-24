package com.prodigy.api.review.reviewer;

import com.prodigy.api.answers.Answer;
import com.prodigy.api.questions.Question;
import com.prodigy.api.questions.data.QuestionRepository;
import com.prodigy.api.review.Score;
import com.prodigy.api.review.request.AddReviewRequest;
import com.prodigy.nlp.*;
import com.prodigy.nlp.diff.DMPTextDiffCalculator;
import com.prodigy.nlp.diff.TextDiff;
import com.prodigy.nlp.diff.TextDiffCalculator;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import static com.prodigy.nlp.diff.TextDiff.Operation.*;

public class WordDiffReviewer implements Reviewer {

    private final SentenceParser parser = new BasicSentenceParser();
    private final TextDiffCalculator diffCalculator = new DMPTextDiffCalculator();
    private final QuestionRepository questionRepository;

    public WordDiffReviewer(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public AddReviewRequest review(Answer answer) {
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

        Explain explain = null;
        for (Sentence target : answerKey) {
            explain = explain(input, target);
            break;
        }

        return new AddReviewRequest(answer.getId(), score(explain), null, null, null, explain);
    }

    private Explain explain(Sentence source, Sentence target) {
        List<TextDiff> diff = revertLowercaseDiff(lowercaseDiff(source, target), source, target);

        return new Explain(diff.stream()
                .map(s -> new ReviewStep(getResult(s.getOperation()), s.getText()))
                .collect(Collectors.toList())
        );
    }

    private List<TextDiff> lowercaseDiff(Sentence source, Sentence target) {
        return diffCalculator.diff(
                source.getWords().stream()
                        .map(s -> s.value().toLowerCase(Locale.ROOT))
                        .collect(Collectors.toList()),
                target.getWords().stream()
                        .map(s -> s.value().toLowerCase(Locale.ROOT))
                        .collect(Collectors.toList())
        );
    }

    private List<TextDiff> revertLowercaseDiff(List<TextDiff> diffs, Sentence source, Sentence target) {
        LinkedList<Word> sourceQueue = new LinkedList<>(source.getWords());
        System.out.println(sourceQueue);
        LinkedList<Word> targetQueue = new LinkedList<>(target.getWords());
        System.out.println(targetQueue);
        List<TextDiff> result = new ArrayList<>();

        for (TextDiff diff : diffs) {
            switch (diff.getOperation()) {
                case EQUAL:
                    result.add(new TextDiff(sourceQueue.remove().value(), EQUAL));
                    targetQueue.remove();
                    break;
                case INSERT:
                    result.add(new TextDiff(targetQueue.remove().value(), INSERT));
                    break;
                case DELETE:
                    result.add(new TextDiff(sourceQueue.remove().value(), DELETE));
                    break;
            }
        }

        return result;
    }

    private Score score(Explain breakdown) {
        boolean hasDiff = false;
        for (ReviewStep step : breakdown.getSteps()) {
            if (!ReviewStep.Result.EQUAL.equals(step.getResult())) {
                hasDiff = true;
                break;
            }

        }

        int points = 1;
        if (hasDiff) {
            points = 0;
        }
        return new Score(points);
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
