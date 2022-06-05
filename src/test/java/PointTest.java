import org.junit.Assert;
import org.junit.Test;

public class PointTest {

    /**
     * The compareTo() method should compare points by their y-coordinates,
     * breaking ties by their x-coordinates. Formally, the invoking point (x0,
     * y0) is less than the argument point (x1, y1) if and only if either y0 <
     * y1 or if y0 = y1 and x0 < x1.
     */
    @Test
    public void testCompareTo() {
        Assert.assertEquals(0, new Point(0, 0).compareTo(new Point(0, 0)));
        Assert.assertEquals(-1, new Point(0, 0).compareTo(new Point(0, 1)));
        Assert.assertEquals(-1, new Point(0, 0).compareTo(new Point(1, 0)));
        Assert.assertEquals(-1, new Point(0, 0).compareTo(new Point(1, 1)));
        Assert.assertEquals(1, new Point(0, 1).compareTo(new Point(0, 0)));
        Assert.assertEquals(0, new Point(0, 1).compareTo(new Point(0, 1)));
        Assert.assertEquals(1, new Point(0, 1).compareTo(new Point(1, 0)));
        Assert.assertEquals(-1, new Point(0, 1).compareTo(new Point(1, 1)));
        Assert.assertEquals(1, new Point(1, 0).compareTo(new Point(0, 0)));
        Assert.assertEquals(-1, new Point(1, 0).compareTo(new Point(0, 1)));
        Assert.assertEquals(0, new Point(1, 0).compareTo(new Point(1, 0)));
        Assert.assertEquals(-1, new Point(1, 0).compareTo(new Point(1, 1)));
        Assert.assertEquals(1, new Point(1, 1).compareTo(new Point(0, 0)));
        Assert.assertEquals(1, new Point(1, 1).compareTo(new Point(0, 1)));
        Assert.assertEquals(1, new Point(1, 1).compareTo(new Point(1, 0)));
        Assert.assertEquals(0, new Point(1, 1).compareTo(new Point(1, 1)));
    }

    /**
     * The slopeTo() method should return the slope between the invoking point
     * (x0, y0) and the argument point (x1, y1), which is given by the formula
     * (y1 − y0) / (x1 − x0). Treat the slope of a horizontal line segment as
     * positive zero; treat the slope of a vertical line segment as positive
     * infinity; treat the slope of a degenerate line segment (between a point
     * and itself) as negative infinity.
     */
    @Test
    public void testSlopeTo() {
        Assert.assertEquals(Double.NEGATIVE_INFINITY,
                new Point(1, 1).slopeTo(new Point(1, 1)), 0.01);
        Assert.assertEquals(Double.POSITIVE_INFINITY,
                new Point(1, 1).slopeTo(new Point(1, 2)), 0.01);
        Assert.assertEquals(0.0, new Point(1, 1).slopeTo(new Point(2, 1)), 0.01);
        Assert.assertEquals(1.0, new Point(1, 1).slopeTo(new Point(2, 2)), 0.01);
        Assert.assertEquals(Double.POSITIVE_INFINITY,
                new Point(1, 2).slopeTo(new Point(1, 1)), 0.01);
        Assert.assertEquals(Double.NEGATIVE_INFINITY,
                new Point(1, 2).slopeTo(new Point(1, 2)), 0.01);
        Assert.assertEquals(-1.0, new Point(1, 2).slopeTo(new Point(2, 1)),
                0.01);
        Assert.assertEquals(0.0, new Point(1, 2).slopeTo(new Point(2, 2)), 0.01);
        Assert.assertEquals(0.0, new Point(2, 1).slopeTo(new Point(1, 1)), 0.01);
        Assert.assertEquals(-1.0, new Point(2, 1).slopeTo(new Point(1, 2)),
                0.01);
        Assert.assertEquals(Double.NEGATIVE_INFINITY,
                new Point(2, 1).slopeTo(new Point(2, 1)), 0.01);
        Assert.assertEquals(Double.POSITIVE_INFINITY,
                new Point(2, 1).slopeTo(new Point(2, 2)), 0.01);
        Assert.assertEquals(1.0, new Point(2, 2).slopeTo(new Point(1, 1)), 0.01);
        Assert.assertEquals(0.0, new Point(2, 2).slopeTo(new Point(1, 2)), 0.01);
        Assert.assertEquals(Double.POSITIVE_INFINITY,
                new Point(2, 2).slopeTo(new Point(2, 1)), 0.01);
        Assert.assertEquals(Double.NEGATIVE_INFINITY,
                new Point(2, 2).slopeTo(new Point(2, 2)), 0.01);
        Assert.assertEquals(-0.583,
                new Point(23, 31).slopeTo(new Point(35, 24)), 0.01);

        Assert.assertEquals(Double.POSITIVE_INFINITY,
                new Point(337, 438).slopeTo(new Point(337, 177)), 0.01);
    }

    /**
     * The SLOPE_ORDER comparator should compare points by the slopes they make
     * with the invoking point (x0, y0). Formally, the point (x1, y1) is less
     * than the point (x2, y2) if and only if the slope (y1 − y0) / (x1 − x0) is
     * less than the slope (y2 − y0) / (x2 − x0). Treat horizontal, vertical,
     * and degenerate line segments as in the slopeTo() method.
     */
    @Test
    public void testSlopeOrder() {
        Assert.assertEquals(0, new Point(1, 1).SLOPE_ORDER.compare(new Point(0,
                0), new Point(2, 2)));
        Assert.assertEquals(0, new Point(2, 2).SLOPE_ORDER.compare(new Point(1,
                1), new Point(3, 3)));

        Assert.assertEquals(-1, new Point(1, 1).SLOPE_ORDER.compare(new Point(
                0, 0), new Point(2, 3)));
        Assert.assertEquals(-1, new Point(1, 1).SLOPE_ORDER.compare(new Point(
                0, 0), new Point(3, 4)));

        Assert.assertEquals(1, new Point(1, 1).SLOPE_ORDER.compare(new Point(0,
                0), new Point(3, 2)));
        Assert.assertEquals(1, new Point(1, 1).SLOPE_ORDER.compare(new Point(0,
                0), new Point(4, 3)));

        Assert.assertEquals(1, new Point(1234, 5678).SLOPE_ORDER.compare(
                new Point(14000, 10000), new Point(18000, 10000)));
        Assert.assertEquals(-1, new Point(316, 164).SLOPE_ORDER.compare(
                new Point(154, 275), new Point(158, 272)));
        Assert.assertEquals(1, new Point(99, 481).SLOPE_ORDER.compare(
                new Point(228, 338), new Point(403, 144)));
    }
}
