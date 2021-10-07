package org.giogt.algorithms.collections.lists;

import org.giogt.algorithms.collections.LinkedList.Node;

public class LinkedListReverser {

    public <T> Node<T> reverse(Node<T> head) {
        if (head == null) {
            return null;
        }

        Node<T> currentNode = head;
        Node<T> reversed = new Node<>(currentNode.getValue());
        while (currentNode.getNext() != null) {
            Node<T> previousReversed = reversed;
            reversed = new Node<>(currentNode.getNext().getValue());
            reversed.setNext(previousReversed);

            currentNode = currentNode.getNext();
        }

        return reversed;
    }
}
