package com.prodigy.domain.questions.service;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

/**
 * Created by guym on 19/05/2017.
 */
public class SearchQuery {

    private final String termsInQuestionBody;
    private final String termsInQuestionAnswer;
    private final String termsInSubject;

    public SearchQuery(String termsInQuestionBody, String termsInQuestionAnswer, String termsInSubject) {
        this.termsInQuestionBody = termsInQuestionBody;
        this.termsInQuestionAnswer = termsInQuestionAnswer;
        this.termsInSubject = termsInSubject;
    }

    public String getTermsInQuestionBody() {
        return termsInQuestionBody;
    }

    public String getTermsInQuestionAnswer() {
        return termsInQuestionAnswer;
    }

    public String getTermsInSubject() {
        return termsInSubject;
    }

    public QueryBuilder getQuery() {
        BoolQueryBuilder boolQuery = new BoolQueryBuilder();
        boolQuery.must(QueryBuilders.matchQuery("body", termsInQuestionBody))
                .must(QueryBuilders.matchQuery("answer", termsInQuestionAnswer))
                .must(QueryBuilders.matchQuery("subject", termsInSubject));
        return boolQuery;
    }

}
