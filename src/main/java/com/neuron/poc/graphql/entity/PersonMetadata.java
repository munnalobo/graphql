package com.neuron.poc.graphql.entity;

import graphql.annotations.annotationTypes.GraphQLField;
import graphql.annotations.annotationTypes.GraphQLNonNull;


public class PersonMetadata {

  @GraphQLField
  @GraphQLNonNull
  private String ethnicity;
}
