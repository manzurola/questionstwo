package com.prodigy.api.questions.data;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.prodigy.api.questions.domain.Question;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by guym on 16/05/2017.
 */
public class ElasticsearchQuestionRepository implements QuestionRepository {

    private final String index = "questions_en";
    private final String mappedType = "question";
    private final TransportClient client;
    private final ObjectMapper mapper;

    public ElasticsearchQuestionRepository(TransportClient client, ObjectMapper mapper) {
        this.client = client;
        this.mapper = mapper;
    }

    @Override
    public Question get(String id) throws Exception {
        return mapper.readValue(client.prepareGet(index, mappedType, id).get().getSourceAsBytes(), Question.class);
    }

    @Override
    public List<Question> getAll() throws Exception {
        GetResponse getResponse = client.prepareGet()
                .setIndex(index)
                .setType(mappedType)
                .get();
        if (!getResponse.isExists()) return Collections.emptyList();
        return mapper.readValue(
                getResponse
                        .getSourceAsBytes(),
                new TypeReference<List<Question>>() {
                }
        );
    }

    @Override
    public void add(Question question) throws Exception {
        client.prepareIndex(index, mappedType, question.getId())
                .setSource(mapper.writeValueAsBytes(question), XContentType.JSON).get();
    }

    @Override
    public void add(List<Question> questions) throws Exception {
        for (Question question : questions) {
            add(question);
        }
    }

    @Override
    public void delete(String id) throws Exception {
        client.prepareDelete(index, mappedType, id).get();
    }

    @Override
    public void delete(List<String> ids) throws Exception {
        for (String id : ids) {
            delete(id);
        }
    }

    @Override
    public List<Question> searchByAnswer(String termsInAnswer) throws Exception {
        BoolQueryBuilder boolQuery = new BoolQueryBuilder();
        boolQuery.must(QueryBuilders.matchQuery("answerKey", termsInAnswer));
        return search(boolQuery);
    }

//    @Override
//    public List<Question> searchQuestionsByAnswerAndSubject(String termsInAnswer, String termsInSubject) throws Exception {
//        BoolQueryBuilder boolQuery = new BoolQueryBuilder();
//        boolQuery.must(QueryBuilders.matchQuery("answerKey", termsInAnswer))
//                .must(QueryBuilders.matchQuery("subject", termsInSubject));
//        return search(boolQuery);
//    }
//
//    @Override
//    public List<Question> searchQuestionsBySubject(String termsInSubject) throws Exception {
//        BoolQueryBuilder boolQuery = new BoolQueryBuilder();
//        boolQuery.must(QueryBuilders.matchQuery("subject", termsInSubject));
//        return search(boolQuery);
//    }

    private List<Question> search(QueryBuilder query) throws IOException {
        SearchResponse response = client.prepareSearch(index)
                .setTypes(mappedType)
                .setQuery(query)
                .get();
        List<Question> results = new ArrayList<>();
        for (SearchHit searchHit : response.getHits()) {
            results.add(mapper.readValue(searchHit.getSourceAsString(), Question.class));
        }

        return results;
    }
}
