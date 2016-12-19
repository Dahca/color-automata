package com.dahca.colorautomata.cells;
/**
 * Represents the concept of a Cell for simulation. This high-level cell view
 * only has information about the state of the cell.
 * @author Ian
 *
 */
public abstract class VagueCell
{
	/**
	 * Defaults to CellState.DEAD for convenience.
	 */
	private CellState state = CellState.DEAD;
	/**
	 * Fetch the current state of the cell. If uninitialized, the state of the
   * cell defaults to CellState.DEAD.
   *
	 * @return CellState state - the current state of the cell
	 */
	public CellState getState()
	{
		return state;
	}
	/**
	 * Set the state of the cell.
	 * @param cs - the desired state of the cell.
	 */
	public void setState(CellState cs)
	{
		state = cs;
	}
}
