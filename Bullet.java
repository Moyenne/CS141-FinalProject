package edu.cpp.cs.cs141.FinalProject;

/**
 * This class represents the power up that lets the player gain a bullet in their gun.
 * It has properties such as its position on the grid and the type of item it is.
 * @author Team 404
 */
public class Bullet extends Item
{
	/**
	 * Constructs an object of type {@code Bullet} with a position on the grid determined by
	 * the given parameters, and an item type of "Bullet".
	 * @param row the row in which this object resides in on the grid. An argument of zero
	 * refers to the first row on the grid.
	 * @param col the column in which this object resides in on the grid. An argument of zero
	 * refers to the first column on the grid.
	 */
	public Bullet(int row, int col)
	{
		super("Bullet", row, col);
		mark = 'B';
	}
	
	/**
	 * Applies the effect of this item. The effect of the bullet power up item is to
	 * gain a bullet. The given {@code Player} object will then gain one bullet.
	 * @param target the target {@code Player} object that will gain a bullet
	 */
	public void applyItemEffect(Player target)
	{
		target.gainBullet();
	}
}
