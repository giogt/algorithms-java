package org.giogt.algorithms.collections.lists;

import org.giogt.algorithms.collections.LinkedList.Node;
import org.giogt.algorithms.collections.LinkedListNodeAsserts;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

class LinkedListReverserTest {

    @Test
    public void reverse_forNullList_mustReturnNull() {
        LinkedListReverser linkedListReverser = newLinkedListReverser();
        Node<Integer> result = linkedListReverser.reverse(null);

        assertThat(result, is(nullValue()));
    }

    @Test
    public void reverse_forNotNullList_mustReturnNull() {
        Node<Integer> list = newList(1, 2, 3, 4, 5);
        Node<Integer> expected = newList(5, 4, 3, 2, 1);

        LinkedListReverser linkedListReverser = newLinkedListReverser();
        Node<Integer> result = linkedListReverser.reverse(list);

        LinkedListNodeAsserts.assertEquals(expected, result);
    }

    private LinkedListReverser newLinkedListReverser() {
        return new LinkedListReverser();
    }

    @SafeVarargs
    private <T> Node<T> newList(T... values) {
        if (values.length == 0) {
            return null;
        }

        Node<T> list = new Node<>(values[0]);
        Node<T> currentNode = list;
        for (int i = 1; i < values.length; i++) {
            T value = values[i];
            currentNode.setNext(new Node<>(value));
            currentNode = currentNode.getNext();
        }
        return list;
    }
}