package com.prodigy.api.questions.tools;


import com.prodigy.api.questions.Question;

/**
 * Created by guym on 24/05/2017.
 */
public interface QuestionParser<T extends Question> {

    T parseQuestion(String[] values);

    String getVersion();
}
