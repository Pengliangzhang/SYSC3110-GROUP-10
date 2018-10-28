

/**
 * Represents plants.
 * 
 * @author Kevin Li
 * @version October 21, 2018
 */
public class Plant extends Entity
{
	private int health;
	private String name;
	
	
	
	/**
	 * 
	 * @param health The plant's health
	 */
	protected Plant(int x, int y, int health, String name)
	{
		super (x, y);
		this.health = health;
		this.name = name;
	
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
	
	public String getName() {
		return name;
	}
	
}
