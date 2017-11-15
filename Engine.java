package edu.cpp.cs.cs141.FinalProject;

import java.io.Serializable;
import java.lang.Math;
import java.util.Random;

/**
 * This class represents the game's main internal engine, controlling all of the game's
 * processes and rules. The Engine initializes the grid, determines the state of the game,
 * actions that can be taken by the player, how enemies act, and how all of these different
 * GridObjects interact.
 * @author Team 404
 */
public class Engine implements Serializable
{
	/**
	 * A grid value, which instantiates all of the functions and contents of the Grid class.
	 * This gives the Engine the access it needs to alter the state of the grid.
	 */
	private Grid grid = new Grid();
	
	/**
	 * A boolean value that determines if the game is still being played or not. Note: the game
	 * can be over, but not lost. The game can also be over, but not won. Victory is determined
	 * by the victory variable.
	 */
	private boolean gameOver = false;
	
	/**
	 * A boolean value that determines if the player has won or lost when the game is over. This
	 * value is constantly checked and updated by the checkWin and CheckLoseCondition methods.
	 */
	private boolean victory = false;
	
	/**
	 * A boolean value that determines if it is the player's turn to take an action. If this is false,
	 * then the enemies take their turn automatically.
	 */
	private boolean isPlayerTurn = true;
	
	/**
	 * A simple method that returns the boolean value stored by the gameOver variable.
	 */
	public boolean gameOver()
	{
		return gameOver;
	}
	
	/**
	 * A simple method that returns the boolean value stored by the victory variable.
	 */
	public boolean victorious()
	{
		return victory;
	}
	
	/**
	 * A simple method that returns the boolean value stored by the isPlayerTurn variable.
	 */
	public boolean isPlayerTurn()
	{
		return isPlayerTurn;
	}
	
	public void toggleTurn()
	{
		if(isPlayerTurn)
		{
			isPlayerTurn = false;
		}
		else
		{
			isPlayerTurn = true;
		}
	}
	
	/**
	 * A method that returns true or false depending on whether or not the player has satisfied
	 * the necessary conditions to lose the game.
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
	 * A method that accepts a String value as a parameter. Depending on the value of this String,
	 * the player will be allowed to do one of the following: move, shoot, or look. A failure to meet
	 * any of these three values will result in an error message. A boolean value is returned depending
	 * on if there was a success or failure with the player's choice.
	 */
	public boolean playerTurn(String input)
	{
		//Depending on player choice to move, shoot, look, has to input another value
		return checkInput(input);
	}
	
	/**
	 * A method that accepts a String as a parameter. This String is compared to nuerous pre-determined
	 * Strings to make sure the player has input a valid choice or not. A boolean value is returned depending
	 * on the result.
	 */
	public boolean checkInput(String input)
	{
		return true;
	}
	
	/**
	 * The hub move method, a method that accepts a String value as a parameter. Depending on the value of this
	 * String, the player will be allowed to move in a certain direction. The corresponding direction calls a
	 * specific method due to unique movement rules.
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
	}
	
	/**
	 * A method that determines if a player is able to move upwards, according to specific movement rules.
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
	 */
	public boolean moveDown()
	{
		
		if(grid.getPlayer().getRow() < 8) {
			GridObject objectDown = grid.getGridObject(grid.getPlayer().getRow() + 1, grid.getPlayer().getColumn());
			if(objectDown.isARoom()) {
				if(objectDown.getRoomNumber() == grid.getWinRoom())
				{
					victory = true;
					gameOver = true;
					return true;
				}
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
	
	
	// modified by Dongri Zhu
	public boolean checkBullet() {							//check the bullet before shoot in the UI
		if(grid.getPlayer().hasBullet())
			return true;
		return false;
	}
	
	/**
	 * The hub shoot method, a method that accepts a String value as a parameter. Depending on the value of this
	 * String, the player will be allowed to shoot in a certain direction. The corresponding direction calls a
	 * specific method due to unique shooting rules.
	 */
	public boolean shoot(String input)
	{	
		grid.getPlayer().shoot();
		input = input.toLowerCase();
		
		int row = grid.getPlayer().getRow();						//start from the above square of the spy
		int col = grid.getPlayer().getColumn();
		
		if(input=="up")
			do
			{
				row--;
				if(grid.getGridObject(row,col).isARoom())				//block by the room
					return false;
				
				if(grid.getGridObject(row,col).isAnEnemy()) {			//kill the first enemy in the line
					enemyIsKilled(row, col);
					return true;
				}
			}while(row > 0);
		
		else if(input=="down")
			do
			{
				row++;
				if(grid.getGridObject(row,col).isARoom())				//block by the room
					return false;
				
				if(grid.getGridObject(row,col).isAnEnemy()) {			//kill the first enemy in the line
					enemyIsKilled(row, col);
					return true;
				}
			}while(row < 8);
		
		else if(input=="right")
			do
			{
				col++;
				if(grid.getGridObject(row,col).isARoom())				//block by the room
					return false;
				
				if(grid.getGridObject(row,col).isAnEnemy()) {			//kill the first enemy in the line
					enemyIsKilled(row, col);
					return true;
				}
			}while(col < 8);
		
		else if(input=="right")
			do
			{
				col--;
				if(grid.getGridObject(row,col).isARoom())				//block by the room
					return false;
				
				if(grid.getGridObject(row,col).isAnEnemy()) {			//kill the first enemy in the line
					enemyIsKilled(row, col);
					return true;
				}
			}while(col > 0);
		
		return false;
	}
	
	/**
	 * A method that determines if a player is able to shoot upwards, according to specific shooting rules.
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
	 */
	public boolean shootLeft()
	{	
		int row = grid.getPlayer().getRow();						
		int col = grid.getPlayer().getColumn();					
				
		if(col == 0 || grid.getGridObject(row,col-1).isARoom())			//check the left square of the spy
			return false;												//Spy cannot shoot to the wall							
		
		return true;
	}
	
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
	 * kills the player. This applies to all enemies.
	 */
	public void enemyTurn()
	{
		int col = grid.getPlayer().getColumn();
		int row = grid.getPlayer().getRow();
		int enemyNumber = 0;
		for(GridObject enemy : grid.getEnemyList())
		{
			int erow=enemy.getRow();
			int ecol=enemy.getColumn();
			if ((col == ecol && (Math.abs(row-erow)==1) || (row==erow && (Math.abs(col-ecol)==1))))//is the player 1 space away? if so, attack.
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
				do
				{
					switch (dir.nextInt(4))
					{
						case 0://try to move enemy: row+1
							if(erow<8)//out of bounds check
							{
								if (!(grid.getGridObject(erow+1,ecol).isARoom()) && !(grid.getGridObject(erow+1,ecol).isAnEnemy()))//enemies can't enter rooms.
								{
									grid.moveEnemy(enemyNumber,erow+1,ecol);
									EnemyHasMoved=true;
								}								
							}
							fail0 = true;
							break;
						case 1://move enemy column+1
							if(ecol<8)
							{
								if (!(grid.getGridObject(erow,ecol+1).isARoom()) && !(grid.getGridObject(erow,ecol+1).isAnEnemy()))
								{
									grid.moveEnemy(enemyNumber,erow,ecol+1);
									EnemyHasMoved=true;
								}
							}
							fail1 = true;
							break;
						case 2://move enemy row -1
							if(erow<0)
							{
								if (!(grid.getGridObject(erow-1,ecol).isARoom()) && !(grid.getGridObject(erow-1,ecol).isAnEnemy()))
								{
									grid.moveEnemy(enemyNumber,erow-1,ecol);
									EnemyHasMoved=true;
								}
							}
							fail2 = true;
							break;
						case 3://move enemy column -1
							if(ecol<0)
							{
								if(!(grid.getGridObject(erow,ecol-1).isARoom()) && !(grid.getGridObject(erow,ecol-1).isAnEnemy()))
								{
									grid.moveEnemy(enemyNumber,erow,ecol-1);
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
			enemyNumber++;
		}
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
	 * other useful information.
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
		isPlayerTurn = true;
	}
	
	// This method returns the grid that is being used in this Engine class.
	// It might serve a purpose for debug mode later on, but it does make
	// the grid field being private redundant as any public class can call
	// this method and modify the grid itself; an example of shallow copying
	public Grid getGrid() {
		return grid;
	}
}
