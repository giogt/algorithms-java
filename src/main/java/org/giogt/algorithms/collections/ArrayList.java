package org.giogt.algorithms.collections;

import org.giogt.algorithms.collections.exceptions.NoCapacityAvailableException;

import java.lang.reflect.Array;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayList<E> extends AbstractList<E> {
  private static final int DEFAULT_CAPACITY = 10;
  private final int capacity;
  private E[] array;
  private int size = 0;
  private int modCount = 0;
  private ArrayList(int capacity) {
    this.capacity = capacity;
  }

  public static <E> ArrayList<E> newList() {
    return newList(DEFAULT_CAPACITY);
  }

  public static <E> ArrayList<E> newList(int capacity) {
    return new ArrayList<>(capacity);
  }

  public static <E> ArrayListBuilder<E> builder(int capacity) {
    return new ArrayListBuilder<>(capacity);
  }

  public static class ArrayListBuilder<E> {

    private final int capacity;
    private final ArrayList<E> arrayList;

    public ArrayListBuilder(int capacity) {
      this.capacity = capacity;
      this.arrayList = ArrayList.newList();
    }

    @SafeVarargs
    public final ArrayListBuilder<E> add(E... elems) {
      for (E elem : elems) {
        arrayList.add(elem);
      }
      return this;
    }

    public final ArrayList<E> build() {
      return arrayList;
    }
  }

  @Override
  public E get(int index) {
    checkBounds(index);
    return array[index];
  }

  @Override
  public E contains(E elem) {
    throw new UnsupportedOperationException("Not implemented yet!");
  }

  @Override
  public void add(E elem) {
    if (size == capacity) {
      // consider implementing growing the underlying array here
      throw NoCapacityAvailableException.forElement(elem);
    }
    if (array == null) {
      initializeArray(elem);
    }

    modCount++;
    array[size++] = elem;
  }

  @Override
  public void remove(int index) {
    checkBounds(index);

    modCount++;
    for (int i = index + 1; i < size; i++) {
      array[i - 1] = array[i];
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
    return new ArrayListIterator();
  }

  private void checkBounds(int index) {
    if (index >= size) {
      throw new ArrayIndexOutOfBoundsException();
    }
  }

  @SuppressWarnings("unchecked")
  private void initializeArray(E elem) {
    array = (E[]) Array.newInstance(elem.getClass(), capacity);
  }

  class ArrayListIterator implements Iterator<E> {

    private final int expectedModCount = modCount;
    private int i = 0;

    @Override
    public boolean hasNext() {
      return i < size;
    }

    @Override
    public E next() {
      checkForModification();
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      return array[i++];
    }

    private void checkForModification() {
      if (expectedModCount != modCount) {
        throw new ConcurrentModificationException();
      }
    }
  }
}
