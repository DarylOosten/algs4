import java.util.LinkedList;
import java.util.List;
import java.util.NavigableSet;
import java.util.TreeSet;

/**
 * Part 1, assignment 5: Kd-Trees
 * 
 * Write a data type to represent a set of points in the unit square (all points
 * have x- and y-coordinates between 0 and 1) using a 2d-tree to support
 * efficient range search (find all of the points contained in a query
 * rectangle) and nearest neighbor search (find a closest point to a query
 * point). 2d-trees have numerous applications, ranging from classifying
 * astronomical objects to computer animation to speeding up neural networks to
 * mining data to image retrieval.
 * 
 * Brute-force implementation.
 * 
 * Write a mutable data type PointSET.java that represents a set of points in
 * the unit square. Implement the following API by using a red-black BST (using
 * either SET from algs4.jar or java.util.TreeSet).
 * 
 * Your implementation should support insert() and contains() in time
 * proportional to the logarithm of the number of points in the set in the worst
 * case; it should support nearest() and range() in time proportional to the
 * number of points in the set.
 * 
 * @author daryl
 * 
 */
public class PointSET {

    private final NavigableSet<Point2D> tree;

    /**
     * construct an empty set of points
     */
    public PointSET() {
        tree = new TreeSet<Point2D>();
    }

    /**
     * @return is the set empty?
     */
    public boolean isEmpty() {
        return tree.isEmpty();
    }

    /**
     * @return number of points in the set
     */
    public int size() {
        return tree.size();
    }

    /**
     * add the point p to the set (if it is not already in the set)
     * 
     * @param p
     */
    public void insert(Point2D p) {
        tree.add(p);
    }

    /**
     * does the set contain the point p?
     * 
     * @param p
     * @return
     */
    public boolean contains(Point2D p) {
        return tree.contains(p);
    }

    /**
     * draw all of the points to standard draw
     */
    public void draw() {
        for (Point2D p : tree) {
            p.draw();
        }
    }

    /**
     * all points in the set that are inside the rectangle
     * 
     * @param rect
     * @return
     */
    public Iterable<Point2D> range(RectHV rect) {
        List<Point2D> results = new LinkedList<Point2D>();
        for (Point2D p : tree) {
            if (rect.contains(p)) {
                results.add(p);
            }
        }
        return results;
    }

    /**
     * a nearest neighbor in the set to p; null if set is empty
     * 
     * @param p
     * @return
     */
    public Point2D nearest(Point2D p) {
        if (tree.isEmpty()) {
            return null;
        }
        Point2D result = null;
        for (Point2D candidate : tree) {
            if (result == null
                    || candidate.distanceTo(p) < result.distanceTo(p)) {
                result = candidate;
            }
        }
        return result;
    }
}