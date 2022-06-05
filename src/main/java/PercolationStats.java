/**
 * Part 1, assignment 1: Percolation
 * 
 * Estimate the value of the percolation threshold via Monte Carlo simulation.
 * 
 * Percolation. Given a composite systems comprised of randomly distributed
 * insulating and metallic materials: what fraction of the materials need to be
 * metallic so that the composite system is an electrical conductor? Given a
 * porous landscape with water on the surface (or oil below), under what
 * conditions will the water be able to drain through to the bottom (or the oil
 * to gush through to the surface)? Scientists have defined an abstract process
 * known as percolation to model such situations.
 * 
 * @see http://coursera.cs.princeton.edu/algs4/assignments/percolation.html
 * 
 * @author daryl
 */
public class PercolationStats {

    /**
     * Confidence constant.
     */
    private static final double CONFIDENCE = 1.96;

    /**
     * Number of open sites per experiment.
     */
    private final double[] fractionsOfOpenSites;

    /**
     * Perform T independent computational experiments on an N-by-N grid.
     * 
     * @param n
     *            length of the grids.
     * @param t
     *            number of experiments to run.
     */
    public PercolationStats(int n, int t) {
        if (n < 1 || t < 1) {
            throw new IllegalArgumentException();
        }
        fractionsOfOpenSites = new double[t];
        experiment(n, t);
    }

    /**
     * @param args
     *            first argument has the length of the grids, the second the
     *            number of experiments to run.
     */
    public static void main(String[] args) {
        PercolationStats ps = new PercolationStats(Integer.parseInt(args[0]),
                Integer.parseInt(args[1]));
        System.out.println("mean = " + ps.mean());
        System.out.println("stddev = " + ps.stddev());
        System.out.println("95% confidence interval = " + ps.confidenceLo()
                + ", " + ps.confidenceHi());
    }

    /**
     * @return the sample mean of percolation threshold.
     */
    public double mean() {
        return StdStats.mean(fractionsOfOpenSites);
    }

    /**
     * @return the sample standard deviation of percolation threshold.
     */
    public double stddev() {
        return StdStats.stddev(fractionsOfOpenSites);
    }

    /**
     * @return the lower bound of the 95% confidence interval.
     */
    public double confidenceLo() {
        return mean() - confidence();
    }

    /**
     * @return the upper bound of the 95% confidence interval.
     */
    public double confidenceHi() {
        return mean() + confidence();
    }

    private double confidence() {
        return ((CONFIDENCE * stddev()) / Math
                .sqrt(fractionsOfOpenSites.length));
    }

    /**
     * Runs t experiments on n by n sized grids.
     * 
     * @param n
     *            length of the grids.
     * @param t
     *            number of experiments to run.
     */
    private void experiment(int n, int t) {
        for (int i = 0; i < t; i++) {
            fractionsOfOpenSites[i] = (double) experiment(n) / (double) (n * n);
        }
    }

    /**
     * Runs one experiment on an n by n grid and returns the number of open
     * sites when the grid percolated.
     * 
     * @param n
     *            length of the grid.
     * @return the number of open sites when the grid percolated
     */
    private int experiment(int n) {
        Percolation p = new Percolation(n);
        int tries = 0;
        for (; !p.percolates(); tries++) {
            int i, j;
            do {
                i = StdRandom.uniform(n) + 1;
                j = StdRandom.uniform(n) + 1;
            } while (p.isOpen(i, j));
            p.open(i, j);
        }
        return tries;
    }
}
