package edu.cpp.cs.cs141.FinalProject;


/**
 * This class represents the radar power up. Essentially the radar power up will
 * give the player the room number in which the briefcase is in. This class contains
 * the properties of the power up such as the position on the grid it is in and
 * the type of item it is.
 * @author Team 404
 */
public class Radar extends Item
{
	/**
	 * Constructs an object of type {@code Radar} with a position on the grid determined
	 * by the given parameters, and with an item type of "Radar".
	 * @param row the row in which the radar power up resides in on the grid. An argument of
	 * zero will refer to the first row of the grid.
	 * @param col the column in which the radar power up resides in on the grid. An argument of
	 * zero will refer to the first column of the grid.
	 */
	public Radar(int row, int col)
	{
		super("Radar", row, col);
		mark = 'R';
	}
	
	/**
	 * Applies the effect of this item. This effect switches the boolean value of the Player's radarEnabled
	 * variable, allowing the Player to see the room that contains the briefcase.
	 */
	public void applyItemEffect(Player target)
	{
		if(!target.radarEnabled())
			target.enableRadar();
	}
}
