package org.giogt.algorithms.collections;

import java.util.function.Consumer;

public interface BinaryTree<E> extends Iterable<E> {

  BinaryTreeNode<E> insert(E elem);

  BinaryTreeNode<E> find(E elem);

  BinaryTreeNode<E> delete(E value);

  BinaryTreeNode<E> root();

  int size();

  int height();

  boolean isBinarySearchTree();

  void depthFirstTraversal(Consumer<BinaryTreeNode<E>> nodeConsumer);

  void inOrderTraversal(Consumer<BinaryTreeNode<E>> nodeConsumer, BinaryTreeNode<E> node);

  void preOrderTraversal(Consumer<BinaryTreeNode<E>> nodeConsumer, BinaryTreeNode<E> node);

  void postOrderTraversal(Consumer<BinaryTreeNode<E>> nodeConsumer, BinaryTreeNode<E> node);

  void breadthFirstTraversal(Consumer<BinaryTreeNode<E>> nodeConsumer);

  void breadthFirstTraversal(Consumer<BinaryTreeNode<E>> nodeConsumer, BinaryTreeNode<E> node);
}
