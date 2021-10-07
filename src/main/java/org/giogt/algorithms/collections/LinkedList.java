package org.giogt.algorithms.collections;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class LinkedList<E> extends AbstractList<E> {

  private int size = 0;
  private Node<E> head;
  private int modCount = 0;

  private LinkedList() {
  }

  private LinkedList(Node<E> head) {
    this.head = head;

    // calculate size
    for (E elem : this) {
      size++;
    }
  }

  /**
   * Creates a new empty linked list.
   *
   * @param <E> the element type
   * @return the newly created linked list
   */
  public static <E> LinkedList<E> newList() {
    return new LinkedList<>();
  }

  /**
   * Creates a new linked list, using the specified node as head. All the subsequent nodes will be
   * part of the linked list, too.
   *
   * @param head the node to use as list head
   * @param <E>  the element type
   * @return the newly created linked list
   */
  public static <E> LinkedList<E> fromNode(Node<E> head) {
    return new LinkedList<>(head);
  }

  @Override
  public E get(int index) {
    if (index >= size) {
      throw new IndexOutOfBoundsException();
    }

    Node<E> node = head;
    for (int i = 0; i < index; i++) {
      node = node.getNext();
    }
    return node.getValue();
  }

  @Override
  public E contains(E elem) {
    for (E current : this) {
      if (current.equals(elem)) {
        return elem;
      }
    }
    return null;
  }

  @Override
  public void add(E elem) {
    modCount++;
    Node<E> newNode = new Node<>(elem);
    if (head == null) {
      head = newNode;
    } else {
      Node<E> lastNode = head;
      while (lastNode.getNext() != null) {
        lastNode = lastNode.getNext();
      }
      lastNode.setNext(newNode);
    }
    size++;
  }

  @Override
  public void remove(int index) {
    if (index >= size) {
      throw new IndexOutOfBoundsException();
    }

    modCount++;
    if (index == 0) {
      // remove head
      head = head.getNext();
    } else {
      // remove element after head
      Node<E> previous = null;
      Node<E> node = head;
      for (int i = 0; i < index; i++) {
        previous = node;
        node = node.getNext();
      }
      previous.setNext(node.getNext());
    }
    size--;
  }

  @Override
  public boolean removeFirst(E elem) {
    throw new UnsupportedOperationException("Not implemented yet!");
  }

  @Override
  public boolean removeAll(E elem) {
    throw new UnsupportedOperationException("Not implemented yet!");
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public Iterator<E> iterator() {
    return new LinkedListIerator();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    List<?> that = (List<?>) o;
    if (size != that.size()) {
      return false;
    }

    Iterator<?> it = iterator();
    Iterator<?> thatIt = that.iterator();
    while (it.hasNext()) {
      if (!Objects.equals(it.next(), thatIt.next())) {
        return false;
      }
    }
    return true;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    for (E e : this) {
      result = result * prime + e.hashCode();
    }
    return result;
  }

  @Data
  @RequiredArgsConstructor
  public static class Node<V> {
    private final V value;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Node<V> next;
  }

  class LinkedListIerator implements Iterator<E> {

    private final int expectedModCount = modCount;
    private Node<E> next = head;

    @Override
    public boolean hasNext() {
      return next != null;
    }

    @Override
    public E next() {
      checkForModification();
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      E value = next.getValue();
      next = next.getNext();

      return value;
    }

    private void checkForModification() {
      if (expectedModCount != modCount) {
        throw new ConcurrentModificationException();
      }
    }
  }
}
