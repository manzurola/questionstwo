package com.prodigy.api.test;

import com.prodigy.api.review.Review;
import com.prodigy.api.review.request.GetReviewRequest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.net.URL;

public class GetReviewApiCall implements ApiCall<Review, GetReviewRequest> {

    @Override
    public ResponseEntity<Review> run(GetReviewRequest request, TestRestTemplate template, URL baseUrl) {
        return template.exchange(
                baseUrl.toString() + "/reviews?answerId={answerId}",
                HttpMethod.GET,
                HttpEntity.EMPTY,
                new ParameterizedTypeReference<Review>() {
                },
                request.getAnswerId().getId()
        );
    }
}
