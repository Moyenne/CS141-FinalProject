package edu.cpp.cs.cs141.FinalProject;

import java.lang.Math;
import java.util.Random;

/**
 * This class represents the game's main internal engine, controlling all of the game's
 * processes and rules. The Engine initializes the grid, determines the state of the game,
 * actions that can be taken by the player, how enemies act, and how all of these different
 * GridObjects interact.
 * @author Team 404
 */
public class Engine
{
	/**
	 * A grid value, which instantiates all of the functions and contents of the Grid class.
	 * This gives the Engine the access it needs to alter the state of the grid.
	 */
	private Grid grid;
	
	/**
	 * A boolean value that determines if the game is still being played or not. Note: the game
	 * can be over, but not lost. The game can also be over, but not won. Victory is determined
	 * by the victory variable.
	 */
	private boolean gameOver;
	
	/**
	 * A boolean value that determines if the player has won or lost when the game is over. This
	 * value is constantly checked and updated by the checkWin and CheckLoseCondition methods.
	 */
	private boolean victory;
	
	/**
	 * A boolean value that determines if the game's Hard mode is active or not. This value is
	 * determined at the start of the game by the UI class.
	 */
	private boolean hardMode;
	
	/**
	 * The default constructor for the Engine, which is called during the initialization of the UI.
	 */
	public Engine() {
		grid = new Grid();
		gameOver = false;
		victory = false;
		hardMode = false;
	}
	
	/**
	 * The secondary constructor for the Engine, which is called during the loading of a saved file,
	 * which takes the file's saved Grid to correctly load all GridObject values.
	 */
	public Engine(Grid grid) {
		this.grid = grid;
		gameOver = false;
		victory = false;
		hardMode = false;
	}
	
	
	/**
	 * A simple method that returns the boolean value stored by the gameOver variable.
	 */
	public boolean gameOver()
	{
		return gameOver;
	}
	
	/**
	 * A simple method that is called to change the value of hardMode to true.
	 */
	public void hardMode() {
		hardMode = true;
	}
	
	/**
	 * A simple method that returns the boolean value stored by the victory variable.
	 */
	public boolean victorious()
	{
		return victory;
	}
	
	/**
	 * A method that returns true or false depending on whether or not the player has satisfied
	 * the necessary conditions to lose the game (losing all 3 lives).
	 */
	public boolean checkLoseCondition()
	{
		if(grid.getPlayer().getLifeCount() == 0)
		{
			gameOver = true;
			return gameOver();
		}
		else
		{
			return gameOver();
		}
	}
	
	/**
	 * The hub move method, a method that accepts a String value as a parameter. Depending on the value of this
	 * String, the player will be allowed to move in a certain direction. The corresponding directions call the
	 * movePlayer method with unique coordinates.
	 */
	public void move(String input)
	{
		input = input.toLowerCase();
		
			switch(input)
			{
				case "up":
					grid.movePlayer(grid.getPlayer().getRow(), grid.getPlayer().getColumn(), grid.getPlayer().getRow() - 1, grid.getPlayer().getColumn());
					break;
				case "right":
					grid.movePlayer(grid.getPlayer().getRow(), grid.getPlayer().getColumn(), grid.getPlayer().getRow(), grid.getPlayer().getColumn() + 1);
					break;
				case "down":
					grid.movePlayer(grid.getPlayer().getRow(), grid.getPlayer().getColumn(), grid.getPlayer().getRow() + 1, grid.getPlayer().getColumn());
					break;
				case "left":
					grid.movePlayer(grid.getPlayer().getRow(), grid.getPlayer().getColumn(), grid.getPlayer().getRow(), grid.getPlayer().getColumn() - 1);
		}
		grid.resetVisibility();
	}
	
	/**
	 * A method that determines if a player is able to move upwards, according to specific movement rules.
	 * This properly implements interactions with Items, Enemies, and Rooms, granting abilities, killing the
	 * player, and restricting movement respectively.
	 */
	 // modified by Fengyi Guo
	public boolean moveUp()
	{
		
		if(grid.getPlayer().getRow() > 0) {	
			GridObject objectUp = grid.getGridObject(grid.getPlayer().getRow() - 1, grid.getPlayer().getColumn());
			if(objectUp.isARoom()) {
				return false;
			}
			else if(objectUp.isAnEnemy()) {
				return false;
			}
			else if(objectUp.isAnItem()) {
				grid.getGridObject(grid.getPlayer().getRow() - 1, grid.getPlayer().getColumn());
				activatePowerUp((Item)objectUp);;
				//TODO
				return true;
			}
			else
			{
				grid.getGridObject(grid.getPlayer().getRow() - 1, grid.getPlayer().getColumn());
				//grid.resetVisibility();			
				return true;
			}
		}
		else {
			return false;// out of range
		}
	}
	
	/**
	 * A method that determines if a player is able to move downwards, according to specific movement rules.
	 * This properly implements interactions with Items, Enemies, and Rooms, granting abilities, killing the
	 * player, and restricting movement respectively.
	 */
	public boolean moveDown()
	{
		
		if(grid.getPlayer().getRow() < 8) {
			GridObject objectDown = grid.getGridObject(grid.getPlayer().getRow() + 1, grid.getPlayer().getColumn());
			if(objectDown.isARoom())
			{
				return false;
			}
			else if(objectDown.isAnEnemy()) {
				return false;
			}
			else if(objectDown.isAnItem()) {
				//TODO
				grid.getGridObject(grid.getPlayer().getRow() + 1, grid.getPlayer().getColumn());
				activatePowerUp((Item)objectDown);
				return true;
			}
			else {
				// player is able to move down
				grid.getGridObject(grid.getPlayer().getRow() + 1, grid.getPlayer().getColumn());
				//grid.resetVisibility();	
				return true;
			}			
		}
		else {
			return false;// out of range
		}		
	}
	
	/**
	 * A method that determines if a player is able to move leftwards, according to specific movement rules.
	 * This properly implements interactions with Items, Enemies, and Rooms, granting abilities, killing the
	 * player, and restricting movement respectively.
	 */
	public boolean moveLeft()
	{
	
		if(grid.getPlayer().getColumn() > 0) {
			GridObject objectLeft = grid.getGridObject(grid.getPlayer().getRow(), grid.getPlayer().getColumn() - 1);
			if(objectLeft.isARoom()) {
				return false;
			}
			else if(objectLeft.isAnEnemy()) {
				return false;
			}
			else if(objectLeft.isAnItem()) {
				grid.getGridObject(grid.getPlayer().getRow(), grid.getPlayer().getColumn() - 1);
				activatePowerUp((Item)objectLeft);
				//TODO
				return true;
			}
			else {
				grid.getGridObject(grid.getPlayer().getRow(), grid.getPlayer().getColumn() - 1);
				//grid.resetVisibility();			
				return true;
			}
		}
		else {
			return false;// out of range
		}
	}
	
	/**
	 * A method that determines if a player is able to move rightwards, according to specific movement rules.
	 * This properly implements interactions with Items, Enemies, and Rooms, granting abilities, killing the
	 * player, and restricting movement respectively.
	 */
	public boolean moveRight()
	{
		
		if(grid.getPlayer().getColumn() < 8) {
			GridObject objectRight = grid.getGridObject(grid.getPlayer().getRow(), grid.getPlayer().getColumn() + 1);
			if(objectRight.isARoom()) {
				return false;
			}
			else if(objectRight.isAnEnemy()) {
				return false;
			}
			else if(objectRight.isAnItem()) {
				grid.getGridObject(grid.getPlayer().getRow(), grid.getPlayer().getColumn() + 1);
				activatePowerUp((Item)objectRight);
				//TODO
				return true;
			}
			else {
				grid.getGridObject(grid.getPlayer().getRow(), grid.getPlayer().getColumn() + 1);
				//grid.resetVisibility();			
				return true;
			}
		}
		else {
			return false; // out of range
		}
	}
	
	
	/**
	 * A simple method that makes sure that the player actually has a bullet to shoot with. Created to simplify repeated
	 * method calls in the various shoot methods.
	 * @return
	 */
	public boolean checkBullet() {							//check the bullet before shoot in the UI
		if(grid.getPlayer().hasBullet())
			return true;
		return false;
	}
	
	/**
	 * The hub shoot method, a method that accepts a String value as a parameter. Depending on the value of this
	 * String, the player will be allowed to shoot in a certain direction. Rules are checked beforehand by the
	 * individual shootDirection methods, as well as being checked whenever this method is called directly.
	 */
	public boolean shoot(String input)
	{	
		grid.getPlayer().shoot();
		input = input.toLowerCase();
		
		int row = grid.getPlayer().getRow();						
		int col = grid.getPlayer().getColumn();
		
		if(input=="up")
			do
			{
				row--;
				if(grid.getGridObject(row,col).isARoom()) {				//block by the room
					grid.resetVisibility();
					return false;
				}
				if(grid.getGridObject(row,col).isAnEnemy()) {			//kill the first enemy in the line
					enemyIsKilled(row, col);
					grid.resetVisibility();
					return true;
				}
			}while(row > 0);
		
		else if(input=="down")
			do
			{
				row++;
				if(grid.getGridObject(row,col).isARoom()) {				//block by the room
					grid.resetVisibility();
					return false;
				}
				if(grid.getGridObject(row,col).isAnEnemy()) {			//kill the first enemy in the line
					enemyIsKilled(row, col);
					grid.resetVisibility();
					return true;
				}
			}while(row < 8);
		
		else if(input=="right")
			do
			{
				col++;
				if(grid.getGridObject(row,col).isARoom()) {				//block by the room
					grid.resetVisibility();
					return false;
				}
				if(grid.getGridObject(row,col).isAnEnemy()) {			//kill the first enemy in the line
					enemyIsKilled(row, col);
					grid.resetVisibility();
					return true;
				}
			}while(col < 8);
		
		else if(input=="left")
			do
			{
				col--;
				if(grid.getGridObject(row,col).isARoom()) {				//block by the room
					grid.resetVisibility();
					return false;
				}
				if(grid.getGridObject(row,col).isAnEnemy()) {			//kill the first enemy in the line
					enemyIsKilled(row, col);
					grid.resetVisibility();
					return true;
				}
			}while(col > 0);
		grid.resetVisibility();
		return false;
	}
	
	/**
	 * A method that determines if a player is able to shoot upwards, according to specific shooting rules.
	 * This determines if the player hits an enemy or not, returning false on a miss, and true on a hit.
	 */
	public boolean shootUp()
	{	
		int row = grid.getPlayer().getRow();						
		int col = grid.getPlayer().getColumn();					
				
		if(row == 0 || grid.getGridObject(row-1,col).isARoom())			//check the above square of the spy
			return false;												//Spy cannot shoot to the wall							
		
		return true;
	}

	/**
	 * A method that determines if a player is able to shoot downwards, according to specific shooting rules.
	 * This determines if the player hits an enemy or not, returning false on a miss, and true on a hit.
	 */
	public boolean shootDown()
	{
		int row = grid.getPlayer().getRow();						
		int col = grid.getPlayer().getColumn();					
				
		if(row == 8 || grid.getGridObject(row+1,col).isARoom())			//check the bottom square of the spy
			return false;												//Spy cannot shoot to the wall							
		
		return true;
	}
	
	
	/**
	 * A method that determines if a player is able to shoot rightwards, according to specific shooting rules.
	 * This determines if the player hits an enemy or not, returning false on a miss, and true on a hit.
	 */
	public boolean shootRight()
	{
		int row = grid.getPlayer().getRow();						
		int col = grid.getPlayer().getColumn();					
				
		if(col == 8 || grid.getGridObject(row,col+1).isARoom())			//check the right square of the spy
			return false;												//Spy cannot shoot to the wall							
		
		return true;
	}
	
	
	/**
	 * A method that determines if a player is able to shoot leftwards, according to specific shooting rules.
	 * This determines if the player hits an enemy or not, returning false on a miss, and true on a hit.
	 */
	public boolean shootLeft()
	{	
		int row = grid.getPlayer().getRow();						
		int col = grid.getPlayer().getColumn();					
				
		if(col == 0 || grid.getGridObject(row,col-1).isARoom())			//check the left square of the spy
			return false;												//Spy cannot shoot to the wall							
		
		return true;
	}
	
	/**
	 * A method that calls a specific Enemy's die method, as well as completely removing that particular enemy
	 * from the grid.
	 */
	private void enemyIsKilled(int row, int col)								// modified by Dongri
	{
		Enemy dying = (Enemy)grid.getGridObject(row, col);
		dying.die();
		grid.removeGridObject(row, col);
	}

	
	/**
	 * This method implements the "look" action that can be performed by the player. It will properly
	 * make the second square in the designated direction visible.
	 * 
	 * <p>Please use this method after determining if the direction desired is valid. For example, before
	 * passing the argument "UP" use the method {@linkplain #lookUp()} and make sure it returns a true value,
	 * indicating it is safe to look in the up direction.
	 * 
	 * <p>Do note that the {@code direction} parameter must be of one of these string values: "up", "right"
	 * , "down", "left"; all being case-insensitive. Arguments with strings other than these four will have this
	 * method do nothing.
	 * @param direction Must be one of the following: "up", "right", "down", "left"; case-insensitive
	 */
	public void look(String direction)
	{
		direction = direction.toLowerCase();
		
		switch(direction)
		{
		case "up":
			grid.setPositionVisibility(grid.getPlayer().getRow() - 2, grid.getPlayer().getColumn());
			break;
		case "right":
			grid.setPositionVisibility(grid.getPlayer().getRow(), grid.getPlayer().getColumn() + 2);
			break;
		case "down":
			grid.setPositionVisibility(grid.getPlayer().getRow() + 2, grid.getPlayer().getColumn());
			break;
		case "left":
			grid.setPositionVisibility(grid.getPlayer().getRow(), grid.getPlayer().getColumn() - 2);
		}
	}
	
	/**
	 * A method that determines if a player is able to look upwards, according to specific looking rules.
	 */
	public boolean lookUp()
	{		
		if(grid.getPlayer().getRow() < 2)
			return false;
		
		GridObject targetSpot = grid.getGridObject(grid.getPlayer().getRow() - 2, grid.getPlayer().getColumn());
		if(targetSpot.isARoom())
			return false;
		else
			return true;
	}
	
	/**
	 * A method that determines if a player is able to look downwards, according to specific looking rules.
	 */
	public boolean lookDown()
	{
		if(grid.getPlayer().getRow() > 6)
			return false;
		
		GridObject targetSpot = grid.getGridObject(grid.getPlayer().getRow() + 2, grid.getPlayer().getColumn());
		if(targetSpot.isARoom())
			return false;
		else
			return true;
	}
	
	/**
	 * A method that determines if a player is able to look leftwards, according to specific looking rules.
	 */
	public boolean lookLeft()
	{
		if(grid.getPlayer().getColumn() < 2)
			return false;
		
		GridObject targetSpot = grid.getGridObject(grid.getPlayer().getRow(), grid.getPlayer().getColumn() - 2);
		if(targetSpot.isARoom())
			return false;
		else
			return true;
	}
	
	/**
	 * A method that determines if a player is able to look rightwards, according to specific looking rules.
	 */
	public boolean lookRight()
	{
		if(grid.getPlayer().getColumn() > 6)
			return false;
		
		GridObject targetSpot = grid.getGridObject(grid.getPlayer().getRow(), grid.getPlayer().getColumn() + 2);
		if(targetSpot.isARoom())
			return false;
		else
			return true;
	}
	
	/**
	 * A method that is called when the Player enters the space of an Item, which then has it's applyItemEffect
	 * method called before it is deleted, and the Player takes over its position.
	 */
	public void activatePowerUp(Item powerUp)
	{
		powerUp.applyItemEffect(grid.getPlayer());
	}
	
	/**
	 * A method that details the functions to be performed on the enemy turn. The enemy either moves randomly or
	 * kills the player. This applies to all enemies that are alive, which is checked for each enemy originally
	 * placed on the grid. Movement is decided randomly, and is checked to be valid by a number of failsafe cases.
	 * If the hardMode value equals true, then enemy movement is determined if the player is nearby.
	 */
	public void enemyTurn()
	{
		int col = grid.getPlayer().getColumn();
		int row = grid.getPlayer().getRow();
		int enemyNumber = 0;
		for(Enemy enemy : grid.getEnemyList())
		{
			if(enemy.isAlive())
			{
				int eRow=enemy.getRow();
				int eCol=enemy.getColumn();
				if ((col == eCol && (Math.abs(row-eRow)==1) || (row==eRow && (Math.abs(col-eCol)==1))))//is the player 1 space away? if so, attack.
				{
					if(!grid.getPlayer().isInvincible())//invincibility check
					{
						grid.getPlayer().decreaseLifeCount();
						if(grid.getPlayer().getLifeCount()==0)
							gameOver=true;
						grid.movePlayer(row,col,8,0);
					}
				}
				else
				{
					boolean fail0 = false;
					boolean fail1 = false;
					boolean fail2 = false;
					boolean fail3 = false;
					Random dir = new Random();
					boolean EnemyHasMoved=false;
					
					int pRow = grid.getPlayer().getRow();						
					int pCol = grid.getPlayer().getColumn();
					
					
					do
					{
						int direction = dir.nextInt(4);
						
						// start hard mode
						if (hardMode==true){						
							
							//check if player is same column
							if(eCol == pCol) 
								if(!thereIsARoomBetween(pCol)){							//check a room is blocked the sight of enemy
									if(eRow - pRow > 0 && fail2 == false)					//enemy is bottom of the player
										direction = 2;							//move enemy up
									if (eRow - pRow < 0 && fail0 == false)					//enemy is above of the player
										direction = 0;							//move enemy down	
								}
							
							//check if player is same row
							if(eRow == pRow) 
								if(!thereIsARoomBetween(pRow)){							//check a room is blocked the sight of enemy{
									if(eCol - pCol > 0 && fail3 == false)					//enemy is right of the player
										direction = 3;							//move enemy left
									if(eCol - pCol < 0 && fail1 == false)					//enemy is left of the player
										direction = 1;							//move enemy right
								}
						}
						
						switch (direction)
						{
							case 0://try to move enemy: row+1
								if(eRow<8)//out of bounds check
								{
									if (!(grid.getGridObject(eRow+1,eCol).isARoom()) && !(grid.getGridObject(eRow+1,eCol).isAnEnemy()))//enemies can't enter rooms.
									{
										grid.moveEnemy(enemyNumber,eRow+1,eCol);
										EnemyHasMoved=true;
									}								
								}
								fail0 = true;
								break;
							case 1://move enemy column+1
								if(eCol<8)
								{
									if (!(grid.getGridObject(eRow,eCol+1).isARoom()) && !(grid.getGridObject(eRow,eCol+1).isAnEnemy()))
									{
										grid.moveEnemy(enemyNumber,eRow,eCol+1);
										EnemyHasMoved=true;
									}
								}
								fail1 = true;
								break;
							case 2://move enemy row -1
								if(eRow>0)
								{
									if (!(grid.getGridObject(eRow-1,eCol).isARoom()) && !(grid.getGridObject(eRow-1,eCol).isAnEnemy()))
									{
										grid.moveEnemy(enemyNumber,eRow-1,eCol);
										EnemyHasMoved=true;
									}
								}
								fail2 = true;
								break;
							case 3://move enemy column -1
								if(eCol>0)
								{
									if(!(grid.getGridObject(eRow,eCol-1).isARoom()) && !(grid.getGridObject(eRow,eCol-1).isAnEnemy()))
									{
										grid.moveEnemy(enemyNumber,eRow,eCol-1);
										EnemyHasMoved=true;
									}
								}
								fail3 = true;
								break;
						}
						if(fail0 && fail1 && fail2 && fail3)
						{
							EnemyHasMoved = true;
						}
					}while(!EnemyHasMoved);
				}
			}
			enemyNumber++;
		}
	}
	
	/**
	 * A simple method that returns the status of whether a room is between enemy and player
	 */
	
	private boolean thereIsARoomBetween(int line) {
		if((line == 1 || line == 4 || line == 7))
			return true;
		return false;
	}
	
	/**
	 * A simple method that returns the total display created in the Grid class that details all GridObjects on the
	 * grid.
	 */
	public String displayGrid()
	{
		return grid.getGrid();
	}
	
	/**
	 * A simple method that returns a String detailing the player's items, lives, action options, menu options, and
	 * other useful information, such as which power-ups have been obtained.
	 */
	public String displayStats()
	{
		String stats = "Player Stats" + "\n------------";
		stats += "\nLife Count: " + grid.getPlayer().getLifeCount();
		if(grid.getPlayer().hasBullet())
		{
			stats += "\nAmmunition: 1";
		}
		else
		{
			stats += "\nAmmunition: 0";
		}
		if(grid.getPlayer().isInvincible())
		{
			stats += "\nTurns Invincibile Remaining: " + grid.getPlayer().getInvincibilityLength();
		}
		if(grid.getPlayer().radarEnabled())
		{
			stats += "\nBriefcase Location: Room " + grid.getWinRoom();
		}
		return stats;
	}
	
	/**
	 * A simple method that resets all field variables for the Engine class to their default values.
	 */
	public void reset()
	{
		grid = new Grid();
		gameOver = false;
		victory = false;
	}
	
	// This method returns the grid that is being used in this Engine class.
	// It might serve a purpose for debug mode later on, but it does make
	// the grid field being private redundant as any public class can call
	// this method and modify the grid itself; an example of shallow copying
	public Grid getGrid() {
		return grid;
	}

	/**
	 * Assuming there is a room below Player, this method will return true
	 * if that room contains the briefcase and it will properly alter the
	 * appropriate boolean fields to indicate the game is over and the player
	 * is victorious. Otherwise, it just returns false;
	 * @return True if the room below the player contains the briefcase, false otherwise
	 */
	public boolean roomHasBriefcase()
	{
		if(grid.getGridObject(grid.getPlayer().getRow() + 1, grid.getPlayer().getColumn()).getRoomNumber() == grid.getWinRoom())
		{
			victory = true;
			gameOver = true;
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * This method will determine if there is a room below Player, and it should
	 * return true if and only if a room exists below Player and that the room has not 
	 * been checked yet. Otherwise, it just returns false.
	 * 
	 * <p>Make sure this method can differentiate between rooms that have and have not been
	 * checked.
	 * @return True if there exists an unchecked room below Player, false if there is no room
	 * or if a room does exist, it has already been checked
	 */
	public boolean canCheckRoom()
	{
		if((grid.getPlayer().getRow() + 1) < 9)
		{
			if(grid.getGridObject(grid.getPlayer().getRow() + 1, grid.getPlayer().getColumn()).isARoom())
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		else
		{
			return false;
		}
	}
}
