package de.sebdas.foo.impl;

import de.sebdas.foo.api.FooManager;
import de.sebdas.foo.spi.FooService;

import static de.sebdas.foo.impl.DynamicProxy.proxyWithLogging;

public class FooServiceImplWithLogging implements FooService {

  @Override
  public FooManager getFooManager() {
    return proxyWithLogging(FooManager.class, new FooManagerImpl());
  }
}
