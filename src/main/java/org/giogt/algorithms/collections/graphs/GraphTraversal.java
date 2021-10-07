package org.giogt.algorithms.collections.graphs;

import java.util.function.Consumer;

public interface GraphTraversal<V> {
  void traverse(Consumer<V> vertexConsumer, V vertex);
}
