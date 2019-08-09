package com.prodigy.api.calls;

import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import java.net.URL;

public interface ApiCall<RESULT, REQUEST> {

    ResponseEntity<RESULT> run(REQUEST request, TestRestTemplate template, URL baseUrl);
}
