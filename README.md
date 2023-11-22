# graphql

### references:

- https://github.com/graphql-java-kickstart/graphql-spring-boot
- https://github.com/graphql-java-kickstart/samples
    - https://github.com/graphql-java-kickstart/samples/tree/master/graphql-annotations

### graphiql is enabled on this url

- http://localhost:8080/graphiql

### Notes:

1. In order for app to work with graphql queries, we have to make methods in `GraphQLQueryResolver`
   static
2. However, you can't autowire dependencies, as they are required for static methods.
   So, we have to use `ApplicationContextAware` and override `setApplicationContext`
   to get the bean from the context and set it to static variable.
