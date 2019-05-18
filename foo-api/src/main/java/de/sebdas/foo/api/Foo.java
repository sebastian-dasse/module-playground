package de.sebdas.foo.api;

public class Foo {
  private static long count = 0;

  private final long bar;
  private final String baz;

  public Foo(final String baz) {
    this.bar = count++;
    this.baz = baz;
  }

  public long getBar() {
    return bar;
  }

  public String getBaz() {
    return baz;
  }

  @Override
  public String toString() {
    return "Foo{" +
           "bar=" + bar +
           ", baz='" + baz + '\'' +
           '}';
  }
}
