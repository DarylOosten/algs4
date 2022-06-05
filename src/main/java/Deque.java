import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Part 1, assignment 2, dequeue.
 * 
 * Dequeue. A double-ended queue or deque (pronounced "deck") is a
 * generalization of a stack and a queue that supports inserting and removing
 * items from either the front or the back of the data structure. Create a
 * generic data type Deque that implements the following API:
 * 
 * Throw a NullPointerException if the client attempts to add a null item; throw
 * a java.util.NoSuchElementException if the client attempts to remove an item
 * from an empty deque; throw an UnsupportedOperationException if the client
 * calls the remove() method in the iterator; throw a
 * java.util.NoSuchElementException if the client calls the next() method in the
 * iterator and there are no more items to return.
 * 
 * Your deque implementation must support each deque operation in constant
 * worst-case time and use space proportional to the number of items currently
 * in the deque. Additionally, your iterator implementation must support the
 * operations next() and hasNext() (plus construction) in constant worst-case
 * time and use a constant amount of extra space per iterator.
 * 
 * @see http://coursera.cs.princeton.edu/algs4/checklists/queues.html
 * 
 * @author daryl
 * 
 * @param <Item>
 */
public class Deque<Item> implements Iterable<Item> {

    /**
     * Head element in the deque.
     */
    private Element<Item> head;
    /**
     * Tail element in the deque.
     */
    private Element<Item> tail;
    /**
     * Size of the deque.
     */
    private int size;

    /**
     * Construct an empty deque.
     */
    public Deque() {
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        // Part of the required class signature, but unused.
    }

    /**
     * Is the deque empty?
     * 
     * @return true if it is empty.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * @return the number of items on the deque
     */
    public int size() {
        return size;
    }

    /**
     * Insert the item at the front.
     * 
     * @param item
     *            to be added as head.
     */
    public void addFirst(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }

        head = new Element<Item>(item, head, null);

        if (head.next != null) {
            head.next.prev = head;
        }
        if (tail == null) {
            tail = head;
        }
        size++;
    }

    /**
     * Insert the item at the end.
     * 
     * @param item
     *            to be added as tail.
     */
    public void addLast(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }

        tail = new Element<Item>(item, null, tail);

        if (tail.prev != null) {
            tail.prev.next = tail;
        }
        if (head == null) {
            head = tail;
        }
        size++;
    }

    /**
     * Delete and return the item at the front.
     * 
     * @return the head item.
     */
    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Element<Item> detachedHead = head;

        head = head.next;
        if (head != null) {
            head.prev = null;
        }
        if (tail == detachedHead) {
            tail = null;
        }

        Item item = detachedHead.item;
        detachedHead.clear();
        size--;
        return item;
    }

    /**
     * Delete and return the item at the end.
     * 
     * @return the tail item.
     */
    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Element<Item> detachedTail = tail;

        tail = tail.prev;
        if (tail != null) {
            tail.next = null;
        }
        if (head == detachedTail) {
            head = null;
        }

        Item item = detachedTail.item;
        detachedTail.clear();
        size--;
        return item;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Iterable#iterator()
     */
    public Iterator<Item> iterator() {
        return new DequeIterator(this);
    }

    /**
     * Element wrapper for items. Forms a double linked list.
     * 
     * @author daryl
     * 
     * @param <Item>
     */
    private static class Element<Item> {
        /**
         * The item contained in this element.
         */
        private Item item;
        /**
         * The next element, or null when there is none.
         */
        private Element<Item> next;
        /**
         * The previous element, or null when there is none.
         */
        private Element<Item> prev;

        public Element(Item item, Element<Item> next, Element<Item> prev) {
            super();
            this.item = item;
            this.next = next;
            this.prev = prev;
        }

        private void clear() {
            item = null;
            next = null;
            prev = null;
        }
    }

    /**
     * Iterator for a deque. Does not check for concurrent modification and does
     * not support removal.
     * 
     * @author daryl
     * 
     */
    private class DequeIterator implements Iterator<Item> {
        /**
         * The next element to be iterated, or null when there is none.
         */
        private Element<Item> next;

        public DequeIterator(Deque<Item> deque) {
            super();
            next = deque.head;
        }

        /*
         * (non-Javadoc)
         * 
         * @see java.util.Iterator#hasNext()
         */
        public boolean hasNext() {
            checkConcurrentModification();
            return next != null;
        }

        /*
         * (non-Javadoc)
         * 
         * @see java.util.Iterator#next()
         */
        public Item next() {
            if (next == null) {
                throw new NoSuchElementException();
            }
            checkConcurrentModification();
            Item item = next.item;
            next = next.next;
            return item;
        }

        /*
         * (non-Javadoc)
         * 
         * @see java.util.Iterator#remove()
         */
        public void remove() {
            throw new UnsupportedOperationException();
        }

        /**
         * Checks for concurrent modification exceptions.
         */
        private void checkConcurrentModification() {
            if (next == null) {
                return;
            }
            if (next.next != null && next.next.prev != next) {
                throw new ConcurrentModificationException();
            }
            if (next.prev != null && next.prev.next != next) {
                throw new ConcurrentModificationException();
            }
        }
    }
}