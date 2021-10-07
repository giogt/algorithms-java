package org.giogt.algorithms.collections;

import java.util.Comparator;

public class BinarySearchTree<E> extends AbstractBinaryTree<E> {
  private final Comparator<E> comparator;
  private BinaryTreeNode<E> root;
  private int size = 0;
  private int modCount = 0;

  public BinarySearchTree(Comparator<E> comparator) {
    this.comparator = comparator;
  }

  public BinarySearchTree(Comparator<E> comparator, BinaryTreeNode<E> root) {
    this.comparator = comparator;
    this.root = root;
  }

  /**
   * Inserts the specified element in the tree and returns the new created node containing the
   * element.
   * <p>
   * If the element already exists, the existing node containing the element will be returned.
   */
  @Override
  public BinaryTreeNode<E> insert(E elem) {
    if (root == null) {
      this.root = newNode(elem);
      return this.root;
    } else {
      return insert(elem, root);
    }
  }

  private BinaryTreeNode<E> insert(E elem, BinaryTreeNode<E> node) {
    int comparison = comparator.compare(elem, node.getValue());

    if (comparison == 0) {
      // duplicates are not allowed
      // returning the already existing element, without inserting a duplicate
      return node;
    } else if (comparison < 0) {
      // consider the left subtree

      BinaryTreeNode<E> leftNode = node.getLeft();
      if (leftNode == null) {
        // insert the new node at this left empty position
        BinaryTreeNode<E> newNode = newNode(elem);
        node.setLeft(newNode);
        return newNode;
      } else {
        return insert(elem, leftNode);
      }
    } else { // comparison > 0
      // consider the right subtree

      BinaryTreeNode<E> rightNode = node.getRight();
      if (rightNode == null) {
        // insert the new node at this right empty position
        BinaryTreeNode<E> newNode = newNode(elem);
        node.setRight(newNode);
        return newNode;
      } else {
        return insert(elem, rightNode);
      }
    }
  }

  @Override
  public BinaryTreeNode<E> find(E elem) {
    return find(elem, root);
  }

  private BinaryTreeNode<E> find(E elem, BinaryTreeNode<E> node) {
    if (node == null) {
      return null;
    }

    int comparison = comparator.compare(elem, node.getValue());
    if (comparison < 0) {
      return find(elem, node.getLeft());
    } else if (comparison > 0) {
      return find(elem, node.getRight());
    } else {
      return node;
    }
  }

  @Override
  public BinaryTreeNode<E> delete(E value) {
    return deleteNode(this.root, value);
  }

  private BinaryTreeNode<E> deleteNode(BinaryTreeNode<E> node, E elem) {
    if (node == null) {
      return null;
    }

    int comparison = comparator.compare(elem, node.getValue());
    if (comparison < 0) {
      node.setLeft(deleteNode(node.getLeft(), elem));
    } else if (comparison > 0) {
      node.setRight(deleteNode(node.getRight(), elem));
    } else {
      if (node.getLeft() == null && node.getRight() == null) {
        // node with no children
        return null;
      } else if (node.getLeft() == null) {
        // node with a right child only
        return node.getRight();
      } else if (node.getRight() == null) {
        // node with a left child only
        return node.getLeft();
      } else {
        // nodes with two children
        // search for min number in right sub tree
        E minValue = minValue(node.getRight());
        node.setValue(minValue);
        node.setRight(deleteNode(node.getRight(), minValue));
      }
    }
    return node;
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

  private BinaryTreeNode<E> newNode(E elem) {
    modCount++;
    BinaryTreeNode<E> node = new BinaryTreeNode<>(elem);
    size++;

    return node;
  }

  private E minValue(BinaryTreeNode<E> node) {
    if (node.getLeft() != null) {
      return minValue(node.getLeft());
    }
    return node.getValue();
  }
}
