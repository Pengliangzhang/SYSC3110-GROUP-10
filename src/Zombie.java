
/**
 * Represents zombies.
 * 
 * @author Kevin Li
 * @version October 21, 2018
 */
public class Zombie extends Entity
{
	private int damage, health, moveSpeed, attackSpeed;
	private String name;
	
	/**
	 * Constructor for all zombies.
	 * 
	 * @param damage Damage per attack
	 * @param health Health
	 * @param moveSpeed Ticks required to move 1 space forward
	 * @param attackSpeed Ticks between attacks
	 */
	protected Zombie(int x, int y, int damage, int health, int moveSpeed, int attackSpeed, String name)
	{
		super(x, y);
		this.damage = damage;
		this.health = health;
		this.moveSpeed = moveSpeed;
		this.attackSpeed = attackSpeed;
		this.name = name;
	}
	
	/**
	 * 
	 * @return this zombie's damage per attack
	 */
	public int getDamage()
	{
		return this.damage;
	}
	
	/**
	 * 
	 * @return this zombie's current health
	 */
	public int getHealth()
	{
		return this.health;
	}
	
	/**
	 * 
	 * @return how many ticks it takes for this zombie to move 1 space forward
	 */
	public int getMoveSpeed()
	{
		return this.moveSpeed;
	}
	
	/**
	 * 
	 * @return how many ticks it takes for this zombie to attack again
	 */
	public int getAttackSpeed()
	{
		return this.attackSpeed;
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
