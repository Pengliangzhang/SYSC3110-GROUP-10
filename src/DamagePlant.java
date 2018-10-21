
public class DamagePlant extends Plant
{
	private int range;
	
	private int damage, damageTick;
	
	protected DamagePlant(int health, int range, int damage, int damageTick)
	{
		super(health);
		this.range = range;
		this.damage = damage;
		this.damageTick = damageTick;
	}
	
	public int getRange()
	{
		return this.range;
	}
	
	public int getDamage()
	{
		return this.damage;
	}
	
	public int getDamageTick()
	{
		return this.damageTick;
	}
}
