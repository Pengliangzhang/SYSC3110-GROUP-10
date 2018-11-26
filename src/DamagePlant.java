import java.io.Serializable;

/**
 * This class represents plants that are able to deal damage to zombies. Has
 * range, damage, and attack speed.
 * 
 * @author BeckZ, Kevin, Xinrui Li, Bohua Cao
 * @version Oct 28, 2018
 */
public class DamagePlant extends Plant implements Serializable {
	private int range, damage, damageTick;

	/**
	 * Constructor for DamagePlants.
	 * 
	 * @param x          Column number
	 * @param y          Row number
	 * @param health     Health that this plant is to have
	 * @param range      Range that this plant is to have
	 * @param damage     Damage that this plant is to have
	 * @param damageTick How many ticks that this plant must wait between attacks
	 * @param sunCost    The sun required to plant this plant
	 */
	protected DamagePlant(int x, int y, int health, int range, int damage, int damageTick, int sunCost) {
		super(x, y, health, sunCost);
		this.range = range;
		this.damage = damage;
		this.damageTick = damageTick;
	}

	/**
	 * 
	 * @return return the range that the plant is able to attack from
	 */
	public int getRange() {
		return this.range;
	}

	/**
	 * 
	 * @return return this plant's damage per attack
	 */
	public int getDamage() {
		return this.damage;
	}

	/**
	 *
	 * @return return how many ticks this plant needs to wait between attacks
	 */
	public int getDamageTick() {
		return this.damageTick;
	}
}
