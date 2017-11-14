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
	 * The player's remaining time being invincible. Once it is the player's turn, this value
	 * decreases by one, and, when the value equals 0, the player's isInvincible value is set
	 * to false.
	 */
	private int invincibilityLength = 5;
	
	/**
	 * Invincibility state triggered by Star item. Player cannot be killed while this is true.
	 */
	private boolean isInvincible = false;
	
	/**
	 * Whether or not the player has a bullet. This must be checked before shooting. The player can only have 1 or 0 bullets.
	 */
	private boolean hasBullet = true;
	
	private boolean radarEnabled = false;
	
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
	
	public void shoot()						// modified by Dongri
	{
		hasBullet = false;
	}
	
	public boolean radarEnabled()
	{
		return radarEnabled;
	}
	
	public void enableRadar()
	{
		radarEnabled = true;
	}
	
	public void disableRadar()
	{
		radarEnabled = false;
	}
	
	public boolean isAPlayer()
	{
		return true;
	}
}
