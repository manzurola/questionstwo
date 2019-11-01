package com.prodigy.webapp;

import org.springframework.web.bind.annotation.RequestBody;

public interface AnswerAPI {
    Answer submitAnswer(@RequestBody Answer request);
}
