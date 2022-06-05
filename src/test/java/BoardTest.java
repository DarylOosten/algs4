import java.util.Iterator;

import org.junit.Assert;
import org.junit.Test;

public class BoardTest {

    private Board board;

    @Test
    public void testHamming() {
        setupWith(
                8, 1, 3, 
                4, 0, 2, 
                7, 6, 5);
        Assert.assertEquals(5, board.hamming());
    }

    @Test
    public void testHammingForCorrectBoard() {
        setupWith(
                1, 2, 3, 
                4, 5, 6, 
                7, 8, 0);
        Assert.assertEquals(0, board.hamming());
    }

    @Test
    public void testManhattan() {
        setupWith(
                8, 1, 3, 
                4, 0, 2, 
                7, 6, 5);
        Assert.assertEquals(10, board.manhattan());
    }

    @Test
    public void testManhattanForCorrectBoard() {
        setupWith(
                1, 2, 3, 
                4, 5, 6, 
                7, 8, 0);
        Assert.assertEquals(0, board.manhattan());
    }

    @Test
    public void testGoal() {
        setupWith(
                1, 2, 3, 
                4, 5, 6, 
                7, 8, 0);
        Assert.assertTrue(board.isGoal());
    }

    @Test
    public void testIsNotGoal() {
        setupWith(
                8, 1, 3, 
                4, 0, 2, 
                7, 6, 5);
        Assert.assertFalse(board.isGoal());
    }

    @Test
    public void testGoalRegression() {
        setupWith(
                1, 2, 
                3, 0);
        Assert.assertTrue(board.isGoal());
    }

    @Test
    public void testTwin() {
        setupWith(
                0, 1, 
                2, 3);
        Board twin = board.twin();
        Assert.assertTrue(twin.equals(newBoard(0, 1, 3, 2)));
    }

    @Test
    public void testNeighbors() {
        setupWith(
                1, 2, 3, 
                4, 0, 5, 
                6, 7, 8);
        Iterator<Board> it = board.neighbors().iterator();

        Assert.assertTrue(it.hasNext());
        Assert.assertEquals(newBoard(
                1, 0, 3, 
                4, 2, 5, 
                6, 7, 8
                ), it.next());
        Assert.assertTrue(it.hasNext());
        Assert.assertEquals(newBoard(
                1, 2, 3, 
                0, 4, 5, 
                6, 7, 8
                ), it.next());
        Assert.assertTrue(it.hasNext());
        Assert.assertEquals(newBoard(
                1, 2, 3, 
                4, 7, 5, 
                6, 0, 8
                ), it.next());
        Assert.assertTrue(it.hasNext());
        Assert.assertEquals(newBoard(
                1, 2, 3, 
                4, 5, 0, 
                6, 7, 8
                ), it.next());
        Assert.assertFalse(it.hasNext());
    }

    @Test
    public void testNeighborsWithTopLeftBlank() {
        setupWith(
                0, 1, 2, 
                3, 4, 5, 
                6, 7, 8);
        Iterator<Board> it = board.neighbors().iterator();

        Assert.assertTrue(it.hasNext());
        Assert.assertEquals(newBoard(
                3, 1, 2, 
                0, 4, 5, 
                6, 7, 8
                ), it.next());
        Assert.assertTrue(it.hasNext());
        Assert.assertEquals(newBoard(
                1, 0, 2, 
                3, 4, 5, 
                6, 7, 8
                ), it.next());
        Assert.assertFalse(it.hasNext());
    }

    @Test
    public void testNeighborsWithBottomRightBlank() {
        setupWith(
                1, 2, 3, 
                4, 5, 6, 
                7, 8, 0);
        Iterator<Board> it = board.neighbors().iterator();

        Assert.assertTrue(it.hasNext());
        Board neighbor = it.next();
        Assert.assertEquals(newBoard(
                1, 2, 3, 
                4, 5, 0, 
                7, 8, 6
                ), neighbor);
        Assert.assertEquals(1, neighbor.hamming());
        Assert.assertEquals(1, neighbor.manhattan());
        
        Assert.assertTrue(it.hasNext());
        neighbor = it.next();
        Assert.assertEquals(newBoard(
                1, 2, 3, 
                4, 5, 6, 
                7, 0, 8
                ), neighbor);
        Assert.assertEquals(1, neighbor.hamming());
        Assert.assertEquals(1, neighbor.manhattan());
        
        Assert.assertFalse(it.hasNext());
    }

    @Test
    public void testNeighborsRegression() {
        setupWith(
                1, 0, 
                2, 3);
        Iterator<Board> it = board.neighbors().iterator();

        Assert.assertTrue(it.hasNext());
        Assert.assertEquals(newBoard(
                0, 1, 
                2, 3
                ), it.next());
        Assert.assertTrue(it.hasNext());
        Assert.assertEquals(newBoard(
                1, 3, 
                2, 0
                ), it.next());
        Assert.assertFalse(it.hasNext());
    }

    private void setupWith(int... elements) {
        board = newBoard(elements);
    }

    private Board newBoard(int... elements) {
        int sqrt = (int) Math.sqrt(elements.length);
        int[][] e = new int[sqrt][sqrt];
        for (int i = 0; i < sqrt; i++) {
            for (int j = 0; j < sqrt; j++) {
                e[i][j] = elements[j + i * sqrt];
            }
        }
        return new Board(e);
    }
}
