package cells;

import java.awt.Color;
/**
 * Represents a rectangular 2D cell with a color attribute
 * @author Ian Neal
 *
 */
public class ColorCell2D extends Cell2D
{
	protected Color color;
	public ColorCell2D()
	{
		color = new Color(0,0,0);
		setState(CellState.DEAD);
	}
	/**
	 * 
	 * @return This cell's color
	 */
	public Color getColor()
	{
		return color;
	}
	/**
	 * Sets the cell's color to a pre-defined color object 
	 * @param c - java.awt.Color object
	 */
	public void setColor( Color c )
	{
		color = c;
	}
	/**
	 * Sets the cell's color to a color using the given rgb values. Function is
	 * tolerant to out-of-range rgb values per the ColorCell2D.genColor(r,g,b) function spec.
	 * @param red
	 * @param green
	 * @param blue
	 */
	public void setColor( int red, int green, int blue )
	{
		color = genColor(red,green,blue);
	}
	/**
	 * This function generates a color based on rgb values. However, instead of 
	 * creating an error on values outside of the 0-255 range accepted for rgb values, 
	 * this will adjust the values so that the parameters are either rounded up to 0 or down
	 * to 255. 
	 * @param red
	 * @param green
	 * @param blue
	 * @return a new, valid color object
	 */
	public static Color genColor( int red, int green, int blue )
	{
		red = red > 255 ? 255 : red;
		red = red < 0 ? 0 : red;
		green = green > 255 ? 255 : green;
		green = green < 0 ? 0 : green;
		blue = blue > 255 ? 255 : blue;
		blue = blue < 0 ? 0 : blue;
		return new Color(red,green,blue);
	}
}
