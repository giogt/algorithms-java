package org.giogt.algorithms.collections.exceptions;

import java.text.MessageFormat;

public class NoCapacityAvailableException extends CollectionException {

  private static final String MESSAGE = "No capacity available for element {0}";

  private NoCapacityAvailableException(String message) {
    super(message);
  }

  private NoCapacityAvailableException(String message, Throwable cause) {
    super(message, cause);
  }

  public static NoCapacityAvailableException forElement(Object element) {
    return new NoCapacityAvailableException(
        MessageFormat.format(MESSAGE, element)
    );
  }

  public static NoCapacityAvailableException forElement(Object element, Throwable cause) {
    return new NoCapacityAvailableException(
        MessageFormat.format(MESSAGE, element),
        cause
    );
  }
}
