import java.io.Serializable;

/**
 * Represents a tougher zombie than a basic one, with more health and damage.
 * 
 * @author BeckZ, Kevin, Xinrui Li, Bohua Cao
 * @version Oct 28, 2018
 */

public class AdvancedZombie extends Zombie implements Serializable
{
	/**
	 * All the parameters are standardized, just need to give the row that the zombie will attack.
	 * 
	 * @param row The zombie will spawn in this row
	 */
	public AdvancedZombie(int row)
	{
		super(row, 10, 50, 150, 1, 2);
	}
}
