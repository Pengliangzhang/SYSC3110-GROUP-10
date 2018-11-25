import java.io.Serializable;

/**
 * This class represents plants that specifically generate sun for the player to use.
 * Extends the plant class, and adds sun generated amount, and how often the sun is generated.
 * 
 * @author BeckZ, Kevin, Xinrui Li, Bohua Cao
 * @version Oct 28, 2018
 */
public class SunPlant extends Plant implements Serializable
{
	private int sun, sunTick, tick;
	
	/**
	 * Constructor for SunPlant.
	 * 
	 * @param x Column number
	 * @param y Row number
	 * @param health Health to be given to the SunPlant
	 * @param sun How much sun this SunPlant generates
	 * @param sunTick How many ticks between sun generation
	 */
	protected SunPlant(int x, int y, int health, int sun, int sunTick, int sunCost)
	{
		super(x, y, health, sunCost);
		this.sun = sun;
		this.sunTick = sunTick;
		
		// keeps track of when it's time to generate sun
		this.tick = 1;
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
	
	/*
	 * @return the current tick
	 */
	public int getCurrentTick() {
		return tick;
	}
	
	/*
	 * set the current tick to a number that user choose 
	 */
	public void setCurrentTick(int tick) {
		this.tick = tick;
	}
	
	/**
	 * Called every tick by the Game class, and returns a non-zero value when it's time to generate sun.
	 * 
	 * @return the sun produce in a specific tick
	 */
	public int generateSun()
	{
		if (tick == sunTick)
		{
			tick = 1;
			return this.sun;
		}
		else 
		{
			tick++;
			return 0;
		}
	}
}
