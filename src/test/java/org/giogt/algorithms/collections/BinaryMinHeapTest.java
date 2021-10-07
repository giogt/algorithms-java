package org.giogt.algorithms.collections;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class BinaryMinHeapTest {

  private static Stream<Arguments> testFixtures() {
    return Stream.of(
        Arguments.of(
            Collections.emptyList(),
            Collections.emptyList()
        ),
        Arguments.of(
            Arrays.asList(10, 6, 12, 7, 15, 17, 5),
            Arrays.asList(5, 7, 6, 10, 15, 17, 12)
        )
    );
  }

  @ParameterizedTest
  @MethodSource("testFixtures")
  public void heap_whenAddingElement_mustInsertThemInTheExpectedOrder(
      java.util.List<Integer> elems,
      java.util.List<Integer> expected
  ) {
    BinaryMinHeap<Integer> heap = new BinaryMinHeap<>(Integer::compareTo);
    for (Integer elem : elems) {
      heap.add(elem);
    }

    java.util.List<Integer> result = heap.heap();
    assertThat(result.size(), is(expected.size()));
    for (int i = 0; i < result.size(); i++) {
      assertThat(result.get(i), is(expected.get(i)));
    }
  }

}