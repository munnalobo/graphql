package com.neuron.poc.graphql.configuration;

import graphql.GraphQLContext;
import graphql.execution.CoercedVariables;
import graphql.language.StringValue;
import graphql.language.Value;
import graphql.schema.Coercing;
import graphql.schema.CoercingParseLiteralException;
import graphql.schema.CoercingParseValueException;
import graphql.schema.CoercingSerializeException;
import graphql.schema.GraphQLScalarType;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Locale;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@NoArgsConstructor
public class ScalarConfiguration {

  @Bean
  public GraphQLScalarType localDateScalar() {
    return GraphQLScalarType.newScalar()
        .name("Date")
        .description("LocalDate as GraphQL scalar.")
        .coercing(
            new Coercing<LocalDate, String>() {
              @Override
              public @Nullable String serialize(@NotNull Object dataFetcherResult,
                  @NotNull GraphQLContext graphQLContext, @NotNull Locale locale)
                  throws CoercingSerializeException {
                if (!(dataFetcherResult instanceof LocalDate)) {
                  throw new CoercingSerializeException("LocalDate expected.");
                }
                return String.valueOf(dataFetcherResult);
              }

              @Override
              public @Nullable LocalDate parseValue(@NotNull Object input,
                  @NotNull GraphQLContext graphQLContext, @NotNull Locale locale)
                  throws CoercingParseValueException {
                try {
                  return LocalDate.parse(String.valueOf(input));
                } catch (DateTimeParseException e) {
                  throw new CoercingParseValueException(e);
                }
              }

              @Override
              public @Nullable LocalDate parseLiteral(@NotNull Value<?> input,
                  @NotNull CoercedVariables variables, @NotNull GraphQLContext graphQLContext,
                  @NotNull Locale locale) throws CoercingParseLiteralException {
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
              public @Nullable String serialize(@NotNull Object dataFetcherResult,
                  @NotNull GraphQLContext graphQLContext, @NotNull Locale locale)
                  throws CoercingSerializeException {
                if (!(dataFetcherResult instanceof Instant)) {
                  throw new CoercingSerializeException("Instant expected.");
                }
                return String.valueOf(dataFetcherResult);
              }

              @Override
              public @Nullable Instant parseValue(@NotNull Object input,
                  @NotNull GraphQLContext graphQLContext, @NotNull Locale locale)
                  throws CoercingParseValueException {
                try {
                  return Instant.parse(String.valueOf(input));
                } catch (DateTimeParseException e) {
                  throw new CoercingParseValueException(e);
                }
              }

              @Override
              public @Nullable Instant parseLiteral(@NotNull Value<?> input,
                  @NotNull CoercedVariables variables, @NotNull GraphQLContext graphQLContext,
                  @NotNull Locale locale) throws CoercingParseLiteralException {
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
