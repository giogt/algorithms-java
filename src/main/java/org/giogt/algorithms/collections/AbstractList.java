package org.giogt.algorithms.collections;

import java.util.Iterator;
import java.util.Objects;

public abstract class AbstractList<E> implements List<E> {

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    List<?> that = (List<?>) o;
    if (size() != that.size()) {
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

  @Override
  public String toString() {
    Iterator<E> it = iterator();
    if (!it.hasNext()) {
      return "[]";
    }

    StringBuilder sb = new StringBuilder();
    sb.append('[');
    while (true) {
      E e = it.next();
      sb.append(e == this ? "(this Collection)" : e);
      if (!it.hasNext()) {
        return sb.append(']').toString();
      }
      sb.append(',').append(' ');
    }
  }
}
