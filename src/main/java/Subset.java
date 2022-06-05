/**
 * Part 1, assignment 2, subset.
 * 
 * Subset client. Write a client program Subset.java that takes a command-line
 * integer k; reads in a sequence of N strings from standard input using
 * StdIn.readString(); and prints out exactly k of them, uniformly at random.
 * Each item from the sequence can be printed out at most once. You may assume
 * that k ≥ 0 and no greater than the number of string N on standard input.
 * 
 * The running time of Subset must be linear in the size of the input. You may
 * use only a constant amount of memory plus either one Deque or RandomizedQueue
 * object of maximum size at most N, where N is the number of strings on
 * standard input. (For an extra challenge, use only one Deque or
 * RandomizedQueue object of maximum size at most k.) It should have the
 * following API.
 * 
 * @see http://coursera.cs.princeton.edu/algs4/checklists/queues.html
 * 
 * @author daryl
 * 
 */
public class Subset {

    /**
     * The randomized queue that provides the random elements.
     */
    private RandomizedQueue<String> queue;

    public Subset() {
        super();
        this.queue = new RandomizedQueue<String>();

        while (!StdIn.isEmpty()) {
            queue.enqueue(StdIn.readString());
        }
    }

    /**
     * @param args
     *            contains the number of elements to pick.
     */
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        Subset subset = new Subset();

        for (int i = 0; i < k; i++) {
            System.out.println(subset.random());
        }
    }

    /**
     * @return a random element.
     */
    private String random() {
        return queue.dequeue();
    }
}