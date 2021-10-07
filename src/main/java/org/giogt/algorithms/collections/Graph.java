package org.giogt.algorithms.collections;

import lombok.Builder;
import lombok.Data;
import org.giogt.algorithms.collections.exceptions.NoSuchVertexException;
import org.giogt.algorithms.collections.graphs.BreadthFirstGraphTraversal;
import org.giogt.algorithms.collections.graphs.DepthFirstGraphTraversal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class Graph<V> {
  private final Map<V, List<Edge<V>>> vertexEdges;
  private int size = 0;

  public Graph() {
    this.vertexEdges = new HashMap<>();
  }

  public void addVertex(V vertex) {
    if (!vertexEdges.containsKey(vertex)) {
      vertexEdges.put(vertex, new ArrayList<>(0));
      size++;
    }
  }

  public void addEdge(V source, V target) {
    addEdge(source, target, 0);
  }

  public void addEdge(V source, V target, int weight) {
    if (!vertexEdges.containsKey(source)) {
      throw NoSuchVertexException.forVertex(source);
    }
    if (!vertexEdges.containsKey(target)) {
      throw NoSuchVertexException.forVertex(target);
    }

    vertexEdges.get(source).add(Edge.<V>builder()
        .source(source)
        .target(target)
        .weight(weight)
        .build()
    );
  }

  public void breadthFirstTraverse(V vertex, Consumer<V> vertexConsumer) {
    new BreadthFirstGraphTraversal<>(vertexEdges)
        .traverse(vertexConsumer, vertex);
  }

  public void depthFirstTraverse(V vertex, Consumer<V> vertexConsumer) {
    new DepthFirstGraphTraversal<>(vertexEdges)
        .traverse(vertexConsumer, vertex);
  }

  public int size() {
    return size;
  }

  @Data
  @Builder
  public static class Edge<V> {
    private final V source;
    private final V target;
    private final int weight;
  }
}
