package com.neuron.poc.graphql.service;

import com.neuron.poc.graphql.entity.Person;
import com.neuron.poc.graphql.repository.PersonRepository;
import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class PersonService {

  private final PersonRepository personRepository;

  public List<Person> getAll() {
    return personRepository.getAll();
  }

}
