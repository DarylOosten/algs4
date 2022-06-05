import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Be sure to add -Xmx4096m before running these tests.
 * 
 * @author daryl
 * 
 */
public class SolverTest {

    private Solver solver;

    @Test
    public void testPuzzle00() {
        setupWith("puzzle00.txt");
        assertSolvable();
        assertMoves(0);
    }

    @Test
    public void testPuzzle01() {
        setupWith("puzzle01.txt");
        assertSolvable();
        assertMoves(1);
    }

    @Test
    public void testPuzzle02() {
        setupWith("puzzle02.txt");
        assertSolvable();
        assertMoves(2);
    }

    @Test
    public void testPuzzle03() {
        setupWith("puzzle03.txt");
        assertSolvable();
        assertMoves(3);
    }

    @Test
    public void testPuzzle04() {
        setupWith("puzzle04.txt");
        assertSolvable();
        assertMoves(4);
    }

    @Test
    public void testPuzzle05() {
        setupWith("puzzle05.txt");
        assertSolvable();
        assertMoves(5);
    }

    @Test
    public void testPuzzle06() {
        setupWith("puzzle06.txt");
        assertSolvable();
        assertMoves(6);
    }

    @Test
    public void testPuzzle07() {
        setupWith("puzzle07.txt");
        assertSolvable();
        assertMoves(7);
    }

    @Test
    public void testPuzzle08() {
        setupWith("puzzle08.txt");
        assertSolvable();
        assertMoves(8);
    }

    @Test
    public void testPuzzle09() {
        setupWith("puzzle09.txt");
        assertSolvable();
        assertMoves(9);
    }

    @Test
    public void testPuzzle10() {
        setupWith("puzzle10.txt");
        assertSolvable();
        assertMoves(10);
    }

    @Test
    public void testPuzzle11() {
        setupWith("puzzle11.txt");
        assertSolvable();
        assertMoves(11);
    }

    @Test
    public void testPuzzle12() {
        setupWith("puzzle12.txt");
        assertSolvable();
        assertMoves(12);
    }

    @Test
    public void testPuzzle13() {
        setupWith("puzzle13.txt");
        assertSolvable();
        assertMoves(13);
    }

    @Test
    public void testPuzzle14() {
        setupWith("puzzle14.txt");
        assertSolvable();
        assertMoves(14);
    }

    @Test
    public void testPuzzle15() {
        setupWith("puzzle15.txt");
        assertSolvable();
        assertMoves(15);
    }

    @Test
    public void testPuzzle16() {
        setupWith("puzzle16.txt");
        assertSolvable();
        assertMoves(16);
    }

    @Test
    public void testPuzzle17() {
        setupWith("puzzle17.txt");
        assertSolvable();
        assertMoves(17);
    }

    @Test
    public void testPuzzle18() {
        setupWith("puzzle18.txt");
        assertSolvable();
        assertMoves(18);
    }

    @Test
    public void testPuzzle19() {
        setupWith("puzzle19.txt");
        assertSolvable();
        assertMoves(19);
    }

    @Test
    public void testPuzzle20() {
        setupWith("puzzle20.txt");
        assertSolvable();
        assertMoves(20);
    }

    @Test
    public void testPuzzle21() {
        setupWith("puzzle21.txt");
        assertSolvable();
        assertMoves(21);
    }

    @Test
    public void testPuzzle22() {
        setupWith("puzzle22.txt");
        assertSolvable();
        assertMoves(22);
    }

    @Test
    public void testPuzzle23() {
        setupWith("puzzle23.txt");
        assertSolvable();
        assertMoves(23);
    }

    @Test
    public void testPuzzle24() {
        setupWith("puzzle24.txt");
        assertSolvable();
        assertMoves(24);
    }

    @Test
    public void testPuzzle25() {
        setupWith("puzzle25.txt");
        assertSolvable();
        assertMoves(25);
    }

    @Test
    public void testPuzzle26() {
        setupWith("puzzle26.txt");
        assertSolvable();
        assertMoves(26);
    }

    @Test
    public void testPuzzle27() {
        setupWith("puzzle27.txt");
        assertSolvable();
        assertMoves(27);
    }

    @Test
    public void testPuzzle28() {
        setupWith("puzzle28.txt");
        assertSolvable();
        assertMoves(28);
    }

    @Test
    public void testPuzzle29() {
        setupWith("puzzle29.txt");
        assertSolvable();
        assertMoves(29);
    }

    @Test
    public void testPuzzle30() {
        setupWith("puzzle30.txt");
        assertSolvable();
        assertMoves(30);
    }

    @Test
    public void testPuzzle31() {
        setupWith("puzzle31.txt");
        assertSolvable();
        assertMoves(31);
    }

    @Test
    public void testPuzzle32() {
        setupWith("puzzle32.txt");
        assertSolvable();
        assertMoves(32);
    }

    @Test
    public void testPuzzle33() {
        setupWith("puzzle33.txt");
        assertSolvable();
        assertMoves(33);
    }

    @Test
    public void testPuzzle34() {
        setupWith("puzzle34.txt");
        assertSolvable();
        assertMoves(34);
    }

    @Test
    public void testPuzzle35() {
        setupWith("puzzle35.txt");
        assertSolvable();
        assertMoves(35);
    }

    @Test
    public void testPuzzle36() {
        setupWith("puzzle36.txt");
        assertSolvable();
        assertMoves(36);
    }

    @Test
    public void testPuzzle37() {
        setupWith("puzzle37.txt");
        assertSolvable();
        assertMoves(37);
    }

    @Test
    public void testPuzzle38() {
        setupWith("puzzle38.txt");
        assertSolvable();
        assertMoves(38);
    }

    @Test
    public void testPuzzle39() {
        setupWith("puzzle39.txt");
        assertSolvable();
        assertMoves(39);
    }

    @Test
    public void testPuzzle40() {
        setupWith("puzzle40.txt");
        assertSolvable();
        assertMoves(40);
    }

    @Test
    public void testPuzzle41() {
        setupWith("puzzle41.txt");
        assertSolvable();
        assertMoves(41);
    }

    @Test
    public void testPuzzle42() {
        setupWith("puzzle42.txt");
        assertSolvable();
        assertMoves(42);
    }

    @Test
    public void testPuzzle43() {
        setupWith("puzzle43.txt");
        assertSolvable();
        assertMoves(43);
    }

    @Test
    public void testPuzzle44() {
        setupWith("puzzle44.txt");
        assertSolvable();
        assertMoves(44);
    }

    @Test
    public void testPuzzle45() {
        setupWith("puzzle45.txt");
        assertSolvable();
        assertMoves(45);
    }

    @Test
    public void testPuzzle46() {
        setupWith("puzzle46.txt");
        assertSolvable();
        assertMoves(46);
    }

    @Ignore
    @Test
    public void testPuzzle47() {
        setupWith("puzzle47.txt");
        assertSolvable();
        assertMoves(47);
    }

    @Test
    public void testPuzzle48() {
        setupWith("puzzle48.txt");
        assertSolvable();
        assertMoves(48);
    }

    @Ignore
    @Test
    public void testPuzzle49() {
        setupWith("puzzle49.txt");
        assertSolvable();
        assertMoves(49);
    }

    @Ignore
    @Test
    public void testPuzzle50() {
        setupWith("puzzle50.txt");
        assertSolvable();
        assertMoves(50);
    }

    @Test
    public void testPuzzle2x2Unsolvable1() {
        setupWith("puzzle2x2-unsolvable1.txt");
        assertUnsolvable();
    }

    @Test
    public void testPuzzle2x2Unsolvable2() {
        setupWith("puzzle2x2-unsolvable2.txt");
        assertUnsolvable();
    }

    @Test
    public void testPuzzle2x2Unsolvable3() {
        setupWith("puzzle2x2-unsolvable3.txt");
        assertUnsolvable();
    }

    @Test
    public void testPuzzle3x3Unsolvable1() {
        setupWith("puzzle3x3-unsolvable1.txt");
        assertUnsolvable();
    }

    @Test
    public void testPuzzle3x3Unsolvable2() {
        setupWith("puzzle3x3-unsolvable2.txt");
        assertUnsolvable();
    }

    @Test
    public void testPuzzle3x3Unsolvable3() {
        setupWith("puzzle3x3-unsolvable.txt");
        assertUnsolvable();
    }

    @Ignore
    @Test
    public void testPuzzle4x478() {
        setupWith("puzzle4x4-78.txt");
        assertSolvable();
        assertMoves(78);
    }

    @Ignore
    @Test
    public void testPuzzle4x480() {
        setupWith("puzzle4x4-78.txt");
        assertSolvable();
        assertMoves(80);
    }

    @Test
    public void testPuzzle4x4Hard1() {
        setupWith("puzzle4x4-hard1.txt");
        assertSolvable();
        assertMoves(38);
    }

    @Ignore
    @Test
    public void testPuzzle4x4Hard2() {
        setupWith("puzzle4x4-hard2.txt");
        assertSolvable();
        assertMoves(1);
    }

    @Test
    public void testPuzzle4x4Unsolvable() {
        setupWith("puzzle4x4-unsolvable.txt");
        assertUnsolvable();
    }

    private void assertSolvable() {
        Assert.assertTrue(solver.isSolvable());
    }

    private void assertUnsolvable() {
        Assert.assertFalse(solver.isSolvable());
    }

    private void assertMoves(int expected) {
        Assert.assertEquals(expected, solver.moves());
    }

    private void setupWith(String filename) {
        In in = new In("src/test/resources/8puzzle/" + filename);
        int n = in.readInt();
        int[][] blocks = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                blocks[i][j] = in.readInt();
        solver = new Solver(new Board(blocks));
    }
}
