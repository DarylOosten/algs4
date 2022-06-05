import org.junit.Assert;
import org.junit.Test;

public class PercolationStatsTest {

    @Test
    public void testMean() {
        PercolationStats ps = new PercolationStats(1, 1);
        Assert.assertEquals(1.0, ps.mean(), 0.1);

        ps = new PercolationStats(2, 100);
        Assert.assertEquals(0.66, ps.mean(), 0.1);
    }

    @Test
    public void testStddev() {
        PercolationStats ps = new PercolationStats(2, 100);
        Assert.assertEquals(0.1, ps.stddev(), 0.1);
    }

    @Test
    public void testConfidenceLo() {
        PercolationStats ps = new PercolationStats(2, 100);
        Assert.assertEquals(0.6, ps.confidenceLo(), 0.1);
    }

    @Test
    public void testConfidenceHi() {
        PercolationStats ps = new PercolationStats(2, 100);
        Assert.assertEquals(0.6, ps.confidenceLo(), 0.1);
    }
}
