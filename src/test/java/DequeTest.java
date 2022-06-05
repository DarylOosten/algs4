import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DequeTest {

    private Deque<Integer> deque;

    @Before
    public void setUp() {
        deque = new Deque<Integer>();
    }

    @Test
    public void testIsEmptyIfEmpty() {
        Assert.assertTrue(deque.isEmpty());
    }

    @Test
    public void testIsEmptyIfNotEmpty() {
        deque.addFirst(0);
        Assert.assertFalse(deque.isEmpty());
    }

    @Test
    public void testSizeIfEmpty() {
        Assert.assertEquals(0, deque.size());
    }

    @Test
    public void testSizeIfNonEmpty() {
        deque.addFirst(0);
        Assert.assertEquals(1, deque.size());
    }

    @Test
    public void testSizeIfReturnToEmpty() {
        deque.addFirst(0);
        deque.removeFirst();
        Assert.assertEquals(0, deque.size());
    }

    @Test(expected = NullPointerException.class)
    public void testAddFirstNull() {
        deque.addFirst(null);
    }

    @Test
    public void testAddFirstWhenEmpty() {
        deque.addFirst(0);
        Assert.assertEquals(Integer.valueOf(0), deque.removeFirst());
    }

    @Test
    public void testAddFirstWhenEmptySetsTailToHead() {
        deque.addFirst(0);
        Assert.assertEquals(Integer.valueOf(0), deque.removeLast());
    }

    @Test
    public void testAddFirstWhenNonEmpty() {
        deque.addFirst(0);
        deque.addFirst(1);
        Assert.assertEquals(Integer.valueOf(1), deque.removeFirst());
    }

    @Test
    public void testAddFirstWhenNonEmptyDoesNotSetTail() {
        deque.addFirst(0);
        deque.addFirst(1);
        Assert.assertEquals(Integer.valueOf(0), deque.removeLast());
    }

    @Test
    public void testAddFirstIncrementsSize() {
        deque.addFirst(0);
        Assert.assertEquals(1, deque.size());
    }

    @Test(expected = NullPointerException.class)
    public void testAddLastNull() {
        deque.addLast(null);
    }

    @Test
    public void testAddLastWhenEmpty() {
        deque.addLast(0);
        Assert.assertEquals(Integer.valueOf(0), deque.removeLast());
    }

    @Test
    public void testAddLastWhenEmptySetsHeadToTail() {
        deque.addLast(0);
        Assert.assertEquals(Integer.valueOf(0), deque.removeFirst());
    }

    @Test
    public void testAddLastWhenNonEmpty() {
        deque.addLast(0);
        deque.addLast(1);
        Assert.assertEquals(Integer.valueOf(1), deque.removeLast());
    }

    @Test
    public void testAddLastWhenNonEmptyDoesNotSetHead() {
        deque.addLast(0);
        deque.addLast(1);
        Assert.assertEquals(Integer.valueOf(0), deque.removeFirst());
    }

    @Test
    public void testAddLastIncrementsSize() {
        deque.addLast(0);
        Assert.assertEquals(1, deque.size());
    }

    @Test(expected = NoSuchElementException.class)
    public void testRemoveFirstWhenEmpty() {
        deque.removeFirst();
    }

    @Test
    public void testRemoveFirstEmptiesDeque() {
        deque.addFirst(0);
        deque.removeFirst();
        Assert.assertTrue(deque.isEmpty());
    }

    @Test
    public void testRemoveFirstMakesLastFirst() {
        deque.addFirst(0);
        deque.addLast(1);
        deque.removeFirst();
        Assert.assertEquals(Integer.valueOf(1), deque.removeFirst());
    }

    @Test
    public void testRemoveFirstDecrementsSize() {
        deque.addFirst(0);
        deque.removeFirst();
        Assert.assertEquals(0, deque.size());
    }

    @Test(expected = NoSuchElementException.class)
    public void testRemoveLastWhenEmpty() {
        deque.removeLast();
    }

    @Test
    public void testRemoveLastEmptiesDeque() {
        deque.addLast(0);
        deque.removeLast();
        Assert.assertTrue(deque.isEmpty());
    }

    @Test
    public void testRemoveLastMakesFirstLast() {
        deque.addFirst(0);
        deque.addLast(1);
        deque.removeLast();
        Assert.assertEquals(Integer.valueOf(0), deque.removeLast());
    }

    @Test
    public void testRemoveLastDecrementsSize() {
        deque.addLast(0);
        deque.removeLast();
        Assert.assertEquals(0, deque.size());
    }

    @Test(expected = NoSuchElementException.class)
    public void testNextOnEmptyIterator() {
        deque.iterator().next();
    }

    @Test
    public void testEmptyIterator() {
        Iterator<Integer> iterator = deque.iterator();
        Assert.assertFalse(iterator.hasNext());
    }

    @Test
    public void testIteratorWithOneElementHasNext() {
        deque.addFirst(0);
        Iterator<Integer> iterator = deque.iterator();
        Assert.assertTrue(iterator.hasNext());
    }

    @Test
    public void testIteratorWithOneElementNext() {
        deque.addFirst(0);
        Iterator<Integer> iterator = deque.iterator();
        Assert.assertEquals(Integer.valueOf(0), iterator.next());
    }

    @Test
    public void testIteratorWithOneElementDoesNotHaveNextAfterOneElement() {
        deque.addFirst(0);
        Iterator<Integer> iterator = deque.iterator();
        iterator.next();
        Assert.assertFalse(iterator.hasNext());
    }

    @Test(expected = NoSuchElementException.class)
    public void testIteratorWithOneElementDoesThrowsExceptionAfterOneElement() {
        deque.addFirst(0);
        Iterator<Integer> iterator = deque.iterator();
        iterator.next();
        iterator.next();
    }

    @Test
    public void testIteratorWithTwoElementsHasNext() {
        deque.addFirst(0);
        deque.addLast(1);
        Iterator<Integer> iterator = deque.iterator();
        Assert.assertTrue(iterator.hasNext());
    }

    @Test
    public void testIteratorWithTwoElementsNext() {
        deque.addFirst(0);
        deque.addLast(1);
        Iterator<Integer> iterator = deque.iterator();
        Assert.assertEquals(Integer.valueOf(0), iterator.next());
    }

    @Test
    public void testIteratorWithTwoElementHasNextAfterOneElement() {
        deque.addFirst(0);
        deque.addLast(1);
        Iterator<Integer> iterator = deque.iterator();
        iterator.next();
        Assert.assertTrue(iterator.hasNext());
    }

    @Test
    public void testIteratorWithTwoElementsHasNextAfterOneElement() {
        deque.addFirst(0);
        deque.addLast(1);
        Iterator<Integer> iterator = deque.iterator();
        iterator.next();
        Assert.assertTrue(iterator.hasNext());
    }

    @Test
    public void testIteratorWithTwoElementsHasTwoElements() {
        deque.addFirst(0);
        deque.addLast(1);
        Iterator<Integer> iterator = deque.iterator();
        iterator.next();
        Assert.assertEquals(Integer.valueOf(1), iterator.next());
    }

    @Test
    public void testHappyFlow() {
        Assert.assertTrue(deque.isEmpty());
        Assert.assertEquals(0, deque.size());
        deque.addFirst(0);
        // 0
        Assert.assertEquals(1, deque.size());
        deque.addLast(1);
        // 0, 1
        Assert.assertEquals(2, deque.size());
        Assert.assertEquals(Integer.valueOf(0), deque.removeFirst());
        // 1
        Assert.assertEquals(1, deque.size());
        deque.addFirst(2);
        // 2, 1
        Assert.assertEquals(2, deque.size());
        Assert.assertEquals(Integer.valueOf(1), deque.removeLast());
        // 2
        deque.addFirst(3);
        // 3, 2
        deque.addFirst(4);
        // 4, 3, 2
        Iterator<Integer> iterator = deque.iterator();
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals(Integer.valueOf(4), iterator.next());
        Assert.assertEquals(Integer.valueOf(3), iterator.next());
        Assert.assertEquals(Integer.valueOf(2), iterator.next());
        Assert.assertFalse(iterator.hasNext());
        Assert.assertEquals(3, deque.size());
        Assert.assertEquals(Integer.valueOf(4), deque.removeFirst());
        // 3, 2
        Assert.assertEquals(Integer.valueOf(2), deque.removeLast());
        // 3
        Assert.assertEquals(Integer.valueOf(3), deque.removeLast());
        Assert.assertTrue(deque.isEmpty());
        Assert.assertEquals(0, deque.size());
    }
}
