package com.prodigy.answers.service;

import com.prodigy.answers.domain.Answer;
import com.prodigy.answers.database.AnswerRepository;
import com.prodigy.common.service.ServiceCommand;
import com.prodigy.review.Reviewer;
import com.prodigy.common.service.AbstractServiceCommand;
import com.prodigy.questions.database.QuestionRepository;
import com.prodigy.review.Review;
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
