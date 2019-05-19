package de.sebdas.foo.impl;

import de.sebdas.foo.api.FooManager;
import de.sebdas.foo.spi.FooService;

import static de.sebdas.foo.impl.DynamicProxy.proxyWithDelay;

public class FooServiceImplWithDelay implements FooService {

  @Override
  public FooManager getFooManager() {
    return proxyWithDelay(FooManager.class, new FooManagerImpl(), 1_000);
  }
}
