package cells;
import java.awt.Rectangle;
/** 
 * This cell represents a 2D rectangular Cell.
 * @author Ian Neal
 *
 */
public abstract class Cell2D extends VagueCell
{
	/**
	 * The rectangle that represents the bounds of the cell.
	 */
	protected Rectangle box;
	/**
	 * Fetch the cell's bounding box. Defaults to null if uninitialized.
	 * @return this cell's bounding rectangle
	 */
	public Rectangle getRectangle()
	{
		return box;
	}
	/**
	 * Set the cell's bounding rectangle
	 * @param rect - the rectangle representing the cell
	 */
	public void setRectangle( Rectangle rect )
	{
		box = rect;
	}
}
