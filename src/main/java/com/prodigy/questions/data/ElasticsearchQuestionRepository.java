package com.prodigy.questions.data;

import com.prodigy.common.data.DataStore;
import com.prodigy.common.data.Id;
import com.prodigy.questions.Question;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by guym on 16/05/2017.
 */
public class ElasticsearchQuestionRepository implements QuestionRepository {

    public static final String index = "questions_en";
    public static final String type = "question";
    private final DataStore dataStore;

    public ElasticsearchQuestionRepository(DataStore dataStore) {
        this.dataStore = dataStore;
    }

    @Override
    public Question get(Id<Question> id) throws Exception {
        return dataStore.get(index, type, id, Question.class);
    }

    @Override
    public List<Question> getAll() throws Exception {
        return dataStore.getAll(index, type, Question.class);
    }

    @Override
    public Question add(Question question) throws Exception {
        dataStore.add(index, type, question.getId(), question);
        return dataStore.get(index, type, question.getId(), Question.class);
    }

    @Override
    public void add(List<Question> questions) throws Exception {
        for (Question question : questions) {
            add(question);
        }
    }

    @Override
    public void update(Question question) throws Exception {
        dataStore.update(index, type, question.getId(), question);
    }

    @Override
    public void delete(Id<Question> id) throws Exception {
        dataStore.delete(index, type, id, Question.class);
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
//        return getByProperty(boolQuery);
//    }
//
//    @Override
//    public List<Question> searchQuestionsBySubject(String termsInSubject) throws Exception {
//        BoolQueryBuilder boolQuery = new BoolQueryBuilder();
//        boolQuery.must(QueryBuilders.matchQuery("subject", termsInSubject));
//        return getByProperty(boolQuery);
//    }

    private List<Question> search(QueryBuilder query) throws IOException {
//        SearchResponse response = client.prepareSearch(index)
//                .setTypes(type)
//                .setQuery(query)
//                .get();
        List<Question> results = new ArrayList<>();
//        for (SearchHit searchHit : response.getHits()) {
//            results.add(mapper.readValue(searchHit.getSourceAsString(), Question.class));
//        }

        return results;
    }
}
