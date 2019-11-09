package com.prodigy.domain.repository;


import com.prodigy.domain.Id;
import com.prodigy.domain.Question;

import java.util.List;

/**
 * Created by guym on 16/05/2017.
 */
public interface QuestionRepository {

    Question get(Id<Question> id);

    List<Question> getAll();

    void add(Question question);

    void add(List<Question> questions);

    void update(Question question);

    void delete(Id<Question> id);

    List<Question> searchByAnswer(String termsInAnswer);

//    <Question extends Question> List<Question> searchQuestionsByAnswerAndSubject(String termsInAnswer, String termsInSubject) throws Exception;
//
//    <Question extends Question> List<Question> searchQuestionsBySubject(String termsInSubject) throws Exception;
}
