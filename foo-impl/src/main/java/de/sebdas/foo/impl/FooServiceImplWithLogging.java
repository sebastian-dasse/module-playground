package de.sebdas.foo.impl;

import de.sebdas.foo.api.FooManager;
import de.sebdas.foo.spi.FooService;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

public class FooServiceImplWithLogging implements FooService {

  @Override
  public FooManager getFooManager() {
    return (FooManager) Proxy.newProxyInstance(
        FooServiceImplWithLogging.class.getClassLoader(),
        new Class[] {FooManager.class},
        new LoggingInvocationHandler(new FooManagerImpl()));
  }

  private static class LoggingInvocationHandler implements InvocationHandler {
    private final Object target;
    private final Map<String, Method> methods;

    public LoggingInvocationHandler(final Object target) {
      this.target = target;
      this.methods = new HashMap<>();

      System.out.println();
      System.out.println("    > initializing dynamic proxy for: " + target.getClass());
      System.out.println("    >  methods:");

      for (final Method method : target.getClass().getDeclaredMethods()) {

        System.out.println("    >    " + method.toString());

        this.methods.put(method.getName(), method);
      }
      System.out.println();
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
}
