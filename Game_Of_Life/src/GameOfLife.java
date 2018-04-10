
public class GameOfLife {

	public static int screenWidth = 400;
	public static int screenHeight = 400;
	public static int length = 30;
	public static double colonyWidth = (double) screenWidth / length;
	public static boolean ENABLED = true;
	public static Colony[][] board;

	public static void main(String[] args) {
		StdDraw.enableDoubleBuffering();
		StdDraw.setCanvasSize(screenWidth, screenHeight);
		StdDraw.setXscale(0, screenWidth);
		StdDraw.setYscale(0, screenHeight);
		board = new Colony[length][length];
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				board[i][j] = new Colony(i, j);
			}
		}

		while (ENABLED) {
			show();
		}
	}

	public static void show() {
		for (Colony[] list : board) {
			for (Colony colony : list) {
				colony.show(colonyWidth);
			}
		}
		StdDraw.show();
	}

	public static void update() {
		// Sets the fate
		for (Colony[] curRow : board) {
			for (Colony curCol : curRow) {
				determineFate(curCol);
			}
		}

		// Executes fate
		for (Colony[] curRow : board) {
			for (Colony curCol : curRow) {
				curCol.setFate();
			}
		}
	}

	// Implemented only for the colonies that exist not next to the boundaries of
	// the board.
	public static int getNumberOfInfluence(int x, int y, Colony[][] board) {
		int numPopulated = 0;
		if (x > 0 && y > 0 && x < screenWidth && y < screenHeight) {
			// Check left cell
			if (board[x - 1][y].getPopulated()) {
				numPopulated++;
			}

			// Check top left
			if (board[x - 1][y + 1].getPopulated()) {
				numPopulated++;
			}

			// Check top
			if (board[x][y + 1].getPopulated()) {
				numPopulated++;
			}

			// Check top right
			if (board[x + 1][y + 1].getPopulated()) {
				numPopulated++;
			}

			// Check right
			if (board[x + 1][y].getPopulated()) {
				numPopulated++;
			}

			// Check bottom right
			if (board[x + 1][y - 1].getPopulated()) {
				numPopulated++;
			}

			// Check bottom
			if (board[x][y - 1].getPopulated()) {
				numPopulated++;
			}

			// Check bottom left
			if (board[x - 1][y - 1].getPopulated()) {
				numPopulated++;
			}

		}
		return numPopulated;
	}

	/**
	 * Any live cell with fewer than two live neighbors dies, as if caused by under
	 * population. Any live cell with two or three live neighbors lives on to the
	 * next generation. Any live cell with more than three live neighbors dies, as
	 * if by over population. Any dead cell with exactly three live neighbors
	 * becomes a live cell, as if by reproduction.
	 * 
	 * @param colony
	 *            the colony to be checked
	 */
	public static void determineFate(Colony colony) {
		int surroundingPopulation = getNumberOfInfluence(colony.getX(), colony.getY(), board);
		if (colony.getPopulated()) {
			if (surroundingPopulation < 2) {
				colony.setShouldTurnFate();
			} else if (surroundingPopulation > 3) {
				colony.setShouldTurnFate();
			}
		} else {
			if (surroundingPopulation == 3) {
				colony.setShouldTurnFate();
			}
		}
	}

}
