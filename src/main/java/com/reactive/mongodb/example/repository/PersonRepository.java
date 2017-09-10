package com.reactive.mongodb.example.repository;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.reactive.mongodb.example.model.Person;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PersonRepository extends ReactiveMongoRepository<Person, String> {
    Flux<Person> findByName(String name);

    @Query("{ 'name': ?0, 'age': ?1}")
    Mono<Person> findBynameAndAge(String firstname, int age);

    // Accept parameter inside a reactive type for deferred execution
    Flux<Person> findByAge(Mono<Integer> age);

}
