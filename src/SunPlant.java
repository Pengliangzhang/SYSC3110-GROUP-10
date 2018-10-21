
public class SunPlant extends Plant
{
	private int sun, sunTick;
	
	protected SunPlant(int x, int y, int health, int sun, int sunTick)
	{
		super(x, y, health);
		this.sun = sun;
		this.sunTick = sunTick;
	}
	
	/**
	 * 
	 * @return how much sun this plant creates
	 */
	public int getSun()
	{
		return this.sun;
	}
	
	/**
	 * 
	 * @return how many ticks between sun production
	 */
	public int getSunTick()
	{
		return this.sunTick;
	}
}
