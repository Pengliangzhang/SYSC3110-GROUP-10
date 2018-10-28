/**
 * This class create a text-based Plants vs Zombie game
 * @author BeckZ, Kevin, Xinrui Li, Bohua Cao
 * @version Oct 28, 2018
 */
public class Zombie extends Entity
{
	private int damage, health, moveSpeed, attackSpeed;
	
	/**
	 * Constructor for all zombies.
	 * 
	 * @param damage Damage per attack
	 * @param health Health
	 * @param moveSpeed Ticks required to move 1 space forward
	 * @param attackSpeed Ticks between attacks
	 */
	protected Zombie(int x, int y, int damage, int health, int moveSpeed, int attackSpeed)
	{
		super(x, y);
		this.damage = damage;
		this.health = health;
		this.moveSpeed = moveSpeed;
		this.attackSpeed = attackSpeed;
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
}
