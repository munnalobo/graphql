package com.neuron.poc.graphql.mutation;

import com.neuron.poc.graphql.entity.Person;
import graphql.annotations.annotationTypes.GraphQLDescription;
import graphql.annotations.annotationTypes.GraphQLField;
import graphql.annotations.annotationTypes.GraphQLNonNull;
import graphql.kickstart.annotations.GraphQLMutationResolver;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
@GraphQLMutationResolver
@Slf4j
public class PersonMutation {


  @GraphQLField
  @GraphQLNonNull
  @GraphQLDescription("Creates a new person.")
  public static Person createPerson(final @GraphQLNonNull Person createPerson) {
    log.info("Create person input: {}", createPerson);
    final Person newPerson =
        Person.builder()
            .firstName(createPerson.getFirstName())
            .lastName(createPerson.getLastName())
            .build();
    log.info("Saving new person: {}", newPerson);
    return newPerson;
  }
}
