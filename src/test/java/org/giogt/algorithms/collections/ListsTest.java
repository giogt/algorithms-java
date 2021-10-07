package org.giogt.algorithms.collections;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Optional;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class ListsTest {

  private static Stream<Arguments> binarySearchTestFixtures() {
    return Stream.of(
        Arguments.of(
            ArrayList.builder(6).add(1, 2, 3, 5, 8, 13).build(),
            1,
            Optional.of(0)),
        Arguments.of(
            ArrayList.builder(6).add(1, 2, 3, 5, 8, 13).build(),
            3,
            Optional.of(2)),
        Arguments.of(
            ArrayList.builder(6).add(1, 2, 3, 5, 8, 13).build(),
            5,
            Optional.of(3)),
        Arguments.of(
            ArrayList.builder(6).add(1, 2, 3, 5, 8, 13).build(),
            13,
            Optional.of(5)),
        Arguments.of(
            ArrayList.builder(6).add(1, 2, 3, 5, 8, 13).build(),
            4,
            Optional.empty()),
        Arguments.of(
            ArrayList.builder(6).add(1, 2, 3, 5, 8, 13, 21).build(),
            5,
            Optional.of(3)),
        Arguments.of(
            ArrayList.builder(6).add(1, 2, 3, 5, 8, 13, 21).build(),
            11,
            Optional.empty())
    );
  }

  @ParameterizedTest
  @MethodSource("binarySearchTestFixtures")
  public void binarySearch_test(
      List<Integer> sortedList,
      Integer value,
      Optional<Integer> expected) {

    Optional<Integer> result = Lists.binarySearch(
        sortedList,
        value,
        Integer::compareTo);
    assertThat(result, is(expected));
  }

}