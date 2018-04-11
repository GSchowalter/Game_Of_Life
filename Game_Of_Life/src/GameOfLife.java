/**
 * Conway's Mathematical Game of Life.
 * 
 * @author Grant Schowalter and Josh Peck
 *
 */
public class GameOfLife {

	public static final int DRAW_DELAY = 100;

	public static int buttonWidth = 100;
	public static int screenWidth = 400;
	public static int buttonHeight = screenWidth / 4;
	public static int length = 30;
	public static double colonyWidth = (double) screenWidth / length;
	public static boolean ENABLED = false;
	public static Colony[][] board;

	public static void main(String[] args) {
		StdDraw.enableDoubleBuffering();
		StdDraw.setCanvasSize(screenWidth + buttonWidth, screenWidth);
		StdDraw.setXscale(0, screenWidth + buttonWidth);
		StdDraw.setYscale(0, screenWidth);
		board = new Colony[length][length];
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				board[i][j] = new Colony(i, j);
			}
		}

		while (true) {
			while (ENABLED) {
				show();
				update(board);
				eventHandler();
			}

			while (!ENABLED) {
				show();
				eventHandler();
			}
		}
	}

	@SuppressWarnings("deprecation")
	public static void show() {
		for (Colony[] list : board) {
			for (Colony colony : list) {
				colony.show(colonyWidth);
			}
		}
		showButtons();
		StdDraw.show(DRAW_DELAY);
	}

	public static void update(Colony[][] board) {
		// Sets the fate
		for (Colony[] curRow : board) {
			for (Colony curCol : curRow) {
				determineFate(curCol);
			}
		}

		// Executes fate
		for (Colony[] curRow : board) {
			for (Colony curCol : curRow) {
				if (curCol.getShouldTurnFate()) {
					curCol.setFate();
				}
			}
		}
	}

	// Implemented only for the colonies that exist not next to the boundaries of
	// the board.
	public static int getNumberOfInfluence(int x, int y, Colony[][] board) {
		int numPopulated = 0;
		// Check left cell
		if ((x - 1 >= 0 && y >= 0 && x - 1 < length && y < length) && board[x - 1][y].getPopulated()) {
			numPopulated++;
		}

		// Check top left
		if ((x - 1 > 0 && y + 1 > 0 && x - 1 < length && y + 1 < length) && board[x - 1][y + 1].getPopulated()) {
			numPopulated++;
		}

		// Check top
		if ((x > 0 && y + 1 > 0 && x < length && y + 1 < length) && board[x][y + 1].getPopulated()) {
			numPopulated++;
		}

		// Check top right
		if ((x + 1 > 0 && y + 1 > 0 && x + 1 < length && y + 1 < length) && board[x + 1][y + 1].getPopulated()) {
			numPopulated++;
		}

		// Check right
		if ((x + 1 > 0 && y > 0 && x + 1 < length && y < length) && board[x + 1][y].getPopulated()) {
			numPopulated++;
		}

		// Check bottom right
		if ((x + 1 > 0 && y - 1 > 0 && x + 1 < length && y - 1 < length) && board[x + 1][y - 1].getPopulated()) {
			numPopulated++;
		}

		// Check bottom
		if ((x > 0 && y - 1 > 0 && x < length && y - 1 < length) && board[x][y - 1].getPopulated()) {
			numPopulated++;
		}

		// Check bottom left
		if ((x - 1 > 0 && y - 1 > 0 && x - 1 < length && y - 1 < length) && board[x - 1][y - 1].getPopulated()) {
			numPopulated++;
		}

		return numPopulated;
	}

	/**
	 * <<<<<<< HEAD Any live cell with fewer than two live neighbors dies, as if
	 * caused by under population. Any live cell with two or three live neighbors
	 * lives on to the next generation. Any live cell with more than three live
	 * neighbors dies, as if by over population. Any dead cell with exactly three
	 * live neighbors ======= Any live cell with fewer than two live neighbors dies,
	 * as if caused by under population. Any live cell with two or three live
	 * neighbors lives on to the next generation. Any live cell with more than three
	 * live neighbors dies, as if by over population. Any dead cell with exactly
	 * three live neighbors >>>>>>> branch 'master' of
	 * https://www.github.com/GSchowalter/Game_Of_Life becomes a live cell, as if by
	 * reproduction.
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

	public static void drawGlider(int x, int y) {
		board[x + 1][y].setShouldTurnFate();
		board[x + 1][y].setFate();
		board[x + 1][y - 1].setShouldTurnFate();
		board[x + 1][y - 1].setFate();
		board[x][y - 1].setShouldTurnFate();
		board[x][y - 1].setFate();
		board[x - 1][y - 1].setShouldTurnFate();
		board[x - 1][y - 1].setFate();
		board[x][y + 1].setShouldTurnFate();
		board[x][y + 1].setFate();
	}

	public static void addOnMouseClick() {
		if (StdDraw.mouseX() < screenWidth && StdDraw.mouseY() < screenWidth) {
			int x = (int) (StdDraw.mouseX() / colonyWidth);
			int y = (int) (StdDraw.mouseY() / colonyWidth);
			board[x][y].setShouldTurnFate();
			board[x][y].setFate();
		}

	}

	public static void eventHandler() {
		if (StdDraw.isMousePressed()) {
			addOnMouseClick();
			buttonCheck();
		}
	}

	public static void showButtons() {
		StdDraw.setFont();
		
		// Start button
		StdDraw.setPenColor(StdDraw.BOOK_BLUE);
		StdDraw.filledRectangle(colonyWidth * length + buttonWidth / 2, screenWidth - buttonHeight / 2, buttonWidth / 2,
				buttonHeight / 2);
		StdDraw.text(colonyWidth * length + buttonWidth / 2, screenWidth - buttonHeight / 2, "Start");

		// Stop button
		StdDraw.setPenColor(StdDraw.MAGENTA);
		StdDraw.filledRectangle(colonyWidth * length + buttonWidth / 2, screenWidth - (buttonHeight + buttonHeight / 2),
				buttonWidth / 2, buttonHeight / 2);
		
		// Step button
		StdDraw.setPenColor(StdDraw.ORANGE);
		StdDraw.filledRectangle(colonyWidth * length + buttonWidth / 2,
				screenWidth - (2 * buttonHeight + buttonHeight / 2), buttonWidth / 2, buttonHeight / 2);

		// Reset button
		StdDraw.setPenColor(StdDraw.GREEN);
		StdDraw.filledRectangle(colonyWidth * length + buttonWidth / 2,
				screenWidth - (3 * buttonHeight + buttonHeight / 2), buttonWidth / 2, buttonHeight / 2);
	}

	public static void buttonCheck() {
		// Checks start button
		if (StdDraw.mouseX() > screenWidth && StdDraw.mouseX() < screenWidth + buttonWidth
				&& StdDraw.mouseY() < screenWidth && StdDraw.mouseY() > screenWidth - buttonHeight) {
			startButton();
		}

		// Checks stop button
		if (StdDraw.mouseX() > screenWidth && StdDraw.mouseX() < screenWidth + buttonWidth
				&& StdDraw.mouseY() > screenWidth - (2 * buttonHeight)
				&& StdDraw.mouseY() < screenWidth - buttonHeight) {
			stopButton();
		}

		// Checks step button
		if (StdDraw.mouseX() > screenWidth && StdDraw.mouseX() < screenWidth + buttonWidth
				&& StdDraw.mouseY() < screenWidth - (2 * buttonHeight)
				&& StdDraw.mouseY() > screenWidth - (3 * buttonHeight)) {
			stepButton();
		}

		// Checks clear button
		if (StdDraw.mouseX() > screenWidth && StdDraw.mouseX() < screenWidth + buttonWidth
				&& StdDraw.mouseY() < screenWidth - (3 * buttonHeight)
				&& StdDraw.mouseY() > screenWidth - (4 * buttonHeight)) {
			clearButton();
		}
	}

	private static void clearButton() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				board[i][j] = new Colony(i, j);
			}
		}
	}

	public static void startButton() {
		ENABLED = true;
	}

	public static void stopButton() {
		ENABLED = false;
	}

	public static void stepButton() {
		update(board);
	}

}
