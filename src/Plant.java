
public class Plant
{
	private int health;
	
	protected Plant(int health)
	{
		this.health = health;
	}
	
	public int getHealth()
	{
		return this.health;
	}
	
	public void takeDamage(int amount)
	{
		this.health -= amount;
	}
}
