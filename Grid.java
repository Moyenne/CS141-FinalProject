package edu.cpp.cs.cs141.FinalProject;

import java.io.Serializable;
import java.util.Random;

import edu.cpp.cs.cs141.FinalProject.GridObject.ObjectType;

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
	 * An array that stores all of the Enemies that will have a corresponding object on the
	 * grid. This is used to keep track of stored objects and dead enemies.
	 */
	private Enemy[] enemies = new Enemy[6];
	
	//potentially delete
	private Item[] items = new Item[3];
	
	/**
	 * The main player character. Storing it here gives the Grid and Engine classes easy
	 * access to the Player's attributes.
	 */
	private Player player;
	
	/**
	 * An int value. This value is set within the roomSetup method, being equal to the random
	 * number generated to decide which room will contain the briefcase.
	 */
	private int winRoom;
	
	/**
	 * A boolean value. This value can be toggled using a key String (tdm404) when asked for an
	 * input by the UI, and its value determines the total visibility of the grid that is created
	 * by the getGrid method and printed by the UI.
	 */
	private boolean debugOn = false;
	
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
		
		for(int row = 0; row < 9; row++)
			for(int col = 0; col < 9; col++)
				if(grid[row][col] == null)
					grid[row][col] = new GridObject(row, col);
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
		winRoom = new Random().nextInt(9);
		temp[winRoom].placeBriefcase();
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
	 * as well as the fact that two GridObjects cannot occupy the same space. Enemies placed
	 * on the grid are also added to the enemies array.
	 */
	private void enemySetup()
	{
		int row;
		int col;
		int numOfEnemies = 0;
		Random rng = new Random();
		
		int n = rng.nextInt(2);						// This divides the possibility of the enemy being either within only the first six rows
		if(n == 0)									// or within only the last 6 columns, thus always being at least 3 squares away from the
		{											// player. When n == 0, the enemy will only spawn in a row < 6, when n == 1, the enemy
			while(numOfEnemies < 6)					// will only spawn in a column >= 3.
			{
				row = rng.nextInt(6);
				col = rng.nextInt(9);
				
				if(grid[row][col] != null)
					continue;
				else
				{
					//enemies[numOfEnemies] = new Enemy(row, col);
					//grid[row][col] = enemies[numOfEnemies];
					grid[row][col] = new Enemy(row, col);
					enemies[numOfEnemies] = (Enemy)grid[row][col];
					//enemies[numOfEnemies] = new Enemy(row, col);
					numOfEnemies++;
				}
			}
		}
		else
		{
			while(numOfEnemies < 6)
			{
				row = rng.nextInt(9);
				col = rng.nextInt(6) + 3;
				
				if(grid[row][col] != null)
					continue;
				else
				{
					//enemies[numOfEnemies] = new Enemy(row, col);
					//grid[row][col] = enemies[numOfEnemies];
					grid[row][col] = new Enemy(row, col);
					enemies[numOfEnemies] = (Enemy)grid[row][col];
					//enemies[numOfEnemies] = new Enemy(row, col);
					numOfEnemies++;
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
			if(getGridObject(row, col) == null)
			{
				positionGood = true;
				addGridObject(bullet, row, col);
				items[0] = new Bullet(row, col); //potentially delete
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
			if(getGridObject(row, col) == null)
			{
				positionGood = true;
				addGridObject(radar, row, col);
				items[1] = new Radar(row, col); //potentially delete
			}
			else
			{
				positionGood = false;
			}
		}
		Star star = new Star(0,0);
		//int col = 0;
		//int row = 7;
		positionGood = false;
		while(!positionGood)
		{
			int col = new Random().nextInt(9);
			int row = new Random().nextInt(9);
			if(getGridObject(row, col) == null)
			{
				positionGood = true;
				addGridObject(star, row, col);
				items[2] = new Star(row, col); //potentially delete
			}
			else
			{
				positionGood = false;
				//col = 1;
				//row = 8;
			}
		}
	}
	
	/**
	 * A convenience method that allows the user to add a specific GridObject to a specific
	 * location on the grid.
	 */
	public void addGridObject(GridObject gridObject, int row, int col)
	{
		grid[row][col] = gridObject;
	}
	
	/**
	 * A convenience method that allows the user to remove a GridObject from a specific
	 * location on the grid, assuming there was a GridObject at that location. A default
	 * GridObject is placed where the old GridObject is to be deleted, as there are never
	 * null values in the grid array.
	 */
	public void removeGridObject(int row, int col)
	{
		if(grid[row][col].isStorageEmpty())
		{
			grid[row][col] = new GridObject(row, col);
		}
		else
		{
			grid[row][col] = grid[row][col].getStored();
		}
	}
	
	/**
	 * A convenience method that allows the user to access a GridObject from a specific
	 * location on the grid, assuming there is a GridObject at that location.
	 */
	public GridObject getGridObject(int row, int col)
	{
		return grid[row][col];
	}
	
	/**
	 * A convenience method that allows the user to move a GridObject from a specific
	 * location on the grid to another location, assuming there is a GridObject at the
	 * initial location. The two GridObject locations are swapped, simulating movement.
	 */
	public void moveGridObject(int currentRow, int currentCol, int newRow, int newCol)
	{
		GridObject temp = grid[newRow][newCol];
		grid[newRow][newCol] = grid[currentRow][currentCol];
		grid[newRow][newCol].changePosition(newRow, newCol);
		grid[currentRow][currentCol] = temp;
		grid[currentRow][currentCol].changePosition(currentRow, currentCol);
	}
	
	/**
	 * A method that allows the user to move specifically the Player on the grid, which is
	 * restricted by single-space movement and unique item interaction. This prevents item
	 * duplication and other potential bugs.
	 */
	public void movePlayer(int currentRow, int currentCol, int newRow, int newCol)
	{
		GridObject temp = grid[newRow][newCol];
		grid[newRow][newCol] = grid[currentRow][currentCol];
		grid[newRow][newCol].changePosition(newRow, newCol);
		grid[currentRow][currentCol] = temp;
		grid[currentRow][currentCol].changePosition(currentRow, currentCol);
		if(grid[currentRow][currentCol].isAnItem())
		{
			grid[currentRow][currentCol] = new GridObject(currentRow, currentCol);
		}
	}
	
	/**
	 * A method that allows the user to move a specific enemy, decided by the enemy's number, on
	 * the grid, which is restricted by single-space movement and unique item interaction. This allows
	 * for enemies to pass over items, die on top of items, and more without creating potential bugs.
	 */
	public void moveEnemy(int enemyNumber, int newRow, int newCol)
	{
		Enemy enemy = enemies[enemyNumber];
		GridObject temp = grid[newRow][newCol];
		if(enemy.getStored() != null)
		{
			grid[newRow][newCol] = enemies[enemyNumber];
			grid[enemy.getRow()][enemy.getColumn()] = enemy.getStored();
			enemy.removeStored();
		}
		else
		{
			grid[newRow][newCol] = enemies[enemyNumber];
			grid[enemy.getRow()][enemy.getColumn()] = new GridObject(enemy.getRow(),enemy.getColumn());
		}
		if(temp.isAnItem())
		{
			enemies[enemyNumber].storeObject(temp);
		}
		enemy.changePosition(newRow, newCol);
	}
	
	/**
	 * A simple method that returns the enemies array for the Engine to access for appropriate use within
	 * the enemyTurn method.
	 */
	public Enemy[] getEnemyList()
	{
		return enemies;
	}
	
	/**
	 * A simple method that takes an int parameter, and returns the specific Enemy object in the enemies
	 * array that matches that int value, allowing for the Engine to access for appropriate use within
	 * the enemyTurn method.
	 */
	public Enemy getEnemy(int enemyNumber)
	{
		return enemies[enemyNumber];
	}
	
	//potentially delete
	public Item[] getItemList()
	{
		return items;
	}
	
	//potentially delete
	public Item getItem(int itemNumber)
	{
		return items[itemNumber];
	}
	
	/**
	 * A simple method that returns the Player class stored in the player variable.
	 */
	public Player getPlayer()
	{
		return player;
	}
	
	/**
	 * A simple method that returns the int value stored in the winRoom variable.
	 */
	public int getWinRoom()
	{
		return winRoom;
	}
	
	/**
	 * This method returns a string ready to be printed to the screen. 
	 * @return a string containing the whole grid
	 */
	public String getGrid()
	{
		if(debugOn)
		{
			for(int i = 0; i < 9; i++)
			{
				for(int j = 0; j < 9; j++)
				{
					grid[i][j].setVisibility(true);
				}
			}
		}
		else
		{
			setAdjacentVisible();
		}
		String output = "";
		for(int row = 0; row < 9; row++)
		{
			for(int col = 0; col < 9; col++)
			{
				if((debugOn || player.radarEnabled()) && grid[row][col].isARoom() && (grid[row][col].getRoomNumber() == winRoom))
				{
					output = output.concat("[*]");
				}
				else
				{
					output = output.concat(grid[row][col].getMark());
				}
			}
			output = output.concat("\n");
		}
		
		return output;
	}
	
	/**
	 * A method that takes coordinate parameters to set the visibility of an individual space on the grid to true.
	 */
	public void setPositionVisibility(int row, int col)
	{
		grid[row][col].setVisibility(true);
	}
	
	/**
	 * This method sets the blocks adjacent to the player to be visible.
	 * In other words, they simply do not appear on the grid as [X].
	 */
	private void setAdjacentVisible()
	{
		/* This if-else block determines if there are squares above and below the player square,
		 * and handles the adjacent squares' visibility if they exist.
		 */
		if(player.getRow() == 0)
			grid[player.getRow() + 1][player.getColumn()].setVisibility(true);
		else if(player.getRow() == 8)
			grid[player.getRow() - 1][player.getColumn()].setVisibility(true);
		else
		{
			grid[player.getRow() - 1][player.getColumn()].setVisibility(true);
			grid[player.getRow() + 1][player.getColumn()].setVisibility(true);
		}
		
		/* This if-else block determines if there are squares to the left and to the right of the player square,
		 * and handles the adjacent squares' visibility if they exist.
		 */
		if(player.getColumn() == 0)
			grid[player.getRow()][player.getColumn() + 1].setVisibility(true);
		else if(player.getColumn() == 8)
			grid[player.getRow()][player.getColumn() - 1].setVisibility(true);
		else
		{
			grid[player.getRow()][player.getColumn() - 1].setVisibility(true);
			grid[player.getRow()][player.getColumn() + 1].setVisibility(true);
		}
	}

	/**
	 * This method should be called whenever the grid needs to be reset in terms
	 * of visibility. Essentially, when the player takes any action that would advance
	 * a turn in the game, this method should be called in order to reset any visible
	 * squares on the grid during that turn.
	 */
	public void resetVisibility()
	{
		// Goes through the grid and sets the DEFAULT squares' visibility to false.
		for(int i = 0; i < 9; i++)
		{
			for(int j = 0; j < 9; j++)
			{
				if(grid[i][j].getObjectType() != ObjectType.ROOM && grid[i][j].getObjectType() != ObjectType.PLAYER)
				{
					grid[i][j].setVisibility(false);
				}
			}
		}
	}
	
	/**
	 * A method that toggles the current boolean value of debugOn, setting it to true when it is false, and false when
	 * it is true. Is called by the UI when the appropriate toggle string is entered. (Hint: it's tdm404)
	 */
	public void toggleDebug()
	{
		if(debugOn)
		{
			debugOn = false;
		}
		else
		{
			debugOn = true;
		}
	}
}
