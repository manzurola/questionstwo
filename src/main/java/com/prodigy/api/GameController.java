package com.prodigy.api;

import com.prodigy.api.answers.Answer;
import com.prodigy.api.answers.SubmitAnswerCommand;
import com.prodigy.api.answers.SubmitAnswerRequest;
import com.prodigy.api.common.Id;
import com.prodigy.api.common.service.Result;
import com.prodigy.api.common.service.ServiceExecutor;
import com.prodigy.api.game.GameEvent;
import com.prodigy.api.game.GameSession;
import com.prodigy.api.questions.Question;
import com.prodigy.api.questions.command.GetQuestionCommand;
import com.prodigy.api.questions.request.GetQuestionRequest;
import com.prodigy.api.review.Review;
import com.prodigy.api.review.command.AddReviewCommand;
import com.prodigy.api.review.command.SuggestReviewCommand;
import com.prodigy.api.review.request.AddReviewRequest;
import com.prodigy.api.review.request.SuggestReviewRequest;
import com.prodigy.api.users.User;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/game")
public class GameController {

    private final ServiceExecutor serviceExecutor;

    public GameController(ServiceExecutor serviceExecutor) {
        this.serviceExecutor = serviceExecutor;
    }

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

    @PutMapping("/create")
    public Id<GameSession> createGame(Id<User> userId) {
        // validate user and session id
        // return stream of events
        return null;
    }

    @PutMapping("/join")
    public void joinGame(Id<GameSession> sessionId, Id<User> userId) {
        // validate user and session id
        // return stream of events
    }

    @GetMapping("/stream")
    public Flux<ServerSentEvent<GameEvent>> streamGame(Id<GameSession> sessionId) {
        // validate user and session id
        // return stream of events
        return null;
    }


    @GetMapping("/sessions")
    public List<Id<GameSession>> listSessions() {
        return null;
    }

    @PostMapping("/game/{id}/submit")
    public void submitAnswer(@RequestBody SubmitAnswerRequest request) {
        Answer answer = serviceExecutor.execute(
                SubmitAnswerCommand.class,
                request
        ).getData();
        System.out.println(answer);
        Question question = serviceExecutor.execute(
                GetQuestionCommand.class,
                new GetQuestionRequest(answer.getQuestionId())
        ).getData();
        System.out.println(question);
        AddReviewRequest addReviewRequest = serviceExecutor.execute(
                SuggestReviewCommand.class,
                new SuggestReviewRequest(question, answer)
        ).getData();
        System.out.println(addReviewRequest);
        Result<Review> review = serviceExecutor.execute(
                AddReviewCommand.class,
                addReviewRequest
        );
        System.out.println(review);

        // notify game of review, update game state and send event to clients
    }

}