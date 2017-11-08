package edu.cpp.cs.cs141.FinalProject;

import java.io.Serializable;
import java.util.Random;

/**
 * This class represents the visual grid that stores all GridObjects needed to play.
 * This class stores their locations, can change their locations, and sets up the initial
 * positions of all GridObjects at the start of the game.
 * @author Team 404
 */
public class Grid implements Serializable
{
	/**
	 * A 2D array. It stores all of the GridObjects in the game.
	 */
	private GridObject[][] grid = new GridObject[9][9];
	
	/**
	 * The main player character. Storing it here gives the Grid and Engine classes easy
	 * access to the Player's attributes.
	 */
	private Player player;
	
	/**
	 * The main constructor for the class, determining all initial conditions by calling
	 * the generalGridSetup method.
	 */
	public Grid()
	{
		generalGridSetup();
	}
	
	/**
	 * A method to determine the starting location and attributes of the Player, Enemies,
	 * Rooms, and Items (not in that order). This is done by calling other setup methods.
	 */
	private void generalGridSetup()
	{
		player = new Player(8, 0);
		addGridObject(player, 8, 0);
		roomSetup();
		enemySetup();
		itemSetup();
	}

	/**
	 * An annoyingly large method that sets up all of the rooms in their proper locations,
	 * as well as assigning one of the nine to contain the briefcase.
	 */
	private void roomSetup()
	{
		Room[] temp = new Room[9];
		Room room1 = new Room(1, 1, 1); temp[0] = room1;
		Room room2 = new Room(2, 1, 4); temp[1] = room2;
		Room room3 = new Room(3, 1, 7); temp[2] = room3;
		Room room4 = new Room(4, 4, 1); temp[3] = room4;
		Room room5 = new Room(5, 4, 4); temp[4] = room5;
		Room room6 = new Room(6, 4, 7); temp[5] = room6;
		Room room7 = new Room(7, 7, 1); temp[6] = room7;
		Room room8 = new Room(8, 7, 4); temp[7] = room8;
		Room room9 = new Room(9, 7, 7); temp[8] = room9;
		int x = new Random().nextInt(9);
		temp[x].placeBriefcase();
		addGridObject(temp[0], 1, 1);
		addGridObject(temp[1], 1, 4);
		addGridObject(temp[2], 1, 7);
		addGridObject(temp[3], 4, 1);
		addGridObject(temp[4], 4, 4);
		addGridObject(temp[5], 4, 7);
		addGridObject(temp[6], 7, 1);
		addGridObject(temp[7], 7, 4);
		addGridObject(temp[8], 7, 7);
	}
	
	/**
	 * A method that places six Enemies throughout the grid, according to inherent rules,
	 * as well as the fact that two GridObjects cannot occupy the same space.
	 */
	private void enemySetup()
	{
		for(int x = 0; x < 6; x++)
		{
			Enemy enemy = new Enemy(0,0);
			boolean positionGood = false;
			while(!positionGood)
			{
				int col = new Random().nextInt(9);
				int row = new Random().nextInt(9);
				int yes = 6+3;
				if(getGridObject(col, row) == null)
				{
					
					if(col >= (player.getColumn() - 3) && row <= (player.getRow() + 3))
					{
						positionGood = false;
					}
					else
					{
						positionGood = true;
						addGridObject(enemy, col, row);
					}
				}
				else
				{
					positionGood = false;
				}
			}
		}
	}

	/**
	 * A method that places three Items throughout the grid, according to inherent rules,
	 * as well as the fact that two GridObjects cannot occupy the same space.
	 */
	private void itemSetup()
	{
		Bullet bullet = new Bullet(0,0);
		boolean positionGood = false;
		while(!positionGood)
		{
			int col = new Random().nextInt(9);
			int row = new Random().nextInt(9);
			if(getGridObject(col, row) == null)
			{
				positionGood = true;
				addGridObject(bullet, col, row);
			}
			else
			{
				positionGood = false;
			}
		}
		Radar radar = new Radar(0,0);
		positionGood = false;
		while(!positionGood)
		{
			int col = new Random().nextInt(9);
			int row = new Random().nextInt(9);
			if(getGridObject(col, row) == null)
			{
				positionGood = true;
				addGridObject(radar, col, row);
			}
			else
			{
				positionGood = false;
			}
		}
		Star star = new Star(0,0);
		positionGood = false;
		while(!positionGood)
		{
			int col = new Random().nextInt(9);
			int row = new Random().nextInt(9);
			if(getGridObject(col, row) == null)
			{
				positionGood = true;
				addGridObject(star, col, row);
			}
			else
			{
				positionGood = false;
			}
		}
	}
	
	/**
	 * A convenient method that allows the user to add a specific GridObject to a specific
	 * location on the grid.
	 */
	public void addGridObject(GridObject gridObject, int col, int row)
	{
		grid[col][row] = gridObject;
		grid[col][row].changePosition(col, row);
	}
	
	/**
	 * A convenient method that allows the user to remove a GridObject from a specific
	 * location on the grid, assuming there was a GridObject at that location.
	 */
	public void removeGridObject(int col, int row)
	{
		grid[col][row] = null;
	}
	
	/**
	 * A convenient method that allows the user to access a GridObject from a specific
	 * location on the grid, assuming there is a GridObject at that location.
	 */
	public GridObject getGridObject(int col, int row)
	{
		return grid[col][row];
	}
	
	/**
	 * A convenient method that allows the user to move a GridObject from a specific
	 * location on the grid to another location, assuming there is a GridObject at the
	 * initial location.
	 */
	public void moveGridObject(int currentCol, int currentRow, int newCol, int newRow)
	{
		//if(grid[newXPos][newYPos] == null)
		//{
			grid[newCol][newRow] = grid[currentCol][currentRow];
			grid[newCol][newRow].changePosition(newCol, newRow);
			grid[currentCol][currentRow] = null;
		//}
	}
	
	/**
	 * A simple method that returns the Player class stored in the player variable.
	 */
	public Player getPlayer()
	{
		return player;
	}
	
	/**
	 * A method that reads the current contents of the grid array, and, depending on
	 * the contents each position, prints out a map of the grid detailing the location
	 * of all GridObjects.
	 */
	public void drawGrid()
	{
		System.out.println();
		for(int x = 0; x < 9; x++)
		{
			for(int y = 0; y < 9; y++)
			{
				GridObject obj = grid[x][y];
				if(obj == null && (x == player.getColumn() + 1) && (y == player.getRow()))
				{
					System.out.print("[ ]");
				}
				else if(obj == null && (x == player.getColumn() - 1) && (y == player.getRow()))
				{
					System.out.print("[ ]");
				}
				else if(obj == null && (x == player.getColumn()) && (y == player.getRow() + 1))
				{
					System.out.print("[ ]");
				}
				else if(obj == null && (x == player.getColumn()) && (y == player.getRow() - 1))
				{
					System.out.print("[ ]");
				}
				else if(obj == null)
				{
					System.out.print("[X]");
				}
				else if(obj.isARoom())
				{
					System.out.print("[" + obj.getRoomNumber() + "]");
				}
				else if(obj.isAnItem())
				{
					if(obj.getType().equals("Bullet"))
					{
						System.out.print("[B]");
					}
					else if(obj.getType().equals("Invincibility"))
					{
						System.out.print("[I]");
					}
					else if(obj.getType().equals("Radar"))
					{
						System.out.print("[R]");
					}
				}
				else if(obj.isAnEnemy())
				{
					System.out.print("[E]");
				}
				else if(obj.isAPlayer())
				{
					System.out.print("[P]");
				}
			}
			System.out.println();
		}
	}
}
