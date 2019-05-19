package de.sebdas.foo.impl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

class LoggingInvocationHandler implements InvocationHandler {
  private final Object target;
  private final Map<String, Method> methods;

  LoggingInvocationHandler(final Object target) {
    this.target = target;

    System.out.println("    > initializing dynamic proxy for: " + target.getClass());
    System.out.println("    >  methods:");

    this.methods = Arrays
        .stream(target.getClass().getDeclaredMethods())
        .peek(method -> System.out.println("    >    " + method.toString()))
        .collect(toMap(Method::getName, identity()));
  }

  @Override
  public Object invoke(final Object proxy, final Method method, final Object[] args) throws Throwable {
    final long start = System.nanoTime();

    final Object result = methods.get(method.getName()).invoke(target, args);

    final long elapsed = System.nanoTime() - start;
    System.out.printf("    > executing %s took %d ns%n", method.getName(), elapsed);

    return result;
  }
}
