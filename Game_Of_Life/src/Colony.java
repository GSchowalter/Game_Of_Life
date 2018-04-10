
public class Colony {

	private boolean alive;
	// x and y on coordinate-plane, bottom left is (0,0)
	private int x;
	private int y;
	
	public Colony(int x, int y)
	{
		alive = false;
		this.x = x;
		this.y = y;
	}
	
	public void show(double length)
	{
		if (alive)
		{
			StdDraw.setPenColor(StdDraw.YELLOW);
		}
		else
		{
			StdDraw.setPenColor(StdDraw.RED);
		}
		StdDraw.filledSquare((x * length) + length / 2, (y * length) + length / 2, length/2);
	}
}
