/**
 * Part 1, assignment 1: Percolation
 * 
 * Percolation. Given a composite systems comprised of randomly distributed
 * insulating and metallic materials: what fraction of the materials need to be
 * metallic so that the composite system is an electrical conductor? Given a
 * porous landscape with water on the surface (or oil below), under what
 * conditions will the water be able to drain through to the bottom (or the oil
 * to gush through to the surface)? Scientists have defined an abstract process
 * known as percolation to model such situations.
 * 
 * The model. We model a percolation system using an N-by-N grid of sites. Each
 * site is either open or blocked. A full site is an open site that can be
 * connected to an open site in the top row via a chain of neighboring (left,
 * right, up, down) open sites. We say the system percolates if there is a full
 * site in the bottom row. In other words, a system percolates if we fill all
 * open sites connected to the top row and that process fills some open site on
 * the bottom row. (For the insulating/metallic materials example, the open
 * sites correspond to metallic materials, so that a system that percolates has
 * a metallic path from top to bottom, with full sites conducting. For the
 * porous substance example, the open sites correspond to empty space through
 * which water might flow, so that a system that percolates lets water fill open
 * sites, flowing from top to bottom.)
 * 
 * @see http://coursera.cs.princeton.edu/algs4/assignments/percolation.html
 * 
 * @author daryl
 */
public class Percolation {

    /**
     * Length of the grid.
     */
    private final int n;
    /**
     * Quick union find structure of the grid plus two extra nodes for the top
     * and the bottom.
     */
    private final WeightedQuickUnionUF topToBottomQUUF;
    /**
     * Quick union find structure of the grid plus one extra nodes for the top.
     * This may seem a bit redundant, however the top to bottom QUUF allows us
     * to quickly test if the grid perculates. This QUUF allows us to quickly
     * test if any single site is full, without accidently connecting an open
     * bottom site to the top through the bottom.
     */
    private final WeightedQuickUnionUF topQUUF;
    /**
     * Holds information about which site is open/closed.
     */
    private final boolean[] openSites;
    /**
     * Index of the top pseudo site.
     */
    private final int top;
    /**
     * Index of the bottom pseudo site.
     */
    private final int bottom;

    /**
     * Create N-by-N grid, with all sites blocked.
     * 
     * @param n
     *            length of the sides of the grid.
     */
    public Percolation(int n) {
        if (n < 1) {
            throw new IllegalArgumentException();
        }

        this.n = n;
        int nSquared = n * n;
        top = nSquared;
        bottom = nSquared + 1;

        topToBottomQUUF = new WeightedQuickUnionUF(nSquared + 2);
        topQUUF = new WeightedQuickUnionUF(nSquared + 1);
        openSites = new boolean[nSquared + 2];
    }

    /**
     * Open site (row i, column j) if it is not already.
     * 
     * @param i
     *            row of the site.
     * @param j
     *            column of the site.
     */
    public void open(int i, int j) {
        checkValidCoordinate(i, j);
        int site = indexOf(i, j);
        if (openSites[site]) {
            return;
        }
        openSites[site] = true;

        if (i == 1) {
            openSites[top] = true;
            topToBottomQUUF.union(top, site);
            topQUUF.union(top, site);
        }
        if (i == n) {
            openSites[bottom] = true;
            topToBottomQUUF.union(bottom, site);
        }

        if (i - 1 > 0) {
            int up = site - n;
            if (openSites[up]) {
                topToBottomQUUF.union(site, up);
                topQUUF.union(site, up);
            }
        }
        if (j + 1 <= n) {
            int right = site + 1;
            if (openSites[right]) {
                topToBottomQUUF.union(site, right);
                topQUUF.union(site, right);
            }
        }
        if (i + 1 <= n) {
            int down = site + n;
            if (openSites[down]) {
                topToBottomQUUF.union(site, down);
                topQUUF.union(site, down);
            }
        }
        if (j - 1 > 0) {
            int left = site - 1;
            if (openSites[left]) {
                topToBottomQUUF.union(site, left);
                topQUUF.union(site, left);
            }
        }
    }

    /**
     * is site (row i, column j) open?
     * 
     * @param i
     *            row of the site.
     * @param j
     *            column of the site.
     * @return true if it is open.
     */
    public boolean isOpen(int i, int j) {
        checkValidCoordinate(i, j);
        return openSites[indexOf(i, j)];
    }

    /**
     * Is site (row i, column j) full?
     * 
     * @param i
     *            row of the site.
     * @param j
     *            column of the site.
     * @return true if the site is full.
     */
    public boolean isFull(int i, int j) {
        checkValidCoordinate(i, j);
        if (!openSites[top]) {
            return false;
        }
        int site = indexOf(i, j);
        return openSites[site] && topQUUF.connected(top, site);
    }

    /**
     * does the system percolate?
     * 
     * @return true if it percolates.
     */
    public boolean percolates() {
        if (openSites[top] && openSites[bottom]) {
            return topToBottomQUUF.connected(top, bottom);
        }
        return false;
    }

    /**
     * Converts the coordinates of a site into its index.
     * 
     * @param i
     *            row of the site.
     * @param j
     *            column of the site.
     * @return the index for the site.
     */
    private int indexOf(int i, int j) {
        return n * (i - 1) + (j - 1);
    }

    /**
     * Checks if (i,j) is a valid coordinate. Throw index out of bounds if not.
     * 
     * @param i
     *            row of the site.
     * @param j
     *            column of the site.
     */
    private void checkValidCoordinate(int i, int j) {
        if (i < 1 || j < 1 || i > n || j > n) {
            throw new IndexOutOfBoundsException();
        }
    }
}
