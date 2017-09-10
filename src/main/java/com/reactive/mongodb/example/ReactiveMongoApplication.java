package com.reactive.mongodb.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.reactive.mongodb.example.routing.PersonRouter;

@SpringBootApplication(exclude = EmbeddedMongoAutoConfiguration.class)
public class ReactiveMongoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReactiveMongoApplication.class, args);
    }

    @Bean
    public RouterFunction<ServerResponse> monoRouterFunction(PersonRouter personRouter) {
        return personRouter.routes();
    }

}
