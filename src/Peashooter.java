import java.io.Serializable;

/**
 * Represents the Peashooter in the original PvZ, except that it costs 30 sun.
 * 
 * @author BeckZ, Kevin, Xinrui Li, Bohua Cao
 * @version Oct 28, 2018
 */
public class Peashooter extends DamagePlant implements Serializable
{
	/**
	 * The parameters for health, damage, etc. are standardized.
	 * Just need to give the row and column numbers to plant it in.
	 * 
	 * @param x Column number
	 * @param y Row number
	 */
	public Peashooter(int x, int y)
	{
		super(x, y, 100, 10, 25, 2, 50);
	}
}
