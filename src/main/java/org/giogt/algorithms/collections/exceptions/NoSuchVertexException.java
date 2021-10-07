package org.giogt.algorithms.collections.exceptions;

import java.text.MessageFormat;

public class NoSuchVertexException extends CollectionException {
  private static final String MESSAGE = "No such vertex: {0}";

  private NoSuchVertexException(String message) {
    super(message);
  }

  public NoSuchVertexException(String message, Throwable cause) {
    super(message, cause);
  }

  public static <V> NoSuchVertexException forVertex(V vertex) {
    return new NoSuchVertexException(
        MessageFormat.format(MESSAGE, vertex)
    );
  }

  public static <V> NoSuchVertexException forVertex(V vertex, Throwable cause) {
    return new NoSuchVertexException(
        MessageFormat.format(MESSAGE, vertex),
        cause
    );
  }
}
