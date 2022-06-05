import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Part 1, assignment 2, randomized queue.
 * 
 * Randomized queue. A randomized queue is similar to a stack or queue, except
 * that the item removed is chosen uniformly at random from items in the data
 * structure. Create a generic data type RandomizedQueue that implements the
 * following API:
 * 
 * Throw a NullPointerException if the client attempts to add a null item; throw
 * a java.util.NoSuchElementException if the client attempts to sample or
 * dequeue an item from an empty randomized queue; throw an
 * UnsupportedOperationException if the client calls the remove() method in the
 * iterator; throw a java.util.NoSuchElementException if the client calls the
 * next() method in the iterator and there are no more items to return.
 * 
 * Your randomized queue implementation must support each randomized queue
 * operation (besides creating an iterator) in constant amortized time and use
 * space proportional to the number of items currently in the queue. That is,
 * any sequence of M randomized queue operations (starting from an empty queue)
 * should take at most cM steps in the worst case, for some constant c.
 * Additionally, your iterator implementation must support construction in time
 * linear in the number of items and it must support the operations next() and
 * hasNext() in constant worst-case time; you may use a linear amount of extra
 * memory per iterator. The order of two or more iterators to the same
 * randomized queue should be mutually independent; each iterator must maintain
 * its own random order.
 * 
 * @see http://coursera.cs.princeton.edu/algs4/checklists/queues.html
 * 
 * @author daryl
 * 
 * @param <Item>
 */
public class RandomizedQueue<Item> implements Iterable<Item> {

    /**
     * Minimum capacity of an queue.
     */
    private static final int MINIMUM_CAPACITY = 8;

    /**
     * The elements contained in this queue.
     */
    private Item[] elements;
    /**
     * The current size of the queue.
     */
    private int size;
    /**
     * The head of the queue.
     */
    private int head;

    /**
     * Construct an empty randomized queue.
     */
    public RandomizedQueue() {
        elements = (Item[]) new Object[MINIMUM_CAPACITY];
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
    }

    /**
     * Is the queue empty?
     * 
     * @return true if empty.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * @return the number of items on the queue.
     */
    public int size() {
        return size;
    }

    /**
     * Add the item.
     * 
     * @param item
     *            to be enqueued.
     */
    public void enqueue(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }

        grow();
        elements[head++] = item;
        size++;
    }

    /**
     * Delete and return a random item.
     * 
     * @return a uniformly random item.
     */
    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        int random = randomNonNull();
        Item item = elements[random];
        elements[random] = null;

        size--;
        shrink();
        return item;
    }

    /**
     * @return (but do not delete) a random item.
     */
    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return elements[randomNonNull()];
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Iterable#iterator()
     */
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator<Item>(this);
    }

    /**
     * Doubles the size of the queue if it is full enough.
     */
    private void grow() {
        if (head == elements.length) {
            compress();
        }
        if (size != elements.length) {
            return;
        }

        elements = Arrays.copyOf(elements, elements.length * 2);
    }

    /**
     * Halves the size of the queue if it is empty enough.
     */
    private void shrink() {
        if (size > elements.length / 4 || elements.length < MINIMUM_CAPACITY) {
            return;
        }

        compress();
        elements = Arrays.copyOf(elements, elements.length / 2);
    }

    /**
     * Compresses the elements in the queue if it starts getting full.
     */
    private void compress() {
        if (head == size) {
            return;
        }

        Item[] oldElements = elements;
        elements = (Item[]) new Object[elements.length];
        int i = 0;
        for (Item item : oldElements) {
            if (item != null) {
                elements[i++] = item;
            }
        }
        head = i;
    }

    /**
     * @return the index of a uniformly random non null element.
     */
    private int randomNonNull() {
        int random;
        do {
            random = StdRandom.uniform(elements.length);
        } while (elements[random] == null);
        return random;
    }

    /**
     * Iterator for the randomized queue that iterates in a random order.
     * 
     * @author daryl
     * 
     * @param <Item>
     */
    private static class RandomizedQueueIterator<Item> implements
            Iterator<Item> {

        /**
         * Elements to be iterated.
         */
        private final Item[] elements;
        /**
         * Index of the next item to be iterated.
         */
        private int index;

        /**
         * @param queue
         *            to iterate.
         */
        private RandomizedQueueIterator(RandomizedQueue<Item> queue) {
            this.elements = (Item[]) new Object[queue.size];
            int i = 0;
            for (Item item : queue.elements) {
                if (item != null) {
                    elements[i++] = item;
                }
            }
            StdRandom.shuffle(elements);
            index = 0;
        }

        /*
         * (non-Javadoc)
         * 
         * @see java.util.Iterator#hasNext()
         */
        public boolean hasNext() {
            return index < elements.length;
        }

        /*
         * (non-Javadoc)
         * 
         * @see java.util.Iterator#next()
         */
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item item = elements[index++];
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
    }
}
