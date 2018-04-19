package com.hbbclub.demo.dao;

import com.hbbclub.demo.domain.User;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

/**
 * @author 南来
 * @version V1.0
 * @Description UserRepository
 * @date 2018-04-15 12:16
 */
@Repository
public interface UserRepository extends ReactiveMongoRepository<User, Long> {

    @Query("{ 'name': ?0, 'age': ?1}")
    Mono<User> findByNameAndAge(String name, String age);
}
