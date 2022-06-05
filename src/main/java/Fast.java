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
 * A faster, sorting-based solution. Remarkably, it is possible to solve the
 * problem much faster than the brute-force solution described above. Given a
 * point p, the following method determines whether p participates in a set of 4
 * or more collinear points.
 * 
 * Think of p as the origin.
 * 
 * For each other point q, determine the slope it makes with p.
 * 
 * Sort the points according to the slopes they makes with p.
 * 
 * Check if any 3 (or more) adjacent points in the sorted order have equal
 * slopes with respect to p. If so, these points, together with p, are
 * collinear.
 * 
 * Applying this method for each of the N points in turn yields an efficient
 * algorithm to the problem. The algorithm solves the problem because points
 * that have equal slopes with respect to p are collinear, and sorting brings
 * such points together. The algorithm is fast because the bottleneck operation
 * is sorting.
 * 
 * Write a program Fast.java that implements this algorithm. The order of growth
 * of the running time of your program should be N2 log N in the worst case and
 * it should use space proportional to N.
 * 
 * @author daryl
 * 
 */
public class Fast {

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

        Point[][] collinears = new Fast().findCollinears(points);
        for (Point[] collinear : collinears) {
            collinear[0].drawTo(collinear[collinear.length - 1]);
            StringBuilder out = new StringBuilder();
            for (Point p : collinear) {
                if (out.length() != 0) {
                    out.append(" -> ");
                }
                out.append(p);
            }
            System.out.println(out);
        }
    }

    /**
     * @param plane
     *            in which collinear points should be found.
     * @return the collinear points in the plane.
     */
    private Point[][] findCollinears(Point[] plane) {
        Point[][] collinears = new Point[0][];

        Arrays.sort(plane);
        for (int p = 0; p < plane.length; p++) {
            Point origin = plane[p];
            Point[] remaining = Arrays.copyOfRange(plane, p + 1, plane.length);
            collinears = append(collinears, findCollinears(origin, remaining));
        }

        return collinears;
    }

    /**
     * @param origin
     *            the point from which collinears should be found.
     * @param other
     *            the points that form a collinear with the origin.
     * @return an array of collinear points to the origin.
     */
    private Point[][] findCollinears(Point origin, Point[] other) {
        Arrays.sort(other, origin.SLOPE_ORDER);
        Point[][] found = new Point[0][];

        Point[] currentCollinear = null;
        for (int p = 0; p < other.length - 1; p++) {
            Point p0 = other[p];
            Point p1 = other[p + 1];

            if (origin.SLOPE_ORDER.compare(p0, p1) == 0) {
                if (currentCollinear == null) {
                    currentCollinear = new Point[3];
                    currentCollinear[0] = origin;
                    currentCollinear[1] = p0;
                    currentCollinear[2] = p1;
                } else {
                    currentCollinear = add(p1, currentCollinear);
                }
            } else if (currentCollinear != null) {
                found = add(currentCollinear, found);
                currentCollinear = null;
            }
        }
        if (currentCollinear != null) {
            found = add(currentCollinear, found);
        }

        return found;
    }

    /**
     * @param p
     * @param a
     * @return a + p
     */
    private static Point[] add(Point p, Point[] a) {
        Point[] result = Arrays.copyOf(a, a.length + 1);
        result[a.length] = p;
        return result;
    }

    /**
     * @param p
     * @param a
     * @return a + p
     */
    private static Point[][] add(Point[] p, Point[][] a) {
        Point[][] result = Arrays.copyOf(a, a.length + 1);
        result[a.length] = p;
        return result;
    }

    /**
     * @param a
     * @param b
     * @return a + b
     */
    private static Point[][] append(Point[][] a, Point[][] b) {
        if (a.length == 0) {
            return b;
        }
        if (b.length == 0) {
            return a;
        }
        Point[][] result = Arrays.copyOf(a, a.length + b.length);
        System.arraycopy(b, 0, result, a.length, b.length);
        return result;
    }
}
