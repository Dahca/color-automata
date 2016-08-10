package fields;
import java.awt.Point;

import rules.*;
import cells.*;
/**
 * This is a field that is represented by 2D square cells where the top edge continues to the bottom and the sides 
 * to one another - basically a simulation of 2D square cells on the surface of a toroid.
 * @author Ian
 *
 * @param <C> any Cell2D subclass
 */
public abstract class WrapAroundField2D<C extends Cell2D> extends Field2D<C>
{
	public Point[] getAdjacent(Point p)
	{
		Point[] arr = new Point[8];
		int width_of_field = board.length;
		int height_of_field = board[0].length;
		//Get the 8 cells surrounding the given cell
		arr[0] = new Point(p.x - 1, p.y - 1);
		arr[1] = new Point(p.x - 1, p.y);
		arr[2] = new Point(p.x - 1, p.y + 1);
		arr[3] = new Point(p.x , p.y - 1);
		arr[4] = new Point(p.x , p.y + 1);
		arr[5] = new Point(p.x + 1, p.y - 1);
		arr[6] = new Point(p.x + 1, p.y);
		arr[7] = new Point(p.x + 1, p.y + 1);
		for( int i = 0; i < arr.length; i++ )
		{
			if(arr[i].x < 0)
				arr[i] = new Point(width_of_field - 1, arr[i].y);
			else if(arr[i].x >= width_of_field)
				arr[i] = new Point(0, arr[i].y);
			if(arr[i].y < 0)
				arr[i] = new Point(arr[i].x, height_of_field - 1);
			else if(arr[i].y >= height_of_field)
				arr[i] = new Point(arr[i].x, 0);
		}
		return arr;
	}
}
