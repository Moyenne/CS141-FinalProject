package edu.cpp.cs.cs141.FinalProject;

import edu.cpp.cs.cs141.FinalProject.GridObject.ObjectType;

/**
 * AI controlled ninja-assassins. Moves one space randomly. Kills the Player if adjacent at the start of Enemy turn.
 * @author Team 404
 */
public class Enemy extends GridObject
{
	/**
	 * Is the Enemy alive? This determines if the Enemy will be able to move, attack, or even be printed onto the
	 * board during the enemyTurn method of the Engine class.
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
	 * Kills the Enemy upon being shot by the player, setting isAlive to false.
	 */
	public void die()
	{
		isAlive = false;
	}
	
	/**
	 * A simple method that returns true always, confirming this is, in fact, an enemy. This is
	 * used for grid scans later on in the Engine and Grid classes.
	 */
	public boolean isAnEnemy()
	{
		return true;
	}
}
