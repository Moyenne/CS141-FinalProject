package edu.cpp.cs.cs141.FinalProject;

/**
 * The Star class represents the power up that makes the player invincible,
 * thus being unable to be killed by the enemies. This class contains the
 * properties of the power up such as its position on the grid and the type
 * of item it is.
 * @author Team 404
 */
public class Star extends Item
{
	/**
	 * Constructs an object of type {@code Star} with a position on the grid determined by the
	 * given parameters and an item type of "Invincibility".
	 * @param row the row in which this {@code Star} resides in on the grid. An argument of
	 * zero will refer to the first row on the grid.
	 * @param col the column in which this {@code Star} resides in on the grid. An argument of
	 * zero will refer to the first column on the grid.
	 */
	public Star(int row, int col)
	{
		super("Invincibility", row, col);
		mark = 'S';
	}
	
	/**
	 * Applies the effect of this item. The effect of the star power up will turn the given
	 * {@code Player} object invincible.
	 * @param target the object of type {@code Player} to turn invincible
	 */
	public void applyItemEffect(Player target)
	{
		if(!target.isInvincible())
			target.switchInvincibility();
	}
}
