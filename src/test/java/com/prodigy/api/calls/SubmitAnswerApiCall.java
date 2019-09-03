package com.prodigy.api.calls;

import com.prodigy.domain.answers.Answer;
import com.prodigy.domain.answers.request.SubmitAnswerRequest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.net.URL;

public class SubmitAnswerApiCall implements  ApiCall<Answer, SubmitAnswerRequest> {
    @Override
    public ResponseEntity<Answer> run(SubmitAnswerRequest submitAnswerRequest, TestRestTemplate template, URL baseUrl) {

        return template.exchange(
                baseUrl.toString() + "/questions/{questionId}/answer",
                HttpMethod.POST,
                new HttpEntity<>(submitAnswerRequest),
                new ParameterizedTypeReference<Answer>() {},
                submitAnswerRequest.getQuestionId().getId());
    }
}
