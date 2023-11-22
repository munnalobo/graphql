package com.neuron.poc.graphql.controller;

import static graphql.annotations.AnnotationsSchemaCreator.newAnnotationsSchema;

import com.neuron.poc.graphql.mutation.PersonMutation;
import com.neuron.poc.graphql.query.PersonQuery;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.SchemaPrinter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SchemaGeneratorController {

  @GetMapping("/personSchema")
  public String getCurrentSchema() {
    GraphQLSchema schema = newAnnotationsSchema()
        .query(PersonQuery.class)
        .mutation(PersonMutation.class)
        .build();

    SchemaPrinter schemaPrinter = new SchemaPrinter(
        SchemaPrinter.Options.defaultOptions()
            .includeScalarTypes(false)
            .includeDirectives(false)
            .includeSchemaDefinition(true)
    );
    return schemaPrinter.print(schema);
  }
}
