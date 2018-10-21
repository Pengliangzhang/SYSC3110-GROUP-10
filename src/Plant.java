
/**
 * Represents plants.
 * 
 * @author Kevin
 * @version October 21, 2018
 */
public class Plant
{
	private int health;
	
	/**
	 * 
	 * @param health The plant's health
	 */
	protected Plant(int health)
	{
		this.health = health;
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
}
