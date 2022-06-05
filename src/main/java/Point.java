/*************************************************************************
 * Name:
 * Email:
 *
 * Compilation:  javac Point.java
 * Execution:
 * Dependencies: StdDraw.java
 *
 * Description: An immutable data type for points in the plane.
 *
 *************************************************************************/

import java.util.Comparator;

/**
 * Part 1, assignment 3: Pattern Recognition
 * 
 * Write a program to recognize line patterns in a given set of points.
 * 
 * Computer vision involves analyzing patterns in visual images and
 * reconstructing the real-world objects that produced them. The process in
 * often broken up into two phases: feature detection and pattern recognition.
 * Feature detection involves selecting important features of the image; pattern
 * recognition involves discovering patterns in the features. We will
 * investigate a particularly clean pattern recognition problem involving points
 * and line segments. This kind of pattern recognition arises in many other
 * applications such as statistical data analysis.
 * 
 * The problem. Given a set of N distinct points in the plane, draw every
 * (maximal) line segment that connects a subset of 4 or more of the points.
 * 
 * Point data type. Create an immutable data type Point that represents a point
 * in the plane.
 * 
 * To get started, use the data type Point.java, which implements the
 * constructor and the draw(), drawTo(), and toString() methods. Your job is to
 * add the following components.
 * 
 * The compareTo() method should compare points by their y-coordinates, breaking
 * ties by their x-coordinates. Formally, the invoking point (x0, y0) is less
 * than the argument point (x1, y1) if and only if either y0 < y1 or if y0 = y1
 * and x0 < x1.
 * 
 * The slopeTo() method should return the slope between the invoking point (x0,
 * y0) and the argument point (x1, y1), which is given by the formula (y1 − y0)
 * / (x1 − x0). Treat the slope of a horizontal line segment as positive zero;
 * treat the slope of a vertical line segment as positive infinity; treat the
 * slope of a degenerate line segment (between a point and itself) as negative
 * infinity.
 * 
 * The SLOPE_ORDER comparator should compare points by the slopes they make with
 * the invoking point (x0, y0). Formally, the point (x1, y1) is less than the
 * point (x2, y2) if and only if the slope (y1 − y0) / (x1 − x0) is less than
 * the slope (y2 − y0) / (x2 − x0). Treat horizontal, vertical, and degenerate
 * line segments as in the slopeTo() method.
 * 
 * @author daryl
 * 
 */
public class Point implements Comparable<Point> {

    /**
     * Compare points by slope to nesting point.
     */
    public final Comparator<Point> SLOPE_ORDER = new Comparator<Point>() {
        // YOUR DEFINITION HERE
        public int compare(Point p1, Point p2) {
            if (p1 == null || p2 == null) {
                throw new NullPointerException();
            }
            double s1 = Point.this.slopeTo(p1);
            double s2 = Point.this.slopeTo(p2);
            if (s1 == s2 || Math.abs(s1 - s2) < 0.000001) {
                return 0;
            }
            if (s1 < s2) {
                return -1;
            } else {
                return 1;
            }
        }
    };

    /**
     * x coordinate
     */
    private final int x;
    /**
     * y coordinate
     */
    private final int y;

    /**
     * Create the point (x, y)
     * 
     * @param x
     *            coordinate.
     * @param y
     *            coordinate.
     */
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    /**
     * Plot this point to standard drawing
     */
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    /**
     * Draw line between this point and that point to standard drawing
     * 
     * @param that
     */
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    /**
     * Slope between this point and that point
     * 
     * @param that
     *            to which the slope is to be calculated.
     * @return the slope from point this to point that.
     */
    public double slopeTo(Point that) {
        /* YOUR CODE HERE */
        if (that == null) {
            throw new NullPointerException();
        }
        if (this.x == that.x && this.y == that.y) {
            return Double.NEGATIVE_INFINITY;
        }
        if (this.x == that.x) {
            return Double.POSITIVE_INFINITY;
        }
        if (this.y == that.y) {
            return 0.0;
        }
        return (double) (that.y - this.y) / (that.x - this.x);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    public int compareTo(Point that) {
        /* YOUR CODE HERE */
        if (that == null) {
            throw new NullPointerException();
        }
        if (this.y == that.y) {
            if (this.x == that.x) {
                return 0;
            }
            if (this.x < that.x) {
                return -1;
            } else {
                return 1;
            }
        }
        if (this.y < that.y) {
            return -1;
        } else {
            return 1;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    /**
     * Unused, but required by API.
     */
    public static void main(String[] args) {
        /* YOUR CODE HERE */
    }
}
