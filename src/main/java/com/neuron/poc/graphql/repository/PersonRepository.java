package com.neuron.poc.graphql.repository;

import com.neuron.poc.graphql.entity.Person;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class PersonRepository {

  public List<Person> getAll() {
    return List.of(Person.builder()
        .firstName("John")
        .lastName("Smith")
        .dateOfBirth(Instant.now())
        .milestone(LocalDate.now())
        .build());
  }
}
