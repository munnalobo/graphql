package com.neuron.poc.graphql.entity;

import graphql.annotations.annotationTypes.GraphQLField;
import graphql.annotations.annotationTypes.GraphQLNonNull;
import graphql.annotations.annotationTypes.GraphQLType;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@GraphQLType
public class Person {

  @GraphQLField
  List<Address> address;
  @GraphQLField
  @GraphQLNonNull
  private String firstName;
  @GraphQLField
  @GraphQLNonNull
  private String lastName;
  @GraphQLField
  private Instant dateOfBirth;
  @GraphQLField
  private LocalDate milestone;

}
