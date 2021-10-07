package org.giogt.algorithms.collections.graphs;

import org.giogt.algorithms.collections.Graph.Edge;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

public class DepthFirstGraphTraversal<V> implements GraphTraversal<V> {
  private final Map<V, List<Edge<V>>> vertexEdges;
  private final Set<V> visited;

  public DepthFirstGraphTraversal(Map<V, List<Edge<V>>> vertexEdges) {
    this.vertexEdges = vertexEdges;
    this.visited = new HashSet<>();
  }

  @Override
  public void traverse(Consumer<V> vertexConsumer, V vertex) {
    if (visited.contains(vertex)) {
      return;
    }

    vertexConsumer.accept(vertex);
    visited.add(vertex);
    List<Edge<V>> edges = vertexEdges.get(vertex);
    for (Edge<V> edge : edges) {
      traverse(vertexConsumer, edge.getTarget());
    }
  }
}
