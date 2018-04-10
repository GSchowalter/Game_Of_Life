
public class GameOfLife {

	public static int screenWidth = 1800;
	public static int screenHeight = 1800;
	public static int length = 30;
	public static double colonyWidth = (double) screenWidth / length;
	public static boolean ENABLED = true;
	public static Colony[][] board;
	
	public static void main(String[] args)
	{
		StdDraw.enableDoubleBuffering();
		StdDraw.setCanvasSize(screenWidth, screenHeight);
		StdDraw.setXscale(0, screenWidth);
		StdDraw.setYscale(0, screenHeight);
		board = new Colony[length][length];
		for (int i = 0; i < board.length; i++)
		{
			for (int j = 0; j < board[i].length; j++)
			{
				board[i][j] = new Colony(i, j);
			}
		}
		
		while (ENABLED)
		{
			show();
		}
	}
	
	public static void show()
	{
		for (Colony[] list : board)
		{
			for (Colony colony : list)
			{
				colony.show(colonyWidth);
			}
		}
		StdDraw.show();
	}
}
