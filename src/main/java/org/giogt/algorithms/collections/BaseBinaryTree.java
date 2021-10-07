package org.giogt.algorithms.collections;

import java.util.Comparator;

public class BaseBinaryTree<E> extends AbstractBinaryTree<E> {
  private final Comparator<E> comparator;
  private BinaryTreeNode<E> root;
  private int size = 0;
  private int modCount = 0;

  public BaseBinaryTree(Comparator<E> comparator) {
    this.comparator = comparator;
  }

  public BaseBinaryTree(Comparator<E> comparator, BinaryTreeNode<E> root) {
    this.comparator = comparator;
    this.root = root;
  }

  @Override
  public BinaryTreeNode<E> insert(E elem) {
    throw new UnsupportedOperationException("Not implemented yet!");
  }

  @Override
  public BinaryTreeNode<E> find(E elem) {
    throw new UnsupportedOperationException("Not implemented yet!");
  }

  @Override
  public BinaryTreeNode<E> delete(E value) {
    throw new UnsupportedOperationException("Not implemented yet!");
  }

  @Override
  public BinaryTreeNode<E> root() {
    return root;
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  protected Comparator<E> comparator() {
    return comparator;
  }

  @Override
  protected int modCount() {
    return modCount;
  }
}
