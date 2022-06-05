import org.junit.Assert;

import org.junit.Test;

public class PercolationTest {

    @Test
    public void testOpen() {
        Percolation p = new Percolation(1);
        Assert.assertFalse(p.isOpen(1, 1));
        p.open(1, 1);
        Assert.assertTrue(p.isOpen(1, 1));
    }

    @Test
    public void testIsFull() {
        Percolation p = new Percolation(2);
        Assert.assertFalse(p.isFull(1, 1));
        Assert.assertFalse(p.isFull(1, 2));
        Assert.assertFalse(p.isFull(2, 1));
        Assert.assertFalse(p.isFull(2, 2));

        p.open(2, 1);
        Assert.assertFalse(p.isFull(1, 1));
        Assert.assertFalse(p.isFull(1, 2));
        Assert.assertFalse(p.isFull(2, 1));
        Assert.assertFalse(p.isFull(2, 2));

        p.open(1, 2);
        Assert.assertFalse(p.isFull(1, 1));
        Assert.assertTrue(p.isFull(1, 2));
        Assert.assertFalse(p.isFull(2, 1));
        Assert.assertFalse(p.isFull(2, 2));

        p.open(1, 1);
        Assert.assertTrue(p.isFull(1, 1));
        Assert.assertTrue(p.isFull(1, 2));
        Assert.assertTrue(p.isFull(2, 1));
        Assert.assertFalse(p.isFull(2, 2));
    }

    @Test
    public void testPercolates() {
        Percolation p = new Percolation(1);
        Assert.assertFalse(p.percolates());
        p.open(1, 1);
        Assert.assertTrue(p.percolates());

        p = new Percolation(2);
        Assert.assertFalse(p.percolates());
        p.open(1, 1);
        Assert.assertFalse(p.percolates());
        p.open(2, 2);
        Assert.assertFalse(p.percolates());
        p.open(1, 2);
        Assert.assertTrue(p.percolates());
        p.open(2, 1);
        Assert.assertTrue(p.percolates());

        p = new Percolation(3);
        Assert.assertFalse(p.percolates());
        p.open(1, 1);
        Assert.assertFalse(p.percolates());
        p.open(1, 3);
        Assert.assertFalse(p.percolates());
        p.open(2, 2);
        Assert.assertFalse(p.percolates());
        p.open(3, 1);
        Assert.assertFalse(p.percolates());
        p.open(3, 3);
        Assert.assertFalse(p.percolates());
        p.open(1, 2);
        Assert.assertFalse(p.percolates());
        p.open(3, 2);
        Assert.assertTrue(p.percolates());
        p.open(2, 1);
        Assert.assertTrue(p.percolates());
        p.open(2, 3);
        Assert.assertTrue(p.percolates());
    }

    @Test
    public void testBackwash() {
        Percolation p = new Percolation(3);
        p.open(1, 1);
        p.open(2, 1);
        p.open(3, 1);
        p.open(3, 3);
        Assert.assertFalse(p.isFull(3, 3));
    }
}
