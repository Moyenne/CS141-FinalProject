package edu.cpp.cs.cs141.FinalProject;

import edu.cpp.cs.cs141.FinalProject.GridObject.ObjectType;

/**
 * The player's character: a spy. Can look two spaces in a direction, walk one space, and shoot. 
 * @author Team 404
 */
public class Player extends GridObject
{
	/**
	 * The player's extra lives. Game over at lifeCount == 0.
	 */
	private int lifeCount = 3;
	
	/**
	 * Whether or not the player has a bullet. This must be checked before shooting. The player can only have 1 or 0 bullets.
	 */
	private boolean hasBullet = true;
	
	/**
	 * Returns whether or not the Player has enabled the Radar item. This is checked in the Grid class to see if the winning
	 * room's number should be printed.
	 */
	private boolean radarEnabled = false;
	
	/**
	 * Invincibility state triggered by Star item. Player cannot be killed while this is true.
	 */
	private boolean isInvincible = false;
	
	/**
	 * The player's remaining time being invincible. Once it is the player's turn, this value
	 * decreases by one, and, when the value equals 0, the player's isInvincible value is set
	 * to false.
	 */
	private int invincibilityLength = 5;
	
	/**
	 * Constructor for Player. Takes position on the Grid as arguments.
	 */
	public Player(int row, int col)
	{
		super(row, col);
		secret = ObjectType.PLAYER;
		mark = 'P';
		super.setVisibility(true);
	}
	
	/**
	 * Returns the number of lives that the player has left.
	 */
	public int getLifeCount()
	{
		return lifeCount;
	}
	
	/**
	 * Upon dying, decreases number of lives remaining by 1.
	 */
	public void decreaseLifeCount()
	{
		lifeCount--;
	}
	
	/**
	 * Checks to see if the Player has a Bullet to shoot.
	 */
	public boolean hasBullet()
	{
		return hasBullet;
	}
	
	/**
	 * Gives the player a Bullet if they do not have one already.
	 */
	public void gainBullet()
	{
		if(!hasBullet)
		{
			hasBullet = true;
		}
	}
	
	/**
	 * Takes away the Player's bullet to shoot the gun.
	 */
	public boolean shoot()
	{
		if(hasBullet)
		{
			hasBullet = false;
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/**
	 * A simple method that returns the boolean value stored by the radarEnabled variable.
	 */
	public boolean radarEnabled()
	{
		return radarEnabled;
	}
	
	/**
	 * A simple method that sets the boolean value stored by the radarEnabled variable to true.
	 */
	public void enableRadar()
	{
		radarEnabled = true;
	}
	
	/**
	 * A simple method that sets the boolean value stored by the radarEnabled variable to false.
	 */
	public void disableRadar()
	{
		radarEnabled = false;
	}
	
	/**
	 * Returns whether or not the player is invincible.
	 */
	public boolean isInvincible()
	{
		return isInvincible;
	}
	
	/**
	 * Makes the player invincible, or makes the player normal again after invincibility has expired.
	 */
	public void switchInvincibility()
	{
		if(isInvincible)
		{
			isInvincible = false;
		}
		else
		{
			isInvincible = true;
		}
	}
	
	/**
	 * A method to incrementally decrease the player's invincibilityLength value, as the player can only
	 * be invincible for 5 turns. Once the value has been decreased to equal 0, the switchInvincibility
	 * method is automatically called.
	 */
	public void decreaseInvincibility()
	{
		invincibilityLength--;
		if(invincibilityLength == 0)
		{
			switchInvincibility();
		}
	}
	
	/**
	 * A simple method that returns the int value stored by the invincibilityLength variable, showing how long
	 * the Player will be invincibile for.
	 */
	public int getInvincibilityLength()
	{
		return invincibilityLength;
	}
	
	/**
	 * A simple method that returns true always, confirming this is, in fact, a player. This is
	 * used for grid scans later on in the Engine and Grid classes.
	 */
	public boolean isAPlayer()
	{
		return true;
	}
}
