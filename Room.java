package edu.cpp.cs.cs141.FinalProject;

/**
 * This class represents the nine special rooms that are scattered throughout the map.
 * Each room has a number, and a boolean determining whether or not this particular room
 * contains the end-goal briefcase. This class extends from the GridObject class, and can
 * be stored within the Grid, with the constructor determining their starting location.
 * @author Team 404
 */
public class Room extends GridObject
{
	/**
	 * An int value representing the room's number, which is set by the Engine when the game
	 * begins. The number ranges anywhere from 1 to 9, and is revealed to the player when the
	 * Radar item is picked up.
	 */
	private int number;
	
	/**
	 * A boolean value representing whether or not this particular room contains the game-winning
	 * briefcase. The Engine decides which one contains the briefcase randomly at the start of the
	 * game.
	 */
	private boolean containsBriefcase = false;
	
	/**
	 * The class constructor, which accepts three int values as parameters. These are the room's number,
	 * and starting coordinates.
	 * @param number is the room's number. It cannot be changed.
	 * @param col is the room's column position. It can be changed with the superclass method.
	 * @param row is the room's row position. It can be changed with the superclass method.
	 */	
	public Room(int number, int row, int col)
	{
		super(row, col);
		this.number = number;
		secret = ObjectType.ROOM;
		mark = Integer.toString(number).charAt(0);
		super.setVisibility(true);
	}
	
	/**
	 * A simple method to return the stored int value in the number variable.
	 */
	public int getRoomNumber()
	{
		return number;
	}
	
	/**
	 * A simple method to return the stored boolean value in the containsBriefcase variable.
	 */
	public boolean containsBriefcase()
	{
		return containsBriefcase;
	}
	
	/**
	 * A method that allows the user (specifically the Engine) to place the briefcase in a particular
	 * room. This simply sets the boolean value of containsBriefcase to true.
	 */
	public void placeBriefcase()
	{
		containsBriefcase = true;
	}
	
	/**
	 * A simple method that returns true always, confirming this is, in fact, a room. This is
	 * used for grid scans later on in the Engine and Grid classes.
	 */
	public boolean isARoom()
	{
		return true;
	}
}
