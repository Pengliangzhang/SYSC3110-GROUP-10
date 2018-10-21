
public class Zombie
{
	private int damage, health, speed;
	
	protected Zombie(int damage, int health, int speed)
	{
		this.damage = damage;
		this.health = health;
		this.speed = speed;
	}
	
	public int getDamage()
	{
		return this.damage;
	}
	
	public int getHealth()
	{
		return this.health;
	}
	
	public int getSpeed()
	{
		return this.speed;
	}
}
