package de.sebdas.foo.impl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

class DynamicProxy {

  static <T, I extends T> T proxyWithLogging(final Class<T> tClass, final I tImpl) {
    return proxy(tClass, new LoggingInvocationHandler(tImpl));
  }

  static <T, I extends T> T proxyWithDelay(final Class<T> tClass, final I tImpl, final long delayMillis) {
    return proxy(tClass, new DelayingInvocationHandler(tImpl, delayMillis));

  }

  private static <T> T proxy(final Class<T> tClass, final InvocationHandler invocationHandler) {
    //noinspection unchecked
    return (T) Proxy.newProxyInstance(
        DelayingInvocationHandler.class.getClassLoader(),
        new Class[] {tClass},
        invocationHandler);
  }
}
