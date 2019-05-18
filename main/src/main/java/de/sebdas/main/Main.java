package de.sebdas.main;

import de.sebdas.foo.FooServiceAccess;
import de.sebdas.foo.api.Foo;
import de.sebdas.foo.api.FooManager;

import java.util.List;

public class Main {

  public static void main(final String[] args) {
    final FooManager fooManager = FooServiceAccess.getFooManager();
    final List<Foo> foo = fooManager.getFoo();
    foo.forEach(System.out::println);
  }
}
