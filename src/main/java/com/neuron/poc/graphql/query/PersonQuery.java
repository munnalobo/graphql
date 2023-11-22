package com.neuron.poc.graphql.query;

import com.neuron.poc.graphql.entity.Person;
import com.neuron.poc.graphql.service.PersonService;
import graphql.annotations.annotationTypes.GraphQLDescription;
import graphql.annotations.annotationTypes.GraphQLField;
import graphql.annotations.annotationTypes.GraphQLNonNull;
import graphql.annotations.annotationTypes.GraphQLType;
import graphql.kickstart.annotations.GraphQLQueryResolver;
import java.util.List;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@NoArgsConstructor
@GraphQLQueryResolver
@GraphQLType
public class PersonQuery implements ApplicationContextAware {

  private static PersonService personService;

  @GraphQLField
  @GraphQLNonNull
  @GraphQLDescription("Returns all people in the database.")
  @GetMapping("/people/all")
  public static List<Person> people() {
    return personService.getAll();
  }

  @GraphQLField
  @GraphQLNonNull
  @GraphQLDescription("Returns all people in the database with the given first name.")
  @GetMapping("/people")
  public static List<Person> getPerson(final @RequestParam String firstName) {
    return personService.getAll();
  }


  @Override
  public void setApplicationContext(final @NonNull ApplicationContext applicationContext)
      throws BeansException {
    personService = applicationContext.getBean(PersonService.class);
  }
}
