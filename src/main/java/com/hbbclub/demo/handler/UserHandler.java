package com.hbbclub.demo.handler;


import com.hbbclub.demo.dao.UserRepository;
import com.hbbclub.demo.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author 南来
 * @version V1.0
 * @Description UserHandler
 * @date 2018-04-15 11:56
 */
@Component
public class UserHandler {

    private final UserRepository repository;

    @Autowired
    public UserHandler(UserRepository repository) {
        this.repository = repository;
    }

    public Mono<ServerResponse> helloUser(ServerRequest serverRequest) {
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(BodyInserters.fromObject("{\"message\":\"hello !\"}"));
    }

    public Mono<User> save(User user) {
        return repository.save(user);
    }

    public Mono<User> findUserById(Long id) {
        return repository.findById(id);
    }

    public Flux<User> findAllUser() {
        return repository.findAll();
    }

    public Mono<User> modifyUser(User user) {
        return repository.save(user);
    }

    public Mono<Long> deleteUser(Long id) {
        repository.deleteById(id);
        return Mono.create(userMonoSink -> userMonoSink.success(id));
    }
}
