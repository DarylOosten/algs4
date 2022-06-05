import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Part 1, assignment 4, 8puzzle.
 * 
 * Write a program to solve the 8-puzzle problem (and its natural
 * generalizations) using the A* search algorithm.
 * 
 * The problem. The 8-puzzle problem is a puzzle invented and popularized by
 * Noyes Palmer Chapman in the 1870s. It is played on a 3-by-3 grid with 8
 * square blocks labeled 1 through 8 and a blank square. Your goal is to
 * rearrange the blocks so that they are in order, using as few moves as
 * possible. You are permitted to slide blocks horizontally or vertically into
 * the blank square.
 * 
 * Best-first search. Now, we describe a solution to the problem that
 * illustrates a general artificial intelligence methodology known as the A*
 * search algorithm. We define a search node of the game to be a board, the
 * number of moves made to reach the board, and the previous search node. First,
 * insert the initial search node (the initial board, 0 moves, and a null
 * previous search node) into a priority queue. Then, delete from the priority
 * queue the search node with the minimum priority, and insert onto the priority
 * queue all neighboring search nodes (those that can be reached in one move
 * from the dequeued search node). Repeat this procedure until the search node
 * dequeued corresponds to a goal board. The success of this approach hinges on
 * the choice of priority function for a search node. We consider two priority
 * functions:
 * 
 * Hamming priority function. The number of blocks in the wrong position, plus
 * the number of moves made so far to get to the search node. Intutively, a
 * search node with a small number of blocks in the wrong position is close to
 * the goal, and we prefer a search node that have been reached using a small
 * number of moves.
 * 
 * Manhattan priority function. The sum of the Manhattan distances (sum of the
 * vertical and horizontal distance) from the blocks to their goal positions,
 * plus the number of moves made so far to get to the search node.
 * 
 * We make a key oberservation: To solve the puzzle from a given search node on
 * the priority queue, the total number of moves we need to make (including
 * those already made) is at least its priority, using either the Hamming or
 * Manhattan priority function. (For Hamming priority, this is true because each
 * block that is out of place must move at least once to reach its goal
 * position. For Manhattan priority, this is true because each block must move
 * its Manhattan distance from its goal position. Note that we do not count the
 * blank square when computing the Hamming or Manhattan priorities.)
 * Consequently, when the goal board is dequeued, we have discovered not only a
 * sequence of moves from the initial board to the goal board, but one that
 * makes the fewest number of moves. (Challenge for the mathematically inclined:
 * prove this fact.)
 * 
 * @author daryl
 * 
 */
public class Board {

    private final int[][] blocks;

    private int hamming = -1;
    private int manhattan = -1;
    private int[] blank;

    /**
     * construct a board from an N-by-N array of blocks (where blocks[i][j] =
     * block in row i, column j)
     * 
     * @param blocks
     */
    public Board(int[][] blocks) {
        this.blocks = deepCopyOf(blocks);
    }

    /**
     * @return board dimension N
     */
    public int dimension() {
        return blocks.length;
    }

    /**
     * @return number of blocks out of place
     */
    public int hamming() {
        if (hamming == -1) {
            calculate();
        }
        return hamming;
    }

    /**
     * @return sum of Manhattan distances between blocks and goal
     */
    public int manhattan() {
        if (manhattan == -1) {
            calculate();
        }
        return manhattan;
    }

    /**
     * @return is this board the goal board?
     */
    public boolean isGoal() {
        return hamming() == 0 && hamming == manhattan;
    }

    /**
     * @return a board obtained by exchanging two adjacent blocks in the same
     *         row
     */
    public Board twin() {
        int ri;
        do {
            ri = StdRandom.uniform(dimension());
        } while (ri == getBlank()[0]);
        int rj = StdRandom.uniform(dimension() - 1);
        return twinBoard(this, ri, rj, ri, rj + 1);
    }

    /**
     * @return all neighboring boards
     */
    public Iterable<Board> neighbors() {
        return findNeighbors();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        return Arrays.deepEquals(blocks, ((Board) obj).blocks);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(dimension() + "\n");
        for (int i = 0; i < dimension(); i++) {
            for (int j = 0; j < dimension(); j++) {
                s.append(String.format("%2d ", blocks[i][j]));
            }
            s.append("\n");
        }
        return s.toString();
    }

    private int[] getBlank() {
        if (blank == null) {
            calculate();
        }
        return blank;
    }

    private void calculate() {
        hamming = 0;
        manhattan = 0;
        for (int i = 0; i < dimension(); i++) {
            for (int j = 0; j < dimension(); j++) {
                hamming += hamming(i, j);
                manhattan += manhattan(i, j);
                updateBlank(i, j);
            }
        }
    }

    private int hamming(int i, int j) {
        if (i == dimension() - 1 && j == dimension() - 1) {
            return 0;
        }
        if (blocks[i][j] != i * dimension() + j + 1) {
            return 1;
        }
        return 0;
    }

    private int manhattan(int i, int j) {
        int v = blocks[i][j];
        if (v == 0) {
            return 0;
        }
        int k = (v - 1) / dimension();
        int l = (v - 1) % dimension();
        return Math.abs(i - k) + Math.abs(j - l);
    }

    private void updateBlank(int i, int j) {
        if (blocks[i][j] == 0) {
            blank = new int[] { i, j };
        }
    }

    private Iterable<Board> findNeighbors() {
        List<Board> result = new LinkedList<Board>();
        int[] b = getBlank();
        if (b[0] > 0) {
            result.add(twinBoard(this, b[0], b[1], b[0] - 1, b[1]));
        }
        if (b[1] > 0) {
            result.add(twinBoard(this, b[0], b[1], b[0], b[1] - 1));
        }
        if (b[0] < dimension() - 1) {
            result.add(twinBoard(this, b[0], b[1], b[0] + 1, b[1]));
        }
        if (b[1] < dimension() - 1) {
            result.add(twinBoard(this, b[0], b[1], b[0], b[1] + 1));
        }
        return result;
    }

    private static Board twinBoard(Board oldBoard, int i, int j, int k, int l) {
        Board newBoard = new Board(
                swap(deepCopyOf(oldBoard.blocks), i, j, k, l));

        if (oldBoard.hamming == -1 || oldBoard.manhattan == -1
                || oldBoard.blank == null) {
            oldBoard.calculate();
        }
        int hNegDiff = oldBoard.hamming(i, j) + oldBoard.hamming(k, l);
        int hPosDiff = newBoard.hamming(i, j) + newBoard.hamming(k, l);
        newBoard.hamming = oldBoard.hamming - hNegDiff + hPosDiff;
        int mNegDiff = oldBoard.manhattan(i, j) + oldBoard.manhattan(k, l);
        int mPosDiff = newBoard.manhattan(i, j) + newBoard.manhattan(k, l);
        newBoard.manhattan = oldBoard.manhattan - mNegDiff + mPosDiff;
        newBoard.updateBlank(i, j);
        newBoard.updateBlank(k, l);

        return newBoard;
    }

    private static int[][] swap(int[][] a, int i, int j, int k, int l) {
        int t = a[i][j];
        a[i][j] = a[k][l];
        a[k][l] = t;
        return a;
    }

    private static int[][] deepCopyOf(int[][] a) {
        int[][] copy = new int[a.length][];
        for (int i = 0; i < a.length; i++) {
            copy[i] = Arrays.copyOf(a[i], a[i].length);
        }
        return copy;
    }
}