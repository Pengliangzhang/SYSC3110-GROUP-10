/**
 *  construct sun plant.
 * 
 * @author bohua Cao
 * @version October 21, 2018
 */

public class SunPlant extends Plant
{
	private int sunTick,sun;
	
	protected SunPlant(int x, int y, int health, int sun, int sunTick, String name )
	{
		super(x, y, health,name);
		this.sun = sun;
		this.sunTick = sunTick;
	}
	
	/**
	 * 
	 * @return how much sun this plant creates
	 */
	public int getSun() {
		return sun;
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
