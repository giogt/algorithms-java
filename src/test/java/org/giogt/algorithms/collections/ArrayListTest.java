package org.giogt.algorithms.collections;

import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ArrayListTest {

  @Test
  public void newList_mustCreateEmptyList() {
    ArrayList<String> list = ArrayList.newList();
    assertThat(list.size(), is(0));
    assertThat(list.iterator().hasNext(), is(false));
  }

  @Test
  public void verify_empty_ArrayList() {
    ArrayList<String> list = ArrayList.newList();

    assertThat(list.size(), is(0));
    assertThrows(IndexOutOfBoundsException.class, () -> list.get(0));
    assertThrows(IndexOutOfBoundsException.class, () -> list.get(1));
    assertThrows(IndexOutOfBoundsException.class, () -> list.remove(0));
    assertThrows(IndexOutOfBoundsException.class, () -> list.remove(1));

    Iterator<String> it = list.iterator();
    assertThat(it.hasNext(), is(false));
  }

  @Test
  public void verify_oneElement_ArrayList() {
    ArrayList<String> list = ArrayList.newList(10);
    list.add("foo");

    assertThat(list.size(), is(1));
    assertThat(list.get(0), is("foo"));
    assertThrows(IndexOutOfBoundsException.class, () -> list.get(1));
    assertThrows(IndexOutOfBoundsException.class, () -> list.remove(1));

    Iterator<String> it = list.iterator();
    assertThat(it.hasNext(), is(true));
    assertThat(it.next(), is("foo"));
    assertThat(it.hasNext(), is(false));
    assertThrows(NoSuchElementException.class, it::next);

    list.remove(0);

    assertThat(list.size(), is(0));
    assertThrows(IndexOutOfBoundsException.class, () -> list.get(0));
    assertThrows(IndexOutOfBoundsException.class, () -> list.get(1));
    assertThrows(IndexOutOfBoundsException.class, () -> list.remove(0));
    assertThrows(IndexOutOfBoundsException.class, () -> list.remove(1));

    Iterator<String> it2 = list.iterator();
    assertThat(it2.hasNext(), is(false));
    assertThrows(NoSuchElementException.class, it2::next);
  }

  @Test
  public void verify_multipleElements_ArrayList() {
    ArrayList<String> list = ArrayList.newList();
    list.add("foo");
    list.add("bar");
    list.add("hello");
    list.add("world");

    assertThat(list.size(), is(4));
    assertThat(list.get(0), is("foo"));
    assertThat(list.get(1), is("bar"));
    assertThat(list.get(2), is("hello"));
    assertThat(list.get(3), is("world"));
    assertThrows(IndexOutOfBoundsException.class, () -> list.get(4));
    assertThrows(IndexOutOfBoundsException.class, () -> list.remove(4));

    Iterator<String> it = list.iterator();
    assertThat(it.hasNext(), is(true));
    assertThat(it.next(), is("foo"));
    assertThat(it.hasNext(), is(true));
    assertThat(it.next(), is("bar"));
    assertThat(it.hasNext(), is(true));
    assertThat(it.next(), is("hello"));
    assertThat(it.hasNext(), is(true));
    assertThat(it.next(), is("world"));
    assertThat(it.hasNext(), is(false));
    assertThrows(NoSuchElementException.class, it::next);

    list.remove(3);
    list.remove(0);

    assertThat(list.size(), is(2));
    Iterator<String> it2 = list.iterator();
    assertThat(it2.hasNext(), is(true));
    assertThat(it2.next(), is("bar"));
    assertThat(it2.hasNext(), is(true));
    assertThat(it2.next(), is("hello"));
    assertThat(it2.hasNext(), is(false));

    assertThrows(NoSuchElementException.class, it2::next);

    list.remove(1);
    list.remove(0);

    assertThat(list.size(), is(0));
    assertThrows(IndexOutOfBoundsException.class, () -> list.get(0));
    assertThrows(IndexOutOfBoundsException.class, () -> list.get(1));
    assertThrows(IndexOutOfBoundsException.class, () -> list.remove(0));
    assertThrows(IndexOutOfBoundsException.class, () -> list.remove(1));

    Iterator<String> it3 = list.iterator();
    assertThat(it3.hasNext(), is(false));
    assertThrows(NoSuchElementException.class, it3::next);
  }

  @Test
  public void verify_equals() {
    ArrayList<String> list1 = ArrayList.newList();
    ArrayList<String> list2 = ArrayList.newList();
    assertThat(list1.equals(list2), is(true));

    list1.add("foo");
    list2.add("foo");
    assertThat(list1.equals(list2), is(true));

    list1.add("bar");
    list2.add("bar");
    assertThat(list1.equals(list2), is(true));

    list1.remove(1);
    list2.remove(1);
    assertThat(list1.equals(list2), is(true));

    list1.remove(0);
    list2.remove(0);
    assertThat(list1.equals(list2), is(true));
  }
}