package org.giogt.algorithms.collections;

import org.hamcrest.Matchers;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;

class PriorityQueueTest {

  private static Stream<Arguments> testFixtures() {
    return Stream.of(
        Arguments.of(
            (Comparator<String>) String::compareTo,
            new String[]{"a",},
            new String[]{"a"}),
        Arguments.of(
            (Comparator<String>) String::compareTo,
            new String[]{"h", "b", "z", "x", "y", "a", "f", "l"},
            new String[]{"a", "b", "f", "h", "l", "x", "y", "z"}),
        Arguments.of(
            ((Comparator<String>) String::compareTo).reversed(),
            // invert the comparison order, for a max heap
            new String[]{"h", "b", "z", "x", "y", "a", "f", "l"},
            new String[]{"z", "y", "x", "l", "h", "f", "b", "a"})
    );
  }

  @ParameterizedTest
  @MethodSource("testFixtures")
  public void test(
      Comparator<String> comparator,
      String[] elems,
      String[] expectedElems) {

    PriorityQueue<String> priorityQueue = new PriorityQueue<>(comparator);
    priorityQueue.addAll(Arrays.asList(elems));
    for (String expectedElem : expectedElems) {
      String elem = priorityQueue.poll();
      assertThat(elem, Matchers.is(expectedElem));
    }
  }
}
