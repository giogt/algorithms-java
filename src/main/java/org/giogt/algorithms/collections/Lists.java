package org.giogt.algorithms.collections;

import java.util.Comparator;
import java.util.Optional;

/**
 * Utilities for lists.
 */
public class Lists {

  // prevent instantiation
  private Lists() {
  }

  /**
   * Returns the index of the specified value in the list if found, otherwise an empty option, using
   * binary search.
   * <p>
   * The specified list must be sorted before invoking this method.
   */
  public static <E> Optional<Integer> binarySearch(
      List<E> list,
      E value,
      Comparator<E> comparator) {

    int low = 0;
    int high = list.size();

    while (low <= high) {
      int mid = (low + high) / 2; // we do not take overflow into account for this exercise
      E midValue = list.get(mid);

      if (comparator.compare(midValue, value) > 0) {
        high = mid - 1;
      } else if (comparator.compare(midValue, value) < 0) {
        low = mid + 1;
      } else {
        return Optional.of(mid);
      }
    }
    return Optional.empty();
  }
}
