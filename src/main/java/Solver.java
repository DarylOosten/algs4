import java.util.LinkedList;
import java.util.Queue;

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
 * the blank square. A critical optimization. Best-first search has one annoying
 * feature: search nodes corresponding to the same board are enqueued on the
 * priority queue many times. To reduce unnecessary exploration of useless
 * search nodes, when considering the neighbors of a search node, don't enqueue
 * a neighbor if its board is the same as the board of the previous search node.
 * 
 * Game tree. One way to view the computation is as a game tree, where each
 * search node is a node in the game tree and the children of a node correspond
 * to its neighboring search nodes. The root of the game tree is the initial
 * search node; the internal nodes have already been processed; the leaf nodes
 * are maintained in a priority queue; at each step, the A* algorithm removes
 * the node with the smallest priority from the priority queue and processes it
 * (by adding its children to both the game tree and the priority queue).
 * 8puzzle game tree
 * 
 * Detecting infeasible puzzles. Not all initial boards can lead to the goal
 * board. To detect such situations, use the fact that boards are divided into
 * two equivalence classes with respect to reachability: (i) those that lead to
 * the goal board and (ii) those that lead to the goal board if we modify the
 * initial board by swapping any pair of adjacent (non-blank) blocks in the same
 * row. (Difficult challenge for the mathematically inclined: prove this fact.)
 * To apply the fact, run the A* algorithm simultaneously on two puzzle
 * instances—one with the initial board and one with the initial board modified
 * by swapping a pair of adjacent blocks in the same row. Exactly one of the two
 * will lead to the goal board.
 * 
 * @author daryl
 * 
 */
public class Solver {

    private final Queue<Board> solution;

    /**
     * @param initial
     *            find a solution to the initial board (using the A* algorithm)
     */
    public Solver(Board initial) {
        solution = solve(initial);
    }

    /**
     * @return is the initial board solvable?
     */
    public boolean isSolvable() {
        return solution != null;
    }

    /**
     * @return min number of moves to solve initial board; -1 if no solution
     */
    public int moves() {
        if (!isSolvable()) {
            return -1;
        }
        return solution.size() - 1;
    }

    /**
     * @return sequence of boards in a shortest solution; null if no solution
     */
    public Iterable<Board> solution() {
        return solution;
    }

    private Queue<Board> solve(Board initial) {
        MinPQ<BoardNode> initialQueue = new MinPQ<BoardNode>();
        initialQueue.insert(new BoardNode(null, initial));

        Board twin = initial.twin();
        MinPQ<BoardNode> twinQueue = new MinPQ<BoardNode>();
        twinQueue.insert(new BoardNode(null, twin));

        while (true) {
            BoardNode result = move(initialQueue);
            if (result != null) {
                return result.path();
            }
            if (move(twinQueue) != null) {
                return null;
            }
        }
    }

    private BoardNode move(MinPQ<BoardNode> queue) {
        BoardNode minNode = queue.delMin();
        Board board = minNode.getBoard();
        if (board.isGoal()) {
            return minNode;
        } else {
            for (Board neighbor : board.neighbors()) {
                BoardNode neighborNode = new BoardNode(minNode, neighbor);
                if (!minNode.equalToBoardOrAncestors(neighborNode)
                        && minNode.compareTo(neighborNode) <= 0) {
                    queue.insert(neighborNode);
                }
            }
        }
        return null;
    }

    private static class BoardNode implements Comparable<BoardNode> {
        private final BoardNode parent;
        private final Board board;

        protected BoardNode(BoardNode parent, Board board) {
            super();
            this.parent = parent;
            this.board = board;
        }

        protected Board getBoard() {
            return board;
        }

        protected int getMoves() {
            if (parent == null) {
                return 0;
            }
            return parent.getMoves() + 1;
        }

        protected Queue<Board> path() {
            Queue<Board> path;
            if (parent == null) {
                path = new LinkedList<Board>();
            } else {
                path = parent.path();
            }
            path.add(board);
            return path;
        }

        protected boolean equalToBoardOrAncestors(BoardNode that) {
            return this.board.equals(that.board) || parent != null
                    && parent.equalToBoardOrAncestors(that);
        }

        public int compareTo(BoardNode that) {
            return Integer.valueOf(getBoard().manhattan() + getMoves())
                    .compareTo(that.getBoard().manhattan() + that.getMoves());
        }
    }

    /**
     * solve a slider puzzle (given below)
     * 
     * @param args
     */
    public static void main(String[] args) {
        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] blocks = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }
}
