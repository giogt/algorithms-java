package org.giogt.algorithms.collections;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class BaseBinaryTreeTest {

  private static BinaryTree<Integer> binarySearchTree() {
    BinaryTreeNode<Integer> node1 = new BinaryTreeNode<>(1);
    BinaryTreeNode<Integer> node2 = new BinaryTreeNode<>(2);
    BinaryTreeNode<Integer> node3 = new BinaryTreeNode<>(3);
    BinaryTreeNode<Integer> node4 = new BinaryTreeNode<>(4);
    BinaryTreeNode<Integer> node5 = new BinaryTreeNode<>(5);
    BinaryTreeNode<Integer> node6 = new BinaryTreeNode<>(6);

    BinaryTreeNode<Integer> root = node4;

    node4.setLeft(node2);
    node4.setRight(node6);
    node2.setLeft(node1);
    node2.setRight(node3);
    node6.setLeft(node5);

    return new BaseBinaryTree<>(Integer::compare, root);
  }

  private static BinaryTree<Integer> nonBinarySearchTree() {
    BinaryTreeNode<Integer> node1 = new BinaryTreeNode<>(1);
    BinaryTreeNode<Integer> node2 = new BinaryTreeNode<>(2);
    BinaryTreeNode<Integer> node3 = new BinaryTreeNode<>(3);
    BinaryTreeNode<Integer> node4 = new BinaryTreeNode<>(4);
    BinaryTreeNode<Integer> node5 = new BinaryTreeNode<>(5);
    BinaryTreeNode<Integer> node6 = new BinaryTreeNode<>(6);

    BinaryTreeNode<Integer> root = node5;

    node5.setLeft(node1);
    node5.setRight(node4);
    node4.setLeft(node3);
    node4.setRight(node6);
    node3.setLeft(node2);

    return new BaseBinaryTree<>(Integer::compare, root);
  }

  private static Stream<Arguments> isBinarySearchTreeTestFixtures() {
    return Stream.of(
        Arguments.of(binarySearchTree(), true),
        Arguments.of(nonBinarySearchTree(), false)
    );
  }

  @ParameterizedTest
  @MethodSource("isBinarySearchTreeTestFixtures")
  public <E> void isBinarySearchTree_test(
      BinaryTree<E> tree,
      boolean expected) {

    assertThat(tree.isBinarySearchTree(), is(expected));
  }
}