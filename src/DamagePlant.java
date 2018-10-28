
public class DamagePlant extends Plant
{
	private int range;
	private int sun;
	private int damage, damageTick;
	
	protected DamagePlant(int x, int y, int health, int range, int damage, int damageTick,String name, int sun)
	{
		super(x, y, health,name);
		this.range = range;
		this.damage = damage;
		this.damageTick = damageTick;
		this.sun = sun;
	}
	/**
	 * @return return the range that the plant is able to attack
	 */
	public int getRange()
	{
		return this.range;
	}
	
	public int getsunNeed() {
		return sun;
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
}
