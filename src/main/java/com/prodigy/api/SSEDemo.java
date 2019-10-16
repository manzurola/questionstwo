package com.prodigy.api;

import com.prodigy.common.data.Id;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.LocalTime;

@RestController
public class SSEDemo {



    @GetMapping(path = "/stream-flux", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> streamFlux() {
        return Flux.interval(Duration.ofSeconds(1))
                .map(sequence -> "Flux - " + LocalTime.now().toString());
    }

    @GetMapping("/stream-sse")
    public Flux<ServerSentEvent<String>> streamEvents() {
        return Flux.interval(Duration.ofSeconds(1))
                .map(message -> ServerSentEvent.<String>builder()
                        .id(String.valueOf(message))
                        .event("periodic-event")
                        .data("SSE - " + LocalTime.now().toString())
                        .build());
    }

//    @GetMapping("/stream-sse-2")
//    public Flux<ServerSentEvent<String>> streamEventsFromCollection() {
//        return processor.map(message -> ServerSentEvent.<String>builder()
//                .id(String.valueOf(message))
//                .event("periodic-event")
//                .data("SSE - " + LocalTime.now().toString())
//                .build()
//        );
//    }

    @GetMapping("/add-event")
    public void addEvent() {
    }

    public static class Game {


    }

    public static class ChatMessage {
        private final Id<ChatMessage> id;
        private final String message;

        public ChatMessage(Id<ChatMessage> id, String message) {
            this.id = id;
            this.message = message;
        }

        public Id<ChatMessage> getId() {
            return id;
        }

        public String getMessage() {
            return message;
        }

    }
}