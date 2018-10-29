/**
 * This class represents a basic plant.
 * It keeps track of its own health and how much it costs to plant.
 * 
 * @author BeckZ, Kevin, Xinrui Li, Bohua Cao
 * @version Oct 28, 2018
 */
public class Plant extends Entity
{
	private int health, sunCost;
	
	/**
	 * Constructor for Plant objects.
	 * 
	 * @param x Column number
	 * @param y Row number
	 * @param health The plant's health
	 * @param sunCost How much sun this plant requires in order to be planted
	 */
	protected Plant(int x, int y, int health, int sunCost)
	{
		super (x, y);
		this.health = health;
		this.sunCost = sunCost;
	}
	
	/**
	 *
	 * @return this plant's current health
	 */
	public int getHealth()
	{
		return this.health;
	}
	
	/**
	 * 
	 * @param amount How much the health is reduced by
	 */
	public void takeDamage(int amount)
	{
		this.health -= amount;
	}
	
	/**
	 * 
	 * @return the amount of sun that this plant requires in order to be planted
	 */
	public int getSunCost()
	{
		return this.sunCost;
	}
}
