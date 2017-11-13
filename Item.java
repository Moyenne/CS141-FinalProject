package edu.cpp.cs.cs141.FinalProject;

import edu.cpp.cs.cs141.FinalProject.GridObject.ObjectType;

/**
 * This class represents the superclass version of the various items placed throughout the game.
 * It contains basic information shared by all items, such as having a type, and a position on the
 * grid.
 * @author Team 404
 */
public class Item extends GridObject
{
	/**
	 * A String value that is used to determine whether or not the item is a Bullet, Star, or
	 * Radar. It can be other things, but the Engine only creates these three.
	 */
	private String itemType;
	
	/**
	 * The main constructor for the class. It accepts three parameters, a String and two ints.
	 * These determine the itemType and its position on the grid.
	 * @param itemType is the item's... type. Duh. It is stored in the field variable of the
	 * same name.
	 * @param col is the room's column position. It can be changed with the superclass method.
	 * @param row is the room's row position. It can be changed with the superclass method.
	 */
	public Item(String itemType, int row, int col)
	{
		super(row, col);
		this.itemType = itemType;
		secret = ObjectType.ITEM;
	}
	
	/**
	 * An alternative constructor for the class. THere are only the two int parameters, as the
	 * itemType is now set to a default value of "None".
	 * @param col is the room's column position. It can be changed with the superclass method.
	 * @param row is the room's row position. It can be changed with the superclass method.
	 */
	public Item(int row, int col)
	{
		super(row, col);
		itemType = "None";
	}
	
	/**
	 * A simple method that returns the String value stored in the itemType variable.
	 */
	public String getType()
	{
		return itemType;
	}
	
	/**
	 * A simple method that returns true always, confirming this is, in fact, an item. This is
	 * used for grid scans later on in the Engine and Grid classes.
	 */
	public boolean isAnItem()
	{
		return true;
	}
	
	/**
	 * A superclass version of the applyItemEffect method present in the three Item subclasses.
	 * It contains no actual function.
	 * @param target is just the current player character, which allows the Engine to apply the
	 * various item effects to the player.
	 */
	public void applyItemEffect(Player target)
	{
		
	}
}
