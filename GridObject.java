package edu.cpp.cs.cs141.FinalProject;

import java.io.Serializable;

/**
 * This class represents all objects that are going to be placed on the grid throughout
 * the game. The main purpose of this class is to save shared aspects between the various
 * different objects, such as a position on the grid.
 * @author Team 404
 */
public class GridObject implements Serializable
{
	private GridObject storedObj = null;
	
	/**
	 * An int value that tells a GridObject's vertical location on the grid. Another way
	 * of looking at it: the y value on a Cartesian plane.
	 */
	private int row;

	/**
	 * An int value that tells a GridObject's horizontal location on the grid. Another way
	 * of looking at it: the x value on a Cartesian plane.
	 */
	private int column;
	
	/**
	 * Boolean to determine if this GridObject is visible to the player, a value of true
	 * will make this GridObject represent [M] such that M is this GridObject's mark on the grid,
	 * and [X] for a value of false.
	 */
	private boolean isVisible;
	
	/**
	 * Mark that represents this object's mark on the grid.
	 */
	protected char mark;
	
	/**
	 * An enumerated type variable that is of specific values: PLAYER, ENEMY, ROOM, ITEM.
	 * This is used for later grid scanning/object representation.
	 */
	protected static enum ObjectType{DEFAULT, PLAYER, ENEMY, ROOM, ITEM};
	
	/**
	 * An ObjectType value representing the specific type of GridObject that a GridObject is,
	 * which... is... huh. That's kinda weird. Well, let's make it DEFAULT. This is used for
	 * later grid scanning in subclasses.
	 */
	protected ObjectType secret = ObjectType.DEFAULT;						
	
	/**
	 * The main constructor for the class, which accepts two int values for parameters.
	 * This decides the initial starting location for a GridObject.
	 * @param col is the to-be-stored column value of the GridObject.
	 * @param row is the to-be-stored row value of the GridObject.
	 */
	public GridObject(int row, int col)
	{
		column = col;
		this.row = row;
		isVisible = false;
		mark = ' ';
	}
	
	public GridObject getStored()
	{
		return storedObj;
	}
	
	public void storeObject(GridObject passOver)
	{
		storedObj = passOver;
	}
	
	public void removeStored()
	{
		storedObj = null;
	}
	
	public boolean isStorageEmpty()
	{
		if(storedObj == null)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/**
	 * A simple method that returns the int value stored in the column variable.
	 */
	public int getColumn()
	{
		return column;
	}
	
	/**
	 * A simple method that returns the int value stored in the row variable.
	 */
	public int getRow()
	{
		return row;
	}
	
	/**
	 * A method that accepts two int values as parameters, and sets them to the new column and row values.
	 * This method is called after a GridObject has been moved on the grid, so a GridObject's coordinates
	 * actually match its position.
	 * @param newCol is the new column value to be stored.
	 * @param newRow is the new row value to be stored.
	 */
	public void changePosition(int newRow, int newCol)
	{
		row = newRow;
		column = newCol;
	}
	
	/**
	 * A simple method that returns false always, confirming this is, in fact, not a room. This is
	 * used for grid scans later on in the Engine and Grid classes.
	 */
	public boolean isARoom()
	{
		return false;
	}
	
	/**
	 * A simple method that returns false always, confirming this is, in fact, not an item. This is
	 * used for grid scans later on in the Engine and Grid classes.
	 */
	public boolean isAnItem()
	{
		return false;
	}
	
	public boolean isAnEnemy()
	{
		return false;
	}
	
	public boolean isAPlayer()
	{
		return false;
	}
	
	/**
	 * A simple method that returns the ObjectType value stored in the secret variable. 
	 */
	public ObjectType getObjectType()
	{
		return secret;
	}
	
	public String getType()
	{
		return "None";
	}
	
	public int getRoomNumber()
	{
		return 0;
	}
	
	/**
	 * This method sets whether or not this GridObject is visible on the grid to the player.
	 * @param visibility a true value will set the object to be visible on the grid, false will make it not visible
	 */
	public void setVisibility(boolean visibility)
	{
		isVisible = visibility;
	}
	
	/**
	 * This method returns this GridObject's mark as it should be displayed on the grid.
	 * Format would be: [M] such that M represents this GridObject's mark.
	 * @return a string containing the mark this GridObject has on the grid
	 */
	public String getMark()
	{	
		if(isVisible)
			return "[" + mark + "]";
		else
			return "[X]";
	}
}
