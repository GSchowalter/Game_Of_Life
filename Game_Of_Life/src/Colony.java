
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
		double temp = StdDraw.getPenRadius();
		StdDraw.setPenRadius(.01);
		if (alive)
		{
			StdDraw.setPenColor(StdDraw.YELLOW);
		}
		else
		{
			StdDraw.setPenColor(StdDraw.RED);
		}
		StdDraw.filledSquare((x * length) + length / 2, (y * length) + length / 2, length / 2);
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.square((x * length) + length / 2, (y * length) + length / 2, length / 2);
		StdDraw.setPenRadius(temp);
	}
	
	public void setPosition(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
}
