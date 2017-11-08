package com.reactive.mongodb.example.routing;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RequestPredicates.path;
import static org.springframework.web.reactive.function.server.RouterFunctions.nest;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.reactive.mongodb.example.model.Person;
import com.reactive.mongodb.example.services.PersonService;

import reactor.core.publisher.Mono;

@Component
public class PersonRouter {

    @Autowired
    private PersonService personService;

    public RouterFunction<ServerResponse> routes() {
        return nest(path("/person"), route(GET("/list").and(accept(APPLICATION_JSON)), this::listAll)
                .andRoute(GET("/{id}").and(accept(APPLICATION_JSON)), this::findById)
                .andRoute(POST("/save").and(accept(APPLICATION_JSON)), this::save)
                .andRoute(POST("/update/{id}").and(accept(APPLICATION_JSON)), this::update));
    }

    private Mono<ServerResponse> listAll(ServerRequest req) {
        return ok().body(personService.findAll(req), Person.class);
    }

    private Mono<ServerResponse> findById(ServerRequest req) {
        return ok().body(personService.findById(req), Person.class);
    }

    private Mono<ServerResponse> save(ServerRequest req) {
        personService.insert(req).subscribe();
        return ok().build();
    }

    private Mono<ServerResponse> update(ServerRequest req) {
        return ok().body(personService.update(req), Person.class);
    }

}