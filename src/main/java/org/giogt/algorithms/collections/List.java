package org.giogt.algorithms.collections;

public interface List<E> extends Iterable<E> {

  E get(int index);

  E contains(E elem);

  void add(E elem);

  void remove(int index);

  boolean removeFirst(E elem);

  boolean removeAll(E elem);

  int size();
}
