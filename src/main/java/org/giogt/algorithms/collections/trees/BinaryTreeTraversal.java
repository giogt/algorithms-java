package org.giogt.algorithms.collections.trees;

import org.giogt.algorithms.collections.BinaryTreeNode;

import java.util.function.Consumer;

public interface BinaryTreeTraversal<E> {
  void traverse(Consumer<BinaryTreeNode<E>> nodeConsumer, BinaryTreeNode<E> node);
}
