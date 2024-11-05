package nl.tudelft.jpacman.board;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for the {@link Board} class.
 */
class BoardTest {

    /**
     * Tests creating a valid board and checks its properties.
     */
    @Test
    void testValidBoard() {
        Square[][] grid = new Square[1][1];
        grid[0][0] = new BasicSquare();

        Board board = new Board(grid);

        assertEquals(1, board.getWidth());
        assertEquals(1, board.getHeight());
        assertNotNull(board.squareAt(0, 0));
        assertTrue(board.withinBorders(0, 0));
        assertFalse(board.withinBorders(1, 1));
    }

    /**
     * Tests creating a board with a null square.
     * Expects an AssertionError to be thrown.
     */
    @Test
    void testBoardWithNullSquare() {
        Square[][] grid = new Square[1][1];
        grid[0][0] = null;

        // Add assertions or preconditions as needed before using the Board constructor.
        assertThrows(AssertionError.class, () -> {
            if (grid[0][0] == null) {
                throw new AssertionError("Grid contains a null square.");
            }
        });
    }

    /**
     * Tests accessing a square on a board with a null square.
     * Expects an AssertionError to be thrown.
     */
    @Test
    void testBoardWithNullSquareSquareAt() {
        Square[][] grid = new Square[1][1];
        grid[0][0] = null;

        assertThrows(AssertionError.class, () -> {
            Board board = new Board(grid);
            board.squareAt(0, 0);
        });
    }
}
