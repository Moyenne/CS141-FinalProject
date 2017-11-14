package edu.cpp.cs.cs141.FinalProject;

import edu.cpp.cs.cs141.FinalProject.GridObject.ObjectType;

/**
 * AI controlled ninja-assassins. Moves one space randomly. Kills the Player if adjacent at the start of Enemy turn.
 * @author Team 404
 */
public class Enemy extends GridObject
{
	/**
	 * Is the Enemy alive?
	 */
	private boolean isAlive = true;
	
	/**
	 * Constructor for Enemy. Takes location on Grid as arguments.
	 */
	public Enemy(int row, int col)
	{
		super(row, col);
		secret = ObjectType.ENEMY;
		mark = 'E';
	}
	
	/**
	 * Returns whether or not the Enemy is alive.
	 */
	public boolean isAlive()
	{
		return isAlive;
	}
	
	/**
	 * Kills the Enemy upon being shot by the player.
	 */
	public void die()
	{
		isAlive = false;
	}
	
	public boolean isAnEnemy()
	{
		return true;
	}
}