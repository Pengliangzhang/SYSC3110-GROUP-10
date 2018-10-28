/**
 * This class create a text-based Plants vs Zombie game
 * @author BeckZ, Kevin, Xinrui Li, Bohua Cao
 * @version Oct 28, 2018
 */
public class Plant extends Entity
{
	private int health;
	
	/**
	 * 
	 * @param health The plant's health
	 */
	protected Plant(int x, int y, int health)
	{
		super (x, y);
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
