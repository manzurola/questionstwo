package com.prodigy.webapp.api.calls;

import com.prodigy.domain.Question;
import com.prodigy.application.command.AddQuestionCommand;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.net.URL;

public class AddQuestionApiCall implements ApiCall<Question, AddQuestionCommand> {

    @Override
    public ResponseEntity<Question> run(AddQuestionCommand request, TestRestTemplate template, URL baseUrl) {
        ResponseEntity<Question> response = template.exchange(
                baseUrl.toString() + "/questions",
                HttpMethod.POST,
                new HttpEntity<>(request),
                new ParameterizedTypeReference<Question>() {
                });

        return response;
    }
}
