
public class SunPlant extends Plant
{
	private int sun, sunTick;
	
	protected SunPlant(int health, int sun, int sunTick)
	{
		super(health);
		this.sun = sun;
		this.sunTick = sunTick;
	}
	/**
	 * @return 
	 */
	public int getSun()
	{
		return this.sun;
	}
	
	/**
	 * @return 
	 */
	public int getSunTick()
	{
		return this.sunTick;
	}
}
