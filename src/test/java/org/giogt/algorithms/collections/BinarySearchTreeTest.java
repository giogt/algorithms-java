package org.giogt.algorithms.collections;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.*;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class BinarySearchTreeTest {

  private static Comparator<Integer> INTEGER_COMPARATOR = Integer::compare;
  private static Comparator<String> STRING_COMPARATOR = CharSequence::compare;

  private static List<Integer> emptyIntegerTreeElements() {
    return Collections.emptyList();
  }

  private static List<Integer> oneElementIntegerTreeElements() {
    return Collections.singletonList(0);
  }

  /**
   * <pre>
   *          13
   *        /    \
   *       3       14
   *     /   \       \
   *    1     4       18
   *     \     \
   *      2     12
   *            /
   *          10
   *        /   \
   *       5     11
   *        \
   *         8
   *       /  \
   *      7    9
   *     /
   *    6
   * </pre>
   */
  private static List<Integer> integerTreeElements() {
    return Arrays.asList(13, 3, 4, 12, 14, 10, 5, 1, 8, 2, 7, 9, 11, 6, 18);
  }

  /**
   * <pre>
   *          j
   *        /   \
   *       f      k
   *     /   \      \
   *    a     h      z
   *      \
   *       d
   * </pre>
   */
  private static List<String> stringTreeElements() {
    return Arrays.asList("j", "f", "a", "d", "h", "k", "z");
  }

  private static Stream<Arguments> inOrderTraversalTestFixtures() {
    return Stream.of(
        Arguments.of(
            integerTreeElements(),
            INTEGER_COMPARATOR,
            Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 18)),
        Arguments.of(
            stringTreeElements(),
            STRING_COMPARATOR,
            Arrays.asList("a", "d", "f", "h", "j", "k", "z"))
    );
  }

  private static Stream<Arguments> breadthFirstTraversalTestFixtures() {
    return Stream.of(
        Arguments.of(
            stringTreeElements(),
            STRING_COMPARATOR,
            Arrays.asList("j", "f", "k", "a", "h", "z", "d"))
    );
  }

  private static Stream<Arguments> heightTestFixtures() {
    return Stream.of(
        Arguments.of(emptyIntegerTreeElements(), INTEGER_COMPARATOR, 0),
        Arguments.of(oneElementIntegerTreeElements(), INTEGER_COMPARATOR, 1),
        Arguments.of(integerTreeElements(), INTEGER_COMPARATOR, 9),
        Arguments.of(stringTreeElements(), STRING_COMPARATOR, 4)
    );
  }

  private static Stream<Arguments> isBinarySearchTreeTestFixtures() {
    return Stream.of(
        Arguments.of(emptyIntegerTreeElements(), INTEGER_COMPARATOR, true),
        Arguments.of(oneElementIntegerTreeElements(), INTEGER_COMPARATOR, true),
        Arguments.of(integerTreeElements(), INTEGER_COMPARATOR, true),
        Arguments.of(stringTreeElements(), STRING_COMPARATOR, true)
    );
  }

  @ParameterizedTest
  @MethodSource("inOrderTraversalTestFixtures")
  public <E> void inOrderTraversal_test(
      List<E> toInsert,
      Comparator<E> comparator,
      List<E> expectedTraversalOrder) {

    BinaryTree<E> bst = newBinarySearchTree(toInsert, comparator);

    List<E> result = new ArrayList<>(bst.size());
    bst.depthFirstTraversal((n) -> result.add(n.getValue()));
    assertThat(result, is(expectedTraversalOrder));
  }

  @ParameterizedTest
  @MethodSource("breadthFirstTraversalTestFixtures")
  public <E> void breadthFirstTraversal_test(
      List<E> toInsert,
      Comparator<E> comparator,
      List<E> expectedTraversalOrder) {

    BinaryTree<E> bst = newBinarySearchTree(toInsert, comparator);

    List<E> result = new ArrayList<>(bst.size());
    bst.breadthFirstTraversal((n) -> result.add(n.getValue()));
    assertThat(result, is(expectedTraversalOrder));
  }

  @ParameterizedTest
  @MethodSource("heightTestFixtures")
  public <E> void height_test(
      List<E> toInsert,
      Comparator<E> comparator,
      int expectedHeight) {

    BinaryTree<E> bst = newBinarySearchTree(toInsert, comparator);
    assertThat(bst.height(), is(expectedHeight));
  }

  @ParameterizedTest
  @MethodSource("isBinarySearchTreeTestFixtures")
  public <E> void isBinarySearchTree_test(
      List<E> toInsert,
      Comparator<E> comparator,
      boolean expected) {

    BinarySearchTree<E> bst = new BinarySearchTree<>(comparator);
    assertThat(bst.isBinarySearchTree(), is(expected));
  }

  private <E> BinaryTree<E> newBinarySearchTree(List<E> toInsert, Comparator<E> comparator) {
    BinaryTree<E> bst = new BinarySearchTree<>(comparator);
    for (E elem : toInsert) {
      bst.insert(elem);
    }
    assertThat(bst.size(), is(toInsert.size()));
    return bst;
  }
}
