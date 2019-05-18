package de.sebdas.foo;

import de.sebdas.foo.api.FooManager;
import de.sebdas.foo.spi.FooService;

import java.util.Iterator;
import java.util.ServiceLoader;

public final class FooServiceAccess {
  private FooServiceAccess() {}

  private static final FooService FOO_SERVICE_PROVIDER = getServiceProvider();

  private static FooService getServiceProvider() {
    final ServiceLoader<FooService> loader = ServiceLoader.load(FooService.class);
    final Iterator<FooService> providers = loader.iterator();

    if (!providers.hasNext()) {
      throw new RuntimeException("no implementation found for service: " + FooService.class);
    }

    return providers.next();
  }

  private static FooService getInstance() {
    return FOO_SERVICE_PROVIDER;
  }

  public static FooManager getFooManager() {
    return getInstance().getFooManager();
  }
}
