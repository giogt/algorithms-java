package org.giogt.algorithms.collections.trees;

import org.giogt.algorithms.collections.BinaryTreeNode;

import java.util.function.Consumer;

public class InOrderBinaryTreeTraversal<E> implements BinaryTreeTraversal<E> {

  @Override
  public void traverse(Consumer<BinaryTreeNode<E>> nodeConsumer, BinaryTreeNode<E> node) {
    if (node == null) {
      return;
    }

    traverse(nodeConsumer, node.getLeft());
    nodeConsumer.accept(node);
    traverse(nodeConsumer, node.getRight());
  }
}
