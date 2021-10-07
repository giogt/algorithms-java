package org.giogt.algorithms.collections;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class GraphTest {

  @Test
  public void depthFirstTraverse_test() {
    Graph<Integer> graph = testGraph1();
    List<Integer> expected = Arrays.asList(0, 1, 8, 7, 3, 2, 4, 5, 6, 10, 11, 9);

    List<Integer> result = new ArrayList<>(graph.size());
    graph.depthFirstTraverse(0, result::add);

    MatcherAssert.assertThat(result, Matchers.is(expected));
  }

  @Test
  public void breadthFirstTraverse_test() {
    Graph<Integer> graph = testGraph1();
    List<Integer> expected = Arrays.asList(0, 1, 9, 8, 7, 3, 6, 10, 2, 4, 5, 11);

    List<Integer> result = new ArrayList<>(graph.size());
    graph.breadthFirstTraverse(0, result::add);

    MatcherAssert.assertThat(result, Matchers.is(expected));
  }

  /**
   * Returns a test graph (see <p>org.giogt.algorithms.collections.graph1.png</p> in the test
   * resources for a visual representation).
   */
  private Graph<Integer> testGraph1() {
    Graph<Integer> graph = new Graph<>();
    graph.addVertex(0);
    graph.addVertex(1);
    graph.addVertex(2);
    graph.addVertex(3);
    graph.addVertex(4);
    graph.addVertex(5);
    graph.addVertex(6);
    graph.addVertex(7);
    graph.addVertex(8);
    graph.addVertex(9);
    graph.addVertex(10);
    graph.addVertex(11);
    graph.addVertex(12);

    graph.addEdge(0, 1);
    graph.addEdge(0, 9);
    graph.addEdge(1, 0);
    graph.addEdge(1, 8);
    graph.addEdge(2, 3);
    graph.addEdge(3, 2);
    graph.addEdge(3, 4);
    graph.addEdge(3, 5);
    graph.addEdge(4, 3);
    graph.addEdge(5, 3);
    graph.addEdge(5, 6);
    graph.addEdge(6, 5);
    graph.addEdge(6, 7);
    graph.addEdge(7, 3);
    graph.addEdge(7, 6);
    graph.addEdge(7, 10);
    graph.addEdge(8, 1);
    graph.addEdge(8, 7);
    graph.addEdge(9, 8);
    graph.addEdge(10, 11);
    graph.addEdge(11, 7);

    return graph;
  }

}