package com.neuron.poc.graphql.configuration;

import static graphql.annotations.AnnotationsSchemaCreator.newAnnotationsSchema;

import com.neuron.poc.graphql.mutation.PersonMutation;
import com.neuron.poc.graphql.query.PersonQuery;
import graphql.annotations.AnnotationsSchemaCreator.Builder;
import graphql.annotations.processor.GraphQLAnnotations;
import graphql.schema.GraphQLScalarType;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.SchemaPrinter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class GraphSchemaGeneratorConfiguration {
  @Bean
  public SchemaPrinter schemaPrinter(){
    return new SchemaPrinter(
        SchemaPrinter.Options.defaultOptions()
            .includeScalarTypes(true)
            .includeDirectives(false)
            .includeSchemaDefinition(true)
    );
  }

  public Builder customGraphQLSchema(){
    ScalarConfiguration scalarConfiguration = new ScalarConfiguration();
    GraphQLScalarType localInstantScalar = scalarConfiguration.localInstantScalar();
    GraphQLScalarType localDateScalar = scalarConfiguration.localDateScalar();

    GraphQLAnnotations graphqlAnnotations = new GraphQLAnnotations();
    graphqlAnnotations.getTypeRegistry().put("Instant", localInstantScalar);
    graphqlAnnotations.getTypeRegistry().put("InputInstant", localInstantScalar);

    graphqlAnnotations.getTypeRegistry().put("LocalDate", localDateScalar);
    graphqlAnnotations.getTypeRegistry().put("InputLocalDate", localDateScalar);


    return newAnnotationsSchema()
        .setAnnotationsProcessor(graphqlAnnotations);
  }

  @Bean("personGraphQLSchema")
  @Primary
  public GraphQLSchema graphQLSchema(){

    return customGraphQLSchema()
        .query(PersonQuery.class)
        .mutation(PersonMutation.class)
        .build();
  }
}
