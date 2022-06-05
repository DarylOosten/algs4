import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RandomizedQueueTest {

    private RandomizedQueue<String> queue;

    @Before
    public void setUp() throws Exception {
        queue = new RandomizedQueue<String>();
    }

    @Test
    public void testEnqueue() {
        queue.enqueue("1");
        Assert.assertEquals(1, queue.size());
    }

    @Test(expected = NullPointerException.class)
    public void testEnqueueNull() {
        queue.enqueue(null);
    }

    @Test
    public void testDequeue() {
        queue.enqueue("1");
        Assert.assertEquals("1", queue.dequeue());
    }

    @Test(expected = NoSuchElementException.class)
    public void testDequeueWhenEmpty() {
        queue.dequeue();
    }

    @Test
    public void testDequeueToEmpty() {
        queue.enqueue("1");
        queue.dequeue();
        Assert.assertTrue(queue.isEmpty());
    }

    @Test
    public void testSample() {
        queue.enqueue("1");
        Assert.assertEquals("1", queue.sample());
    }

    @Test
    public void testSampleDoesNotRemove() {
        queue.enqueue("1");
        queue.sample();
        Assert.assertEquals(1, queue.size());
    }

    @Test
    public void testIterator() {
        queue.enqueue("1");
        Iterator<String> iterator = queue.iterator();
        Assert.assertEquals("1", iterator.next());
    }
}
