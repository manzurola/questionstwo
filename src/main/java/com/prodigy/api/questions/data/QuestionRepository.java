package com.prodigy.api.questions.data;


import com.prodigy.api.common.Id;
import com.prodigy.api.questions.Question;

import java.util.List;

/**
 * Created by guym on 16/05/2017.
 */
public interface QuestionRepository {

    Question get(Id<Question> id) throws Exception;

    List<Question> getAll() throws Exception;

    Question add(Question question) throws Exception;

    void add(List<Question> questions) throws Exception;

    void update(Question question) throws Exception;

    void delete(Id<Question> id) throws Exception;

    List<Question> searchByAnswer(String termsInAnswer) throws Exception;

//    <Question extends Question> List<Question> searchQuestionsByAnswerAndSubject(String termsInAnswer, String termsInSubject) throws Exception;
//
//    <Question extends Question> List<Question> searchQuestionsBySubject(String termsInSubject) throws Exception;
}
