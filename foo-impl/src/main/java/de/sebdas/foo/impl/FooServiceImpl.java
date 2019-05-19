package de.sebdas.foo.impl;

import de.sebdas.foo.api.FooManager;
import de.sebdas.foo.spi.FooService;

public class FooServiceImpl implements FooService {

  @Override
  public FooManager getFooManager() {
    return new FooManagerImpl();
  }
}
