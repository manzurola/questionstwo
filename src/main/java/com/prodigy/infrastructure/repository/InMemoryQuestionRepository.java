package com.prodigy.infrastructure.repository;

import com.prodigy.domain.repository.DuplicateEntryException;
import com.prodigy.domain.repository.NotFoundException;
import com.prodigy.domain.repository.QuestionRepository;
import com.prodigy.domain.Id;
import com.prodigy.domain.Question;

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
    public Question get(Id<Question> id)  {
        Question question = data.get(id);
        if (question == null) {
            throw new NotFoundException("Question with id " + id + " does not exist");
        }
        return question;
    }

    @Override
    public List<Question> getAll()  {
        return new ArrayList<>(data.values());
    }

    @Override
    public void add(Question question)  {
        if (data.containsKey(question.getId())) {
            throw new DuplicateEntryException("Question with id " + question.getId() + " already exists");
        }
        data.put(question.getId(), question);
    }

    @Override
    public void add(List<Question> questions)  {
        for (Question question : questions) {
            add(question);
        }
    }

    @Override
    public void update(Question question)  {
        Question target = get(question.getId());
        // TODO fix bug
        data.put(question.getId(), question);
    }

    @Override
    public void delete(Id<Question> id)  {
        data.remove(id);
    }

    @Override
    public List<Question> searchByAnswer(String termsInAnswer)  {
        return null;
    }
}
