package com.neuron.poc.graphql.controller;

import graphql.schema.GraphQLSchema;
import graphql.schema.idl.SchemaPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SchemaGeneratorController {

  @Autowired
  SchemaPrinter schemaPrinter;
  @Autowired
  @Qualifier("personGraphQLSchema")
  GraphQLSchema schema;

  @GetMapping("/personSchema")
  public String getCurrentSchema() {
    return schemaPrinter.print(schema);
  }
}
