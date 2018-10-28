/**
 * This class create a text-based Plants vs Zombie game
 * @author BeckZ, Kevin, Xinrui Li, Bohua Cao
 * @version Oct 28, 2018
 */

public class SunPlant extends Plant
{
	private int sun, sunTick, tick;
	
	protected SunPlant(int x, int y, int health, int sun, int sunTick)
	{
		super(x, y, health);
		this.sun = sun;
		this.sunTick = sunTick;
		tick = 1;
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
	
	/**
	 * Return the sun produce by sun plant
	 * 
	 * @return the sun produce in a specific tick
	 */
	public int generateSun() {
		if (tick == sunTick) {
			tick = 1;
			return 50;
		}
		tick++;
		return 0;
	}
}
