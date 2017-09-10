package com.reactive.mongodb.example.config;

//@Configuration
//public class RoutesConfiguration {
//    @Bean
//    RouterFunction<?> routes(PersonRepository personRespository) {
//        return nest(path("/person"),
//            route(RequestPredicates.GET("/{id}"),
//                request -> ok().body(personRespository.findById(request.pathVariable("id")), Person.class))
//                    .andRoute(method(HttpMethod.POST), request -> {
//
//                        System.out.println("-------------------------------");
//                        personRespository.insert(request.bodyToMono(Person.class)).subscribe();
//                        return ok().build();
//                    }));
//    }
//}
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.http.server.reactive.ReactorHttpHandlerAdapter;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;

import reactor.ipc.netty.http.server.HttpServer;

@Configuration
public class RoutesConfiguration {
    private final Environment environment;

    @Autowired
    public RoutesConfiguration(Environment environment) {
        this.environment = environment;
    }

    @Bean
    public HttpServer httpServer(RouterFunction<?> routerFunction) {
        HttpHandler httpHandler = RouterFunctions.toHttpHandler(routerFunction);
        ReactorHttpHandlerAdapter adapter = new ReactorHttpHandlerAdapter(httpHandler);
        HttpServer server = HttpServer.create(environment.getProperty("server.address"),
                Integer.valueOf(environment.getProperty("server.port")));
        System.out.println("-------" + environment.getProperty("server.address"));
        System.out.println("-------" + environment.getProperty("server.port"));
        server.newHandler(adapter);
        return server;
    }
}
