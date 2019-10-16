package com.prodigy.answers.command;

import com.prodigy.answers.Answer;
import com.prodigy.answers.request.SubmitAnswerRequest;
import com.prodigy.answers.data.AnswerRepository;
import com.prodigy.review.Reviewer;
import com.prodigy.common.service.AbstractCommand;
import com.prodigy.questions.data.QuestionRepository;
import com.prodigy.review.Review;
import org.springframework.stereotype.Component;

@Component
public class SubmitAnswerCommand extends AbstractCommand<Answer, SubmitAnswerRequest> {

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
