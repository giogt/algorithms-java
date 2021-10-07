package org.giogt.algorithms.collections;

import org.giogt.algorithms.collections.LinkedList.Node;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.List;

public class LinkedListNodeAsserts {

  public static <A> void assertEquals(Node<A> expected, Node<A> actual) {
    Assertions.assertEquals(toList(expected), toList(actual));
  }

  private static <A> List<A> toList(Node<A> listNode) {
    if (listNode == null) {
      return null;
    }

    List<A> list = new ArrayList<>();

    Node<A> currentNode = listNode;
    while (currentNode != null) {
      list.add(listNode.getValue());
      currentNode = currentNode.getNext();
    }

    return list;
  }
}
