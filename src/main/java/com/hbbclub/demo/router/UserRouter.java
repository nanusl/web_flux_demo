package com.hbbclub.demo.router;

import com.hbbclub.demo.handler.UserHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

/**
 * @author 南来
 * @version V1.0
 * @Description UserRouter
 * @date 2018-04-15 12:04
 */
@Configuration
public class UserRouter {

    @Bean
    public RouterFunction<ServerResponse> routeUser(UserHandler userHandler) {
        return RouterFunctions.route(RequestPredicates.GET("/hello")
                .and(RequestPredicates.accept(MediaType.TEXT_PLAIN)), userHandler::helloUser);
    }
}
