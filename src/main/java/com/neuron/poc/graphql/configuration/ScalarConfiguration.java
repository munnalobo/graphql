package com.neuron.poc.graphql.configuration;

import graphql.language.StringValue;
import graphql.schema.Coercing;
import graphql.schema.CoercingParseLiteralException;
import graphql.schema.CoercingParseValueException;
import graphql.schema.CoercingSerializeException;
import graphql.schema.GraphQLScalarType;
import graphql.schema.idl.RuntimeWiring;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@NoArgsConstructor
public class ScalarConfiguration {
//  @Bean
  public RuntimeWiring runtimeWiring() {
    return RuntimeWiring.newRuntimeWiring()
        .scalar(localDateScalar()) // Use a factory method to create your custom scalars
        .scalar(localInstantScalar())    // Use a factory method for your other custom scalars
        .build();
  }
  @Bean
  public GraphQLScalarType localDateScalar() {
    return GraphQLScalarType.newScalar()
        .name("Date")
        .description("LocalDate as GraphQL scalar.")
        .coercing(
            new Coercing<LocalDate, String>() {
              @Override
              public String serialize(Object dataFetcherResult) throws CoercingSerializeException {
                if (!(dataFetcherResult instanceof LocalDate)) {
                  throw new CoercingSerializeException("LocalDate expected.");
                }
                return String.valueOf(dataFetcherResult);
              }

              @Override
              public LocalDate parseValue(Object input) throws CoercingParseValueException {
                try {
                  return LocalDate.parse(String.valueOf(input));
                } catch (DateTimeParseException e) {
                  throw new CoercingParseValueException(e);
                }
              }

              @Override
              public LocalDate parseLiteral(Object input) throws CoercingParseLiteralException {
                try {
                  if (!(input instanceof StringValue)) {
                    throw new CoercingParseLiteralException("String value expected.");
                  }
                  return LocalDate.parse(((StringValue) input).getValue());
                } catch (DateTimeParseException e) {
                  throw new CoercingParseLiteralException("Failed to parse date literal", e);
                }
              }
            })
        .build();
  }

  @Bean
  public GraphQLScalarType localInstantScalar() {
    return GraphQLScalarType.newScalar()
        .name("Instant")
        .description("Instant as GraphQL scalar.")
        .coercing(
            new Coercing<Instant, String>() {
              @Override
              public String serialize(Object dataFetcherResult) throws CoercingSerializeException {
                if (!(dataFetcherResult instanceof Instant)) {
                  throw new CoercingSerializeException("Instant expected.");
                }
                return String.valueOf(dataFetcherResult);
              }

              @Override
              public Instant parseValue(Object input) throws CoercingParseValueException {
                try {
                  return Instant.parse(String.valueOf(input));
                } catch (DateTimeParseException e) {
                  throw new CoercingParseValueException(e);
                }
              }

              @Override
              public Instant parseLiteral(Object input) throws CoercingParseLiteralException {
                try {
                  if (!(input instanceof StringValue)) {
                    throw new CoercingParseLiteralException("String value expected.");
                  }
                  return Instant.parse(((StringValue) input).getValue());
                } catch (DateTimeParseException e) {
                  throw new CoercingParseLiteralException("Failed to parse date literal", e);
                }
              }
            })
        .build();
  }
}
