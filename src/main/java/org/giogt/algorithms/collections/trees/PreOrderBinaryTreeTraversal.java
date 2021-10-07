package org.giogt.algorithms.collections.trees;

import org.giogt.algorithms.collections.BinaryTreeNode;

import java.util.function.Consumer;

public class PreOrderBinaryTreeTraversal<E> implements BinaryTreeTraversal<E> {

  @Override
  public void traverse(Consumer<BinaryTreeNode<E>> nodeConsumer, BinaryTreeNode<E> node) {
    if (node == null) {
      return;
    }

    nodeConsumer.accept(node);
    traverse(nodeConsumer, node.getLeft());
    traverse(nodeConsumer, node.getRight());
  }
}
