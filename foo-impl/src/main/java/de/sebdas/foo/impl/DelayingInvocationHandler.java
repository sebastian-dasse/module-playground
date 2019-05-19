package de.sebdas.foo.impl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Map;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

class DelayingInvocationHandler implements InvocationHandler {
  private final Object target;
  private final Map<String, Method> methods;
  private final long delay;

  DelayingInvocationHandler(final Object target, final long delayMillis) {
    this.target = target;
    this.methods = Arrays
        .stream(target.getClass().getDeclaredMethods())
        .collect(toMap(Method::getName, identity()));
    this.delay = delayMillis;
  }

  @Override
  public Object invoke(final Object proxy, final Method method, final Object[] args) throws Throwable {

    System.out.printf("    > %s - method invoked%n", LocalTime.now());
    Thread.sleep(delay);
    System.out.printf("    > %s - method execution starts%n", LocalTime.now());

    return methods.get(method.getName()).invoke(target, args);
  }
}
