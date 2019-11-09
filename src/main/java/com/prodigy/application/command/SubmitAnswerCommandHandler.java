package com.prodigy.application.command;

import com.prodigy.domain.Answer;
import com.prodigy.domain.repository.AnswerRepository;
import com.prodigy.domain.Review;
import com.prodigy.domain.Reviewer;
import com.prodigy.domain.repository.QuestionRepository;
import org.springframework.stereotype.Component;

@Component
public class SubmitAnswerCommandHandler implements CommandHandler<SubmitAnswerRequest> {

    private final AnswerRepository repository;
    private final QuestionRepository questionRepository;
    private final Reviewer reviewer;

    public SubmitAnswerCommandHandler(AnswerRepository repository, QuestionRepository questionRepository, Reviewer reviewer) {
        this.repository = repository;
        this.questionRepository = questionRepository;
        this.reviewer = reviewer;
    }

    @Override
    public void handle(SubmitAnswerRequest request) {
        Review review = reviewer.reviewAnswer(request.getAnswer(), questionRepository.get(request.getQuestionId()));
        Answer answer = Answer.builder()
                .questionId(request.getQuestionId())
                .input(request.getAnswer())
                .review(review)
                .build();
    }

}
