package entities;
import java.io.Serializable;

/**
 * Represents the most basic zombie.
 * 
 * @author BeckZ, Kevin, Xinrui Li, Bohua Cao
 * @version December 7, 2018
 */

public class BasicZombie extends Zombie implements Serializable
{
	/**
	 * All the parameters are standardized, just need to give the row that the zombie will attack.
	 * 
	 * @param row The zombie will spawn in this row
	 */
	public BasicZombie(int row)
	{
		super(row, 10, 25, 100, 1, 2);
	}
}
