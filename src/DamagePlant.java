/**
 * This class create a text-based Plants vs Zombie game
 * @author BeckZ, Kevin, Xinrui Li, Bohua Cao
 * @version Oct 28, 2018
 */
public class DamagePlant extends Plant
{
	private int range;
	
	private int damage, damageTick;
	
	private int sun, sunTick;
	
	
	protected DamagePlant(int x, int y, int health, int range, int damage, int damageTick)
	{
		super(x, y, health);
		this.range = range;
		this.damage = damage;
		this.damageTick = damageTick;
		this.sun = 50;
	}
	/**
	 * @return return the range that the plant is able to attack
	 */
	public int getRange()
	{
		return this.range;
	}
	
	/**
	 * 
	 */
	public int getDamage()
	{
		return this.damage;
	}
	
	public int getDamageTick()
	{
		return this.damageTick;
	}
	
	/**
	 * @desc
	 * @author BeckZ
	 * @return return how many sun are needed to create this plant
	 */
	public int getSun()
	{
		return this.sun;
	}
}
