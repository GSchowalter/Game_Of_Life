import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GameOfLifeTest {

	private Colony[][] board;
	int screenWidth = 400;
	int screenHeight = 400;
	int length = 30;
	double colonyWidth = (double) screenWidth / length;

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testGetNumberOfInfluence() {

		board = new Colony[length][length];
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				board[i][j] = new Colony(i, j);
			}
		}
		// Makes an L shape
		board[10][10].setShouldTurnFate();
		board[10][10].setFate();
		board[10][10 + 1].setShouldTurnFate();
		board[10][10 + 1].setFate();
		board[10][10 - 1].setShouldTurnFate();
		board[10][10 - 1].setFate();
		board[10 + 1][10 - 1].setShouldTurnFate();
		board[10 + 1][10 - 1].setFate();

		assertTrue(GameOfLife.board[10][10].getPopulated());
		GameOfLife.update(board);
		assertEquals(GameOfLife.getNumberOfInfluence(10, 11, board), 1);

	}
}