package org.giogt.algorithms.collections;

import org.giogt.algorithms.collections.trees.BreadthFirstBinaryTreeTraversal;
import org.giogt.algorithms.collections.trees.InOrderBinaryTreeTraversal;
import org.giogt.algorithms.collections.trees.PostOrderBinaryTreeTraversal;
import org.giogt.algorithms.collections.trees.PreOrderBinaryTreeTraversal;

import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

public abstract class AbstractBinaryTree<E> implements BinaryTree<E> {

  @Override
  public void depthFirstTraversal(Consumer<BinaryTreeNode<E>> nodeConsumer) {
    inOrderTraversal(nodeConsumer, root());
  }

  @Override
  public void inOrderTraversal(Consumer<BinaryTreeNode<E>> nodeConsumer, BinaryTreeNode<E> node) {
    new InOrderBinaryTreeTraversal<E>()
        .traverse(nodeConsumer, node);
  }

  @Override
  public void preOrderTraversal(Consumer<BinaryTreeNode<E>> nodeConsumer, BinaryTreeNode<E> node) {
    new PreOrderBinaryTreeTraversal<E>()
        .traverse(nodeConsumer, node);
  }

  @Override
  public void postOrderTraversal(Consumer<BinaryTreeNode<E>> nodeConsumer, BinaryTreeNode<E> node) {
    new PostOrderBinaryTreeTraversal<E>()
        .traverse(nodeConsumer, node);
  }

  @Override
  public void breadthFirstTraversal(Consumer<BinaryTreeNode<E>> nodeConsumer) {
    breadthFirstTraversal(nodeConsumer, root());
  }

  @Override
  public void breadthFirstTraversal(Consumer<BinaryTreeNode<E>> nodeConsumer,
      BinaryTreeNode<E> node) {
    new BreadthFirstBinaryTreeTraversal<E>()
        .traverse(nodeConsumer, node);
  }

  @Override
  public boolean isBinarySearchTree() {
    return isBinarySearchTree(root(), null, null);
  }

  private boolean isBinarySearchTree(BinaryTreeNode<E> node, E min, E max) {
    if (node == null) {
      return true;
    }

    E value = node.getValue();
    if (min != null && comparator().compare(value, min) <= 0) {
      return false;
    }
    if (max != null && comparator().compare(value, max) >= 0) {
      return false;
    }

    /*
     * Check left and right subtrees, narrowing down the [min, max] window appropriately.
     * If the check in a subtree returns false, propagate it up.
     * A single false result means that the tree is not a binary search tree.
     *
     * This will also ensure that we do not continue exploring other nodes if we already found one
     * where the binary search tree property does not hold.
     */

    if (!isBinarySearchTree(node.getLeft(), min, value)) {
      return false;
    }
    if (!isBinarySearchTree(node.getRight(), value, max)) {
      return false;
    }

    return true;
  }

  @Override
  public int height() {
    return height(root());
  }

  private int height(BinaryTreeNode<E> node) {
    if (node == null) {
      return 0;
    }
    return Math.max(height(node.getLeft()), height(node.getRight())) + 1;
  }

  @Override
  public Iterator<E> iterator() {
    return new BinaryTreeIterator();
  }

  protected abstract Comparator<E> comparator();

  protected abstract int modCount();

  class BinaryTreeIterator implements Iterator<E> {

    /*
     * TODO find a more space-efficient way to implement this iterator
     *
     * The current implementation is instantiating a list with the same size as the tree, containing
     * references to the node in the order that they need to be processed.
     */


    private final int expectedModCount = modCount();
    private final java.util.List<E> elems = new java.util.ArrayList<>(size());
    private int i = 0;

    public BinaryTreeIterator() {
      depthFirstTraversal((e) -> elems.add(e.getValue()));
    }

    @Override
    public boolean hasNext() {
      return i < size();
    }

    @Override
    public E next() {
      checkForModification();
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      return elems.get(i++);
    }

    private void checkForModification() {
      if (expectedModCount != modCount()) {
        throw new ConcurrentModificationException();
      }
    }
  }
}
