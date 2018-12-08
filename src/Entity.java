import java.io.Serializable;

/**
 * This class is the superclass for all entities on the board (zombies and plants).
 * Keeps track of their position.
 * 
 * @author BeckZ, Kevin, Xinrui Li, Bohua Cao
 * @version December 7, 2018
 */
public class Entity implements Serializable
{
	private int x, y;
	
	/**
	 * Constructor for all entities.
	 * 
	 * @param x Column number
	 * @param y Row number
	 */
	protected Entity(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	/**
	 * 
	 * @return return this entity's column number
	 */
	public int getX()
	{
		return this.x;
	}
	
	/**
	 * 
	 * @return return this entity's row number
	 */
	public int getY()
	{
		return this.y;
	}
	
	/**
	 * Changes this entity's column number to the given one.
	 * 
	 * @param x New column number
	 */
	public void setX(int x)
	{
		this.x = x;
	}
	
	/**
	 * Changes this entity's row number to the given one.
	 * 
	 * @param y New row number
	 */
	public void setY(int y)
	{
		this.y = y;
	}
}
