package com.neuron.poc.graphql.query;

import com.neuron.poc.graphql.entity.Person;
import graphql.annotations.annotationTypes.GraphQLDescription;
import graphql.annotations.annotationTypes.GraphQLField;
import graphql.annotations.annotationTypes.GraphQLNonNull;
import graphql.kickstart.annotations.GraphQLQueryResolver;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
@GraphQLQueryResolver
public class PersonQuery {

  @GraphQLField
  @GraphQLNonNull
  @GraphQLDescription("Returns all people in the database.")
  public static List<Person> people() {
    return List.of(Person.builder()
        .firstName("John")
        .lastName("Smith")
        .build());
  }

  @GraphQLField
  @GraphQLNonNull
  @GraphQLDescription("Returns all people in the database with the given first name.")
  public static List<Person> getPerson(final @Nullable String firstName) {
    return List.of(Person.builder()
        .firstName("John")
        .lastName("Smith")
        .build());
  }
}
