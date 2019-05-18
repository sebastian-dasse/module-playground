module de.sebdas.foo_impl {
  requires de.sebdas.foo_api;
  provides de.sebdas.foo.spi.FooService with de.sebdas.foo.impl.FooServiceImpl;
}