package nl.tudelft.jpacman.board;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class BoardTest {

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
    @Test
    void testBoardWithNullSquare() {
        Square[][] grid = new Square[1][1];
        grid[0][0] = null;

        assertThrows(AssertionError.class, () -> {
            Board board = new Board(grid);
        });
    }
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
