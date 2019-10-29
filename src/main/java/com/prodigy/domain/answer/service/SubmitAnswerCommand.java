package com.prodigy.domain.answer.service;

import com.prodigy.domain.answer.Answer;
import com.prodigy.domain.answer.AnswerRepository;
import com.prodigy.domain.review.Reviewer;
import com.prodigy.common.service.AbstractServiceCommand;
import com.prodigy.domain.questions.database.QuestionRepository;
import com.prodigy.domain.review.Review;
import org.springframework.stereotype.Component;

@Component
public class SubmitAnswerCommand extends AbstractServiceCommand<Answer, SubmitAnswerRequest> {

    private final AnswerRepository repository;
    private final QuestionRepository questionRepository;
    private final Reviewer reviewer;

    public SubmitAnswerCommand(AnswerRepository repository, QuestionRepository questionRepository, Reviewer reviewer) {
        this.repository = repository;
        this.questionRepository = questionRepository;
        this.reviewer = reviewer;
    }

    @Override
    protected Answer doExecute(SubmitAnswerRequest request) throws Exception {
        Review review = reviewer.reviewAnswer(request.getAnswer(), questionRepository.get(request.getQuestionId()));
        Answer answer = Answer.builder()
                .questionId(request.getQuestionId())
                .input(request.getAnswer())
                .review(review)
                .build();
        return repository.addAnswer(answer);
    }
}
