
public class Colony {

	private boolean populated;
	private boolean shouldTurnFate;
	// x and y on coordinate-plane, bottom left is (0,0)
	private int x;
	private int y;
	
	public Colony(int x, int y)
	{
		populated = false;
		shouldTurnFate = false;
		this.x = x;
		this.y = y;
	}
	
	public void show(double length)
	{
		double temp = StdDraw.getPenRadius();
		StdDraw.setPenRadius(.001);
		if (populated)
		{
			StdDraw.setPenColor(StdDraw.DARK_GRAY);
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
	
	public void setFate()
	{
		if (shouldTurnFate == true) 
		{
		populated = !populated;
		shouldTurnFate = false;
		}
	}
	
	public boolean getPopulated()
	{
		return populated;
	}
	
	public void setShouldTurnFate()
	{
		shouldTurnFate = !shouldTurnFate;
	}
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
	
	public boolean getShouldTurnFate()
	{
		return shouldTurnFate;
	}
	
	
}
