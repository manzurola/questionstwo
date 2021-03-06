package com.prodigy.questions.database;

import com.prodigy.common.data.Id;
import com.prodigy.common.data.NotFoundException;
import com.prodigy.questions.domain.Question;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryQuestionRepository implements QuestionRepository {

    private final Map<Id<Question>, Question> data = new HashMap<>();

    public InMemoryQuestionRepository(List<Question> data) {
        for (Question question : data) {
            this.data.put(question.getId(), question);
        }
    }

    @Override
    public Question get(Id<Question> id) throws Exception {
        Question question = data.get(id);
        if (question == null) {
            throw new NotFoundException("Question with id " + id + " does not exist");
        }
        return question;
    }

    @Override
    public List<Question> getAll() throws Exception {
        return new ArrayList<>(data.values());
    }

    @Override
    public Question add(Question question) throws Exception {
        if (data.containsKey(question.getId())) {
            throw new RuntimeException("Question with id " + question.getId() + " already exists");
        }
        data.put(question.getId(), question);
        return question;
    }

    @Override
    public void add(List<Question> questions) throws Exception {
        for (Question question : questions) {
            add(question);
        }
    }

    @Override
    public void update(Question question) throws Exception {
        Question target = get(question.getId());
        // TODO fix bug
        data.put(question.getId(), question);
    }

    @Override
    public void delete(Id<Question> id) throws Exception {
        data.remove(id);
    }

    @Override
    public List<Question> searchByAnswer(String termsInAnswer) throws Exception {
        return null;
    }
}
