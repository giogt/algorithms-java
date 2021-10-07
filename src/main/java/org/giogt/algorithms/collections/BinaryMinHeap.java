package org.giogt.algorithms.collections;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class BinaryMinHeap<E> {

  private final Comparator<E> comparator;
  private final List<E> heap = new ArrayList<>();

  public BinaryMinHeap(Comparator<E> comparator) {
    this.comparator = comparator;
  }

  /**
   * Adds a new element in the heap.
   */
  public void add(E element) {
    heap.add(element);
    heapifyUp(heap.size() - 1);
  }

  /**
   * Returns the minimum element according to the heap comparator, without removing it from the
   * heap.
   */
  public E peek() {
    if (heap.isEmpty()) {
      return null;
    }
    return heap.get(0);
  }

  /**
   * Returns the minimum element according to the heap comparator, removing it from the heap.
   */
  public E poll() {
    throw new UnsupportedOperationException("Not implemented yet!");
  }

  public List<E> heap() {
    return heap;
  }

  public int size() {
    return heap.size();
  }

  /**
   * Returns the index of the parent for the node at the specified index.
   */
  public int parent(int index) {
    return (index - 1) / 2;
  }

  /**
   * Returns the position of the left child for the node at the specified index.
   */
  public int leftChild(int pos) {
    return (2 * pos) + 1;
  }

  /**
   * Returns the position of the right child for the node at the specified index.
   */
  public int rightChild(int pos) {
    return (2 * pos) + 2;
  }

  public boolean isLeaf(int pos) {
    return pos > parent(heap.size() - 1) && pos < heap.size();
  }

  /**
   * Maintains the heap property while inserting.
   * <p>i</p> is the index of the inserted node (last element of the heap).
   */
  private void heapifyUp(int i) {
    E tmp = heap.get(i);

    int parent = parent(i);
    while (i > 0 && comparator.compare(tmp, heap.get(parent)) < 0) {
      heap.set(i, heap.get(parent));
      i = parent;
      parent = parent(i);
    }
    heap.set(i, tmp);
  }
}
