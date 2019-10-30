package com.prodigy.webapp.api.calls;

import com.prodigy.domain.Question;
import com.prodigy.domain.questions.service.AddQuestionRequest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.net.URL;

public class AddQuestionApiCall implements ApiCall<Question, AddQuestionRequest> {

    @Override
    public ResponseEntity<Question> run(AddQuestionRequest request, TestRestTemplate template, URL baseUrl) {
        ResponseEntity<Question> response = template.exchange(
                baseUrl.toString() + "/questions",
                HttpMethod.POST,
                new HttpEntity<>(request),
                new ParameterizedTypeReference<Question>() {
                });

        return response;
    }
}
