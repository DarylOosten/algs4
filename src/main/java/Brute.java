import java.util.Arrays;

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
 * Brute force. Write a program Brute.java that examines 4 points at a time and
 * checks whether they all lie on the same line segment, printing out any such
 * line segments to standard output and drawing them using standard drawing. To
 * check whether the 4 points p, q, r, and s are collinear, check whether the
 * slopes between p and q, between p and r, and between p and s are all equal.
 * 
 * The order of growth of the running time of your program should be N4 in the
 * worst case and it should use space proportional to N.
 * 
 * @author daryl
 * 
 */
public class Brute {

    /**
     * Input format. Read the points from an input file (args[0]) in the
     * following format: An integer N, followed by N pairs of integers (x, y).
     * 
     * @param args
     *            [0] filename that contains the points in a plane.
     */
    public static void main(String[] args) {
        int[] in = new In(args[0]).readAllInts();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);

        Point[] points = new Point[in[0]];
        for (int p = 0, i = 1; p < points.length; p++, i = i + 2) {
            points[p] = new Point(in[i], in[i + 1]);
            points[p].draw();
        }
        Arrays.sort(points);

        Point[][] collinears = new Brute().bruteForceCollinears(points);
        for (Point[] collinear : collinears) {
            collinear[0].drawTo(collinear[3]);
            System.out.println(collinear[0] + " -> " + collinear[1] + " -> "
                    + collinear[2] + " -> " + collinear[3]);
        }
    }

    /**
     * @return a set of 4 tuple collinear points.
     */
    private Point[][] bruteForceCollinears(Point[] points) {
        Point[][] colinears = new Point[0][];

        for (int i = 0; i < points.length; i++) {
            Point p0 = points[i];
            for (int j = 0; j < i; j++) {
                Point p1 = points[j];
                for (int k = 0; k < j; k++) {
                    Point p2 = points[k];
                    for (int l = 0; l < k; l++) {
                        Point p3 = points[l];
                        Point[] candidates = new Point[] { p0, p1, p2, p3 };
                        if (areCollinear(candidates)) {
                            colinears = add(candidates, colinears);
                        }
                    }
                }
            }
        }

        return colinears;
    }

    /**
     * @param candidates
     *            to be tested for collinearity.
     * @return true if, and only if, all candidates are collinear.
     */
    private static boolean areCollinear(Point[] candidates) {
        for (int i = 0; i < candidates.length - 2; i++) {
            if (candidates[i].SLOPE_ORDER.compare(candidates[i + 1],
                    candidates[i + 2]) != 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * @param p
     *            to be appended to a.
     * @param a
     *            to which p will be appended.
     * @return A copy of array a with point p appended.
     */
    private static Point[][] add(Point[] p, Point[][] a) {
        Point[][] result = Arrays.copyOf(a, a.length + 1);
        result[a.length] = p;
        return result;
    }
}
