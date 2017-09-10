package com.reactive.mongodb.example.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.server.ServerRequest;

import com.reactive.mongodb.example.model.Person;
import com.reactive.mongodb.example.repository.PersonRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public Mono<Person> findById(ServerRequest req) {
        return personRepository.findById(req.pathVariable("id"));
    }

    public Mono<Flux<Person>> findAllMono() {
        return Mono.just(personRepository.findAll());
    }

    public Flux<Person> findAll(ServerRequest req) {
        return Flux.from(personRepository.findAll());
    }

    @Transactional
    public Flux<Person> insert(ServerRequest req) {
        return personRepository.insert(req.bodyToMono(Person.class));
    }

    @Transactional
    public Mono<Person> update(ServerRequest req) {
        System.out.println(" id -- " + req.pathVariable("id"));
        Person person = req.bodyToMono(Person.class).block();
        person.setId(req.pathVariable("id"));
        return personRepository.save(person);
    }

}
