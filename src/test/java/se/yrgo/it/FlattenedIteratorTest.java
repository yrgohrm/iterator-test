package se.yrgo.it;

import static org.assertj.core.api.Assertions.*;
import java.util.*;
import org.junit.jupiter.api.*;

/**
 * Tests that should hold for FlattenedIterator.
 * 
 */
class FlattenedIteratorTest {

    @Test
    void testSingleList() {
        var list1 = List.of(1, 2, 3, 4, 5);
        var flatIt = new FlattenedIterator<>(List.of(list1.iterator()));

        assertThat(flatIt).toIterable().containsExactly(list1.toArray(new Integer[0]));
    }

    @Test
    void testEmpty() {
        var flatIt = new FlattenedIterator<>(List.of());

        assertThat(flatIt).toIterable().isEmpty();
    }

    @Test
    void testTwoLists() {
        var list1 = List.of(1, 2, 3, 4, 5);
        var list2 = List.of(1, 2, 3, 4, 5);
        var list3 = new ArrayList<Integer>();
        list3.addAll(list1);
        list3.addAll(list2);

        var flatIt = new FlattenedIterator<>(List.of(list1.iterator(), list2.iterator()));

        assertThat(flatIt).toIterable().containsExactly(list3.toArray(new Integer[0]));
    }

    @Test
    void testWithNullsInList() {
        var list1 = new ArrayList<Integer>();
        list1.add(1);
        list1.add(null);
        list1.add(2);
        list1.add(null);
        list1.add(3);

        var list2 = new ArrayList<Integer>();
        list2.add(null);
        list2.add(2);
        list2.add(null);
        list2.add(null);
        list2.add(3);
        list2.add(null);

        var list3 = new ArrayList<Integer>();
        list3.addAll(list1);
        list3.addAll(list2);

        var flatIt = new FlattenedIterator<>(List.of(list1.iterator(), list2.iterator()));

        assertThat(flatIt).toIterable().containsExactly(list3.toArray(new Integer[0]));
    }

    @Test
    void testWithOneEmptyList() {
        var list1 = List.of(1, 2, 3, 4, 5);
        var list2 = List.of(1, 2, 3, 4, 5);
        List<Integer> emptyList = List.of();

        var list3 = new ArrayList<Integer>();
        list3.addAll(list1);
        list3.addAll(list2);

        var flatIt = new FlattenedIterator<>(
                List.of(list1.iterator(), emptyList.iterator(), list2.iterator()));

        assertThat(flatIt).toIterable().containsExactly(list3.toArray(new Integer[0]));
    }

    @Test
    void testWithOnlyEmptyLists() {
        List<Integer> emptyList1 = List.of();
        List<Integer> emptyList2 = List.of();
        List<Integer> emptyList3 = List.of();

        var flatIt = new FlattenedIterator<>(
                List.of(emptyList1.iterator(), emptyList2.iterator(), emptyList3.iterator()));

        assertThat(flatIt).toIterable().isEmpty();
    }

    @Test
    void testNextBeyond() {
        var list1 = List.of(1, 2, 3, 4, 5);
        var list2 = List.of(1, 2, 3, 4, 5);

        var flatIt = new FlattenedIterator<>(List.of(list1.iterator(), list2.iterator()));

        for (int i = 0; i < list1.size() + list2.size(); ++i) {
            flatIt.next();
        }

        assertThatThrownBy(() -> flatIt.next()).isInstanceOf(NoSuchElementException.class);
    }
}
