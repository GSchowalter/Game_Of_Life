/**
 * Conway's Mathematical Game of Life.
 * 
 * @author Grant Schowalter and Josh Peck
 *
 */
public class GameOfLife {

	public static final int DRAW_DELAY = 100;

	public static int buttonWidth = 100;
	public static int screenHeight = 1800;
	public static int screenWidth = screenHeight + buttonWidth;
	public static int buttonHeight = screenHeight / 5;
	public static int length = 100;
	public static double colonyWidth = (double) (screenHeight) / length;
	public static boolean ENABLED = false;
	public static Colony[][] board;
	public static int timeCount = 1;

	public static void main(String[] args) {
		StdDraw.enableDoubleBuffering();
		StdDraw.setCanvasSize(screenWidth, screenHeight);
		StdDraw.setXscale(0, screenWidth);
		StdDraw.setYscale(0, screenHeight);
		board = new Colony[length][length];
		setBoard(false);

		while (true) {
			while (ENABLED) {
				if (timeCount == DRAW_DELAY) {
					show();
					update(board);
					timeCount = 1;
				}
				eventHandler();
				timeCount++;
			}

			while (!ENABLED) {
				if (timeCount == DRAW_DELAY)
				{
				show();
				timeCount = 1;
				}
				eventHandler();
				timeCount++;
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
		StdDraw.show(1);
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
			if (x < length && y < length) {
				board[x][y].setShouldTurnFate();
				board[x][y].setFate();
			}
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
		StdDraw.filledRectangle(screenWidth - buttonWidth / 2, screenHeight - buttonHeight / 2, buttonWidth / 2,
				buttonHeight / 2);
		StdDraw.text(colonyWidth * length + buttonWidth / 2, screenWidth - buttonHeight / 2, "Start");

		// Stop button
		StdDraw.setPenColor(StdDraw.MAGENTA);
		StdDraw.filledRectangle(screenWidth - buttonWidth / 2, screenHeight - (buttonHeight + buttonHeight / 2),
				buttonWidth / 2, buttonHeight / 2);
		
		// Step button
		StdDraw.setPenColor(StdDraw.ORANGE); 

		StdDraw.filledRectangle(screenWidth - buttonWidth / 2, screenHeight - (2 * buttonHeight + buttonHeight / 2),
				buttonWidth / 2, buttonHeight / 2);

		// Reset button
		StdDraw.setPenColor(StdDraw.GREEN);
		StdDraw.filledRectangle(screenWidth - buttonWidth / 2, screenHeight - (3 * buttonHeight + buttonHeight / 2),
				buttonWidth / 2, buttonHeight / 2);
		
		// Reset random button
				StdDraw.setPenColor(StdDraw.BLACK);
				StdDraw.filledRectangle(screenWidth - buttonWidth / 2, screenHeight - (4 * buttonHeight + buttonHeight / 2),
						buttonWidth / 2, buttonHeight / 2);
	}

	public static void buttonCheck() {
		// Checks start button
		if (StdDraw.mouseX() > screenWidth - buttonWidth && StdDraw.mouseY() > screenHeight - buttonHeight) {
			startButton();
		}

		// Checks stop button
		if (StdDraw.mouseX() > screenWidth - buttonWidth && StdDraw.mouseY() > screenHeight - (2 * buttonHeight)
				&& StdDraw.mouseY() < screenHeight - buttonHeight) {
			stopButton();
		}

		// Checks step button
		if (StdDraw.mouseX() > screenWidth - buttonWidth && StdDraw.mouseX() < screenWidth
				&& StdDraw.mouseY() < screenHeight - (2 * buttonHeight)
				&& StdDraw.mouseY() > screenHeight - (3 * buttonHeight)) {
			stepButton();
		}

		// Checks reset button
		if (StdDraw.mouseX() > screenWidth - buttonWidth && StdDraw.mouseY() < screenHeight - (3 * buttonHeight)
				&& StdDraw.mouseY() > screenHeight - (4 * buttonHeight)) {
			setBoard(false);
		}
		
		// Checks reset random button
				if (StdDraw.mouseX() > screenWidth - buttonWidth && StdDraw.mouseY() < screenHeight - (4 * buttonHeight)
						&& StdDraw.mouseY() > 0) {
					setBoard(true);
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
	
	public static void setBoard(boolean random)
	{
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				board[i][j] = new Colony(i, j, random);
			}
		}
	}

}
