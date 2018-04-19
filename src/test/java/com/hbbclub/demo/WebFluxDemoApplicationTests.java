package com.hbbclub.demo;

import com.hbbclub.demo.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.client.ReactorNettyWebSocketClient;
import org.springframework.web.reactive.socket.client.WebSocketClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.time.Duration;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WebFluxDemoApplicationTests {


    @Test
    public void testCreateUser() {
        final User user = new User();
        user.setName("Test");
        user.setAge(22);
        user.setDescription("test...");
        String result = WebTestClient.bindToServer().baseUrl("http://localhost:8080").build().post().uri("/user")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(Mono.just(user), User.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody().returnResult().toString();

        System.out.println("toString = " + result);

    }

    @Test
    public void testWs() {
        final WebSocketClient webSocketClient = new ReactorNettyWebSocketClient();
        webSocketClient.execute(URI.create("ws://localhost:8080/echo"), session ->
                session.send(Flux.just(session.textMessage("Hello")))
                        .thenMany(session.receive().take(1).map(WebSocketMessage::getPayloadAsText))
                        .doOnNext(System.out::println)
                        .then())
                .block(Duration.ofMillis(5000));
    }

}
