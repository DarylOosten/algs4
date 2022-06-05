import java.util.LinkedList;
import java.util.List;

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
 * 2d-tree implementation
 * 
 * Write a mutable data type KdTree.java that uses a 2d-tree to implement the
 * same API (but replace PointSET with KdTree). A 2d-tree is a generalization of
 * a BST to two-dimensional keys. The idea is to build a BST with points in the
 * nodes, using the x- and y-coordinates of the points as keys in strictly
 * alternating sequence.
 * 
 * Search and insert. The algorithms for search and insert are similar to those
 * for BSTs, but at the root we use the x-coordinate (if the point to be
 * inserted has a smaller x-coordinate than the point at the root, go left;
 * otherwise go right); then at the next level, we use the y-coordinate (if the
 * point to be inserted has a smaller y-coordinate than the point in the node,
 * go left; otherwise go right); then at the next level the x-coordinate, and so
 * forth.
 * 
 * Draw. A 2d-tree divides the unit square in a simple way: all the points to
 * the left of the root go in the left subtree; all those to the right go in the
 * right subtree; and so forth, recursively. Your draw() method should draw all
 * of the points to standard draw in black and the subdivisions in red (for
 * vertical splits) and blue (for horizontal splits). This method need not be
 * efficient—it is primarily for debugging.
 * 
 * The prime advantage of a 2d-tree over a BST is that it supports efficient
 * implementation of range search and nearest neighbor search. Each node
 * corresponds to an axis-aligned rectangle in the unit square, which encloses
 * all of the points in its subtree. The root corresponds to the unit square;
 * the left and right children of the root corresponds to the two rectangles
 * split by the x-coordinate of the point at the root; and so forth.
 * 
 * Range search. To find all points contained in a given query rectangle, start
 * at the root and recursively search for points in both subtrees using the
 * following pruning rule: if the query rectangle does not intersect the
 * rectangle corresponding to a node, there is no need to explore that node (or
 * its subtrees). A subtree is searched only if it might contain a point
 * contained in the query rectangle.
 * 
 * Nearest neighbor search. To find a closest point to a given query point,
 * start at the root and recursively search in both subtrees using the following
 * pruning rule: if the closest point discovered so far is closer than the
 * distance between the query point and the rectangle corresponding to a node,
 * there is no need to explore that node (or its subtrees). That is, a node is
 * searched only if it might contain a point that is closer than the best one
 * found so far. The effectiveness of the pruning rule depends on quickly
 * finding a nearby point. To do this, organize your recursive method so that
 * when there are two possible subtrees to go down, you always choose the
 * subtree that is on the same side of the splitting line as the query point as
 * the first subtree to explore—the closest point found while exploring the
 * first subtree may enable pruning of the second subtree.
 * 
 * @author daryl
 * 
 */
public class KdTree {

    /**
     * The root node of this tree.
     */
    private Node root;
    /**
     * The size of this tree.
     */
    private int size;

    /**
     * construct an empty set of points
     */
    public KdTree() {
        root = null;
        size = 0;
    }

    /**
     * is the set empty?
     * 
     * @return
     */
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * number of points in the set
     * 
     * @return
     */
    public int size() {
        return size;
    }

    /**
     * add the point p to the set (if it is not already in the set)
     * 
     * @param p
     */
    public void insert(Point2D p) {
        if (isEmpty()) {
            root = new Node(p);
            size++;
        } else if (root.insert(p)) {
            size++;
        }
    }

    /**
     * does the set contain the point p?
     * 
     * @param p
     * @return
     */
    public boolean contains(Point2D p) {
        return !isEmpty() && root.contains(p);
    }

    /**
     * draw all of the points to standard draw
     */
    public void draw() {
        if (!isEmpty()) {
            root.draw();
        }
    }

    /**
     * all points in the set that are inside the rectangle
     * 
     * @param rect
     * @return
     */
    public Iterable<Point2D> range(RectHV rect) {
        if (isEmpty()) {
            return new LinkedList<Point2D>();
        }
        return root.range(rect);
    }

    /**
     * a nearest neighbor in the set to p; null if set is empty
     * 
     * @param p
     * @return
     */
    public Point2D nearest(Point2D p) {
        if (isEmpty()) {
            return null;
        }
        return root.nearest(p);
    }

    /**
     * @author daryl
     * 
     */
    private static class Node {

        /**
         * The root node divides the plane vertically, each successive level
         * alternates between horizontal and vertical.
         */
        private static final boolean ROOT_IS_VERTICAL = true;

        /**
         * contained in this node.
         */
        private final Point2D point;
        /**
         * the node that refers to the left/top halve of the plane that this
         * node divides.
         */
        private Node left;
        /**
         * the node that refers to the right/bottom halve of the plane that this
         * node divides.
         */
        private Node right;

        /**
         * @param point
         *            contained in this node.
         */
        protected Node(Point2D point) {
            super();
            this.point = point;
        }

        /**
         * @param p
         *            to be inserted.
         * @return true if the given point could be inserted.
         */
        protected boolean insert(Point2D p) {
            return insert(p, ROOT_IS_VERTICAL);
        }

        /**
         * @param p
         *            to be tested.
         * @return true if this subtree contains the given node.
         */
        protected boolean contains(Point2D p) {
            return contains(p, ROOT_IS_VERTICAL);
        }

        /**
         * Draws the subtree of this node.
         */
        protected void draw() {
            point.draw();
            if (left != null) {
                left.draw();
            }
            if (right != null) {
                right.draw();
            }
        }

        /**
         * @param rect
         *            the range.
         * @return the points that fall in range.
         */
        protected Iterable<Point2D> range(RectHV rect) {
            return range(rect, ROOT_IS_VERTICAL, new LinkedList<Point2D>());
        }

        /**
         * @param p
         *            to be tested.
         * @return the nearest point in the subtree of this node to the given
         *         point.
         */
        protected Point2D nearest(Point2D p) {
            return nearest(p, ROOT_IS_VERTICAL);
        }

        /**
         * @param p
         *            to be inserted.
         * @param vertical
         *            true if this node divides the plane vertically, false if
         *            not.
         * @return true if the point was inserted, false if it wasn't.
         */
        private boolean insert(Point2D p, boolean vertical) {
            if (point.equals(p)) {
                return false;
            }

            if (isLeft(p, vertical)) {
                if (left == null) {
                    left = new Node(p);
                } else {
                    return left.insert(p, !vertical);
                }
            } else {
                if (right == null) {
                    right = new Node(p);
                } else {
                    return right.insert(p, !vertical);
                }
            }
            return true;
        }

        /**
         * @param p
         *            to be tested.
         * @param vertical
         *            true if this node divides the plane vertically, false if
         *            not.
         * @return true if this node or its subtree contains the given point.
         */
        private boolean contains(Point2D p, boolean vertical) {
            if (point.equals(p)) {
                return true;
            }
            if (left != null && isLeft(p, vertical)) {
                return left.contains(p, !vertical);
            } else if (right != null) {
                return right.contains(p, !vertical);
            }
            return false;
        }

        /**
         * @param rect
         *            the range.
         * @param vertical
         *            true if this node divides the plane vertically, false if
         *            not.
         * @param collector
         *            collects the points in the range.
         * @return the collector with the points that fall in range.
         */
        private Iterable<Point2D> range(RectHV rect, boolean vertical,
                List<Point2D> collector) {
            if (rect.contains(point)) {
                collector.add(point);
            }
            if (left != null
                    && isLeft(new Point2D(rect.xmin(), rect.ymin()), vertical)) {
                left.range(rect, !vertical, collector);
            }
            if (right != null
                    && !isLeft(new Point2D(rect.xmax(), rect.ymax()), vertical)) {
                right.range(rect, !vertical, collector);
            }
            return collector;
        }

        /**
         * @param target
         *            for which to find the closest point in the tree.
         * @param vertical
         *            true if this node divides the plane vertically, false if
         *            not.
         * @return the nearest point to the target in the subtree of this node.
         */
        private Point2D nearest(Point2D target, boolean vertical) {
            // A leaf simply returns its point.
            if (left == null && right == null) {
                return point;
            }

            // Find the closest point to the target in the halve of the plane
            // where the target is.
            Point2D best = point;
            if (left != null && isLeft(target, vertical)) {
                best = nearest(target, left.nearest(target, !vertical), point);
            } else if (right != null) {
                best = nearest(target, right.nearest(target, !vertical), point);
            }

            // Find out if there can be a point in the other halve (where the
            // target is not) that can be closer than the best known point. Try
            // to find such a point if so.
            Point2D middle = null;
            if (vertical) {
                middle = new Point2D(point.x(), target.y());
            } else {
                middle = new Point2D(target.x(), point.y());
            }
            if (target.distanceSquaredTo(best) > target
                    .distanceSquaredTo(middle)) {
                if (right != null && isLeft(target, vertical)) {
                    best = nearest(target, best,
                            right.nearest(target, !vertical));
                } else if (left != null) {
                    best = nearest(target, best,
                            left.nearest(target, !vertical));
                }
            }

            return best;
        }

        /**
         * @param target
         *            to be tested against p0 and p1.
         * @param p0
         *            to be tested against the target.
         * @param p1
         *            to be tested against the target.
         * @return p0 if it is closer to the target than p1, p1 if it isnt.
         */
        private Point2D nearest(Point2D target, Point2D p0, Point2D p1) {
            if (target.distanceSquaredTo(p0) < target.distanceSquaredTo(p1)) {
                return p0;
            } else {
                return p1;
            }
        }

        /**
         * @param p
         *            to be tested.
         * @param vertical
         *            true if this node divides the plane vertically, false if
         *            not.
         * @return true if p is to the left (or above if this is a horizontal
         *         node) of this node. False if it is not.
         */
        private boolean isLeft(Point2D p, boolean vertical) {
            return vertical && p.x() < point.x() || !vertical
                    && p.y() < point.y();
        }
    }
}
