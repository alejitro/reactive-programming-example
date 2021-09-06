package com.alejitro.reactiveexample;

import com.alejitro.reactiveexample.domain.Person;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class PersonRepositoryImpl implements PersonRepository {

    Person alejo = Person
            .builder()
            .id(1)
            .firstName("Alejo")
            .lastName("Martinez")
            .build();

    Person tony = new Person(2,"Tony","Stark");
    Person natasha = new Person(3,"Natasha","Romanov");
    Person james = new Person(4,"James","Rodriguez");

    @Override
    public Mono<Person> getById(Integer id) {
        Flux<Person> personFlux = findAll();
        Mono<Person> personMono = personFlux.filter(person -> person.getId().equals(id)).next();
        return personMono.onErrorReturn(Person.builder().build());
    }

    @Override
    public Flux<Person> findAll() {

        return Flux.just(alejo,tony,natasha,james);
    }
}
