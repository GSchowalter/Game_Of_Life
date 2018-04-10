
public class Colony {

	private boolean alive;
	
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
		StdDraw.filledSquare(length/2, length/2, length/2);
	}
}
