package org.giogt.algorithms.collections.graphs;

import org.giogt.algorithms.collections.Graph.Edge;

import java.util.*;
import java.util.function.Consumer;

public class BreadthFirstGraphTraversal<V> implements GraphTraversal<V> {
  private final Map<V, List<Edge<V>>> vertexEdges;
  private final Set<V> visited;
  private final Queue<V> queue;

  public BreadthFirstGraphTraversal(
      Map<V, List<Edge<V>>> vertexEdges) {
    this.vertexEdges = vertexEdges;
    this.visited = new HashSet<>();
    this.queue = new LinkedList<>();
  }

  @Override
  public void traverse(Consumer<V> vertexConsumer, V vertex) {
    visited.add(vertex);
    queue.add(vertex);

    while (!queue.isEmpty()) {
      V currentVertex = queue.remove();
      vertexConsumer.accept(currentVertex);

      for (Edge<V> edge : vertexEdges.get(currentVertex)) {
        V nextVertex = edge.getTarget();
        if (!visited.contains(nextVertex)) {
          visited.add(nextVertex);
          queue.add(nextVertex);
        }
      }
    }
  }
}
