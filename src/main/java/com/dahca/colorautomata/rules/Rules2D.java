package com.dahca.colorautomata.rules;

import com.dahca.colorautomata.cells.*;
/**
 * Defines under what conditions are a cell's state changed.
 * @author Ian
 *
 * @param <C>
 */
public interface Rules2D<C extends Cell2D>
{
	/**
	 * Possibilities for cells to be born.
	 */
	static int[] BORN = new int[0];
	/**
	 * Possibilities for cells to stay alive.
	 */
	static int[] DIES = new int[0];
	/**
	 * Gives the state of the cell at x,y after one step based on it's neighbors.
	 * @param field
	 * @param x
	 * @param y
	 * @return
	 */
	CellState outcome(C[][] field, int x, int y);
}
