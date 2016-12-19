package com.dahca.colorautomata.fields;

import java.awt.Point;
import java.awt.Rectangle;

import com.dahca.colorautomata.cells.*;

/**
 * This represents a field constructed of 2D square cells
 * @author Ian Neal
 *
 * @param <C> any Cell2D subclass
 */
public abstract class Field2D<C extends Cell2D>
{
	/**
	 * Pixel width of field
	 */
	protected int width;
	/**
	 * Pixel height of field
	 */
	protected int height;
	/**
	 * Pixel width/height of each cell
	 */
	protected int box_size;
	/**
	 * The array of cells defining this field
	 */
	protected C[][] board;
	/**
	 * Goes to the next iteration of the simulation
	 * @return The resulting board
	 */
	public abstract C[][] step();
	/**
	 *
	 * @return This field's cellular representation
	 */
	public C[][] getCells()
	{
		return board;
	}
	/**
	 * Get the list of cell coordinates around a point in the field.
	 * @return An array of points
	 */
	public abstract Point[] getAdjacent(Point p);
	/**
	 * Overload
	 * @param p
	 * @return
	 */
	public Point[] getAdjacent(int x, int y)
	{
		return getAdjacent( new Point(x,y));
	}
}
