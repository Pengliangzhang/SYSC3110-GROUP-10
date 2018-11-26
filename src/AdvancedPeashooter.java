import java.io.Serializable;

/**
 * Represents an improved Peashooter plant, with more damage and health.
 * 
 * @author BeckZ, Kevin, Xinrui Li, Bohua Cao
 * @version Oct 28, 2018
 */
public class AdvancedPeashooter extends DamagePlant implements Serializable
{
	/**
	 * The parameters for health, damage, etc. are standardized.
	 * Just need to give the row and column numbers to plant it in.
	 * 
	 * @param x Column number
	 * @param y Row number
	 */
	public AdvancedPeashooter(int x, int y)
	{
		super(x, y, 150, 10, 50, 2, 75);
	}
}