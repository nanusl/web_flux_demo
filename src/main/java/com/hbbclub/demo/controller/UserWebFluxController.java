package com.hbbclub.demo.controller;

import com.hbbclub.demo.domain.User;
import com.hbbclub.demo.handler.UserHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuples;

import java.time.Duration;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author 南来
 * @version V1.0
 * @Description UserWebFluxController
 * @date 2018-04-15 13:03
 */
@RestController
@RequestMapping(value = "/user")
public class UserWebFluxController {

    @Autowired
    private UserHandler userHandler;

    @GetMapping(value = "/{id}")
    public Mono<User> findUserById(@PathVariable("id") Long id) {
        return userHandler.findUserById(id);
    }

    @GetMapping
    public Flux<User> findAll() {
        return userHandler.findAllUser();
    }

    @PostMapping
    public Mono<User> saveUser(@RequestBody User user) {
        return userHandler.save(user);
    }

    @PutMapping
    public Mono<User> modifyUser(@RequestBody User user) {
        return userHandler.modifyUser(user);
    }

    @DeleteMapping(value = "/{id}")
    public Mono<Long> deleteUser(@PathVariable("id") Long id) {
        return userHandler.deleteUser(id);
    }

    /**
     * 服务器推送事件
     */
    @GetMapping("/randomNumbers")
    public Flux<ServerSentEvent<Integer>> randomNumbers() {
        return Flux.interval(Duration.ofSeconds(1))
                .map(seq -> Tuples.of(seq, ThreadLocalRandom.current().nextInt()))
                .map(data -> ServerSentEvent.<Integer>builder()
                        .event("random")
                        .id(Long.toString(data.getT1()))
                        .data(data.getT2())
                        .build());
    }

}
