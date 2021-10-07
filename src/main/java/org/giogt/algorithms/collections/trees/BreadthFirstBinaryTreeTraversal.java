package org.giogt.algorithms.collections.trees;

import org.giogt.algorithms.collections.BinaryTreeNode;

import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Consumer;

public class BreadthFirstBinaryTreeTraversal<E> implements BinaryTreeTraversal<E> {

  @Override
  public void traverse(Consumer<BinaryTreeNode<E>> nodeConsumer, BinaryTreeNode<E> node) {
    if (node == null) {
      return;
    }

    Queue<BinaryTreeNode<E>> queue = new LinkedList<>();
    queue.add(node);
    while (!queue.isEmpty()) {
      BinaryTreeNode<E> currentNode = queue.remove();
      nodeConsumer.accept(currentNode);

      if (currentNode.getLeft() != null) {
        queue.add(currentNode.getLeft());
      }
      if (currentNode.getRight() != null) {
        queue.add(currentNode.getRight());
      }
    }
  }
}
