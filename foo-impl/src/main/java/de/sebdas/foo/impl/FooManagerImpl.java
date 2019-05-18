package de.sebdas.foo.impl;

import de.sebdas.foo.api.Foo;
import de.sebdas.foo.api.FooManager;

import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class FooManagerImpl implements FooManager {

  private static final int MAX_COUNT = 10;
  private static final int MAX_LENGTH = 20;

  private final Random random;

  FooManagerImpl() {
    random = new Random(System.currentTimeMillis());
  }

  @Override
  public List<Foo> getFoo() {
    return Stream.generate(this::generateRandomString)
                 .map(Foo::new)
                 .limit(generateRandomCount())
                 .collect(toList());
  }

  private String generateRandomString() {
    return IntStream.generate(() -> random.nextInt(26))
                    .mapToObj(i -> String.valueOf((char) ('a' + i)))
                    .limit(generateRandomLength())
                    .collect(joining());
  }

  private long generateRandomLength() {
    return 1 + random.nextInt(MAX_LENGTH - 1);
  }

  private long generateRandomCount() {
    return 1 + random.nextInt(MAX_COUNT - 1);
  }
}
