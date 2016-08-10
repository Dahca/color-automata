package rules;
import cells.*;
/**
 * Defines under what conditions cells are to live and die - if surrounded by 
 * 3 cells, a dead cell becomes alive. If a living cell has 2 or 3 neighbors, it
 * continues to live. Otherwise, cells either die or stay dead
 * @author Ian
 *
 * @param <C>
 */
public interface B3_S23<C extends Cell2D> extends Rules2D<C>
{
	static int[] BORN = {3};
	static int[] LIVE = {2,3};
	
}
