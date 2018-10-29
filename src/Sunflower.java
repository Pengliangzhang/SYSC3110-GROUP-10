/**
 * Represents the Sunflower in the original PvZ, except that it costs 25 sun.
 * 
 * @author BeckZ, Kevin, Xinrui Li, Bohua Cao
 * @version Oct 28, 2018
 */

public class Sunflower extends SunPlant
{
	/**
	 * The parameters for health, sun, etc. are standardized.
	 * Just need to give the row and column numbers to plant it in.
	 * 
	 * @param x Column number
	 * @param y Row number
	 */
	public Sunflower(int x, int y)
	{
		super(x, y, 100, 25, 2, 25);
	}
}
