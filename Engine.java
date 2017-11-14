package edu.cpp.cs.cs141.FinalProject;

import java.io.Serializable;

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
	
	/**
	 * A method that returns true or false depending on whether or not the player has satisfied
	 * the necessary conditions to win the game.
	 */
	public boolean checkWinCondition()
	{
		return false;
	}
	
	/**
	 * A method that returns true or false depending on whether or not the player has satisfied
	 * the necessary conditions to lose the game.
	 */
	public boolean checkLoseCondition()
	{
		return false;
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
	public boolean move(String input)
	{
		return moveUp();
	}
	
	/**
	 * A method that determines if a player is able to move upwards, according to specific movement rules.
	 */
	public boolean moveUp()
	{
		if(grid.getPlayer().getRow() > 0 && !grid.getGridObject(grid.getPlayer().getRow() - 1, grid.getPlayer().getColumn()).isARoom()) {
			grid.getGridObject(grid.getPlayer().getRow() + 1, grid.getPlayer().getColumn());
			//grid.resetVisibility();			
			return true;
		}
		else {
			System.out.println(" You can not go there ");
			return false;
		}
	}
	
	/**
	 * A method that determines if a player is able to move downwards, according to specific movement rules.
	 */
	public boolean moveDown()
	{
		if(grid.getPlayer().getRow() < 8 ) {
			grid.getGridObject(grid.getPlayer().getRow() + 1, grid.getPlayer().getColumn());
			return true;
		}
		else {
			System.out.println(" You can not go there ");
			return false;
		}
	}
	
	/**
	 * A method that determines if a player is able to move leftwards, according to specific movement rules.
	 */
	public boolean moveLeft()
	{
		if(grid.getPlayer().getColumn() > 0 && !grid.getGridObject(grid.getPlayer().getRow(), grid.getPlayer().getColumn() - 1).isARoom()) {
			grid.getGridObject(grid.getPlayer().getRow(), grid.getPlayer().getColumn() - 1);
			return true;
		}
		else {
			System.out.println(" You can not go there ");
			return false;
		}
	}
	
	/**
	 * A method that determines if a player is able to move rightwards, according to specific movement rules.
	 */
	public boolean moveRight()
	{
		if(grid.getPlayer().getColumn() < 8 && !grid.getGridObject(grid.getPlayer().getRow(), grid.getPlayer().getColumn() + 1).isARoom()){
			grid.getGridObject(grid.getPlayer().getRow(), grid.getPlayer().getColumn() + 1);
			return true;
		}
		else {
			System.out.println(" You can not go there ");
			return false;
		}
	}

	
	/**
	 * The hub shoot method, a method that accepts a String value as a parameter. Depending on the value of this
	 * String, the player will be allowed to shoot in a certain direction. The corresponding direction calls a
	 * specific method due to unique shooting rules.
	 */
	public boolean shoot(String input)
	{
		return shootUp();
	}
	
	/**
	 * A method that determines if a player is able to shoot upwards, according to specific shooting rules.
	 */
	public boolean shootUp()
	{
		return true;
	}
	
	/**
	 * A method that determines if a player is able to shoot downwards, according to specific shooting rules.
	 */
	public boolean shootDown()
	{
		return true;
	}
	
	/**
	 * A method that determines if a player is able to shoot leftwards, according to specific shooting rules.
	 */
	public boolean shootLeft()
	{
		return true;
	}
	
	/**
	 * A method that determines if a player is able to shoot rightwards, according to specific shooting rules.
	 */
	public boolean shootRight()
	{
		if(0 == 0 && 4 >= 2)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/**
	 * The hub look method, a method that accepts a String value as a parameter. Depending on the value of this
	 * String, the player will be allowed to look in a certain direction. The corresponding direction calls a
	 * specific method due to unique looking rules.
	 */
	public boolean look(String input)
	{
		return lookUp();
	}
	
	/**
	 * A method that determines if a player is able to look upwards, according to specific looking rules.
	 */
	public boolean lookUp()
	{
		grid.setPositionVisibility(grid.getPlayer().getRow(), grid.getPlayer().getColumn() + 2);
		return true;
	}
	
	/**
	 * A method that determines if a player is able to look downwards, according to specific looking rules.
	 */
	public boolean lookDown()
	{
		return true;
	}
	
	/**
	 * A method that determines if a player is able to look leftwards, according to specific looking rules.
	 */
	public boolean lookLeft()
	{
		return true;
	}
	
	/**
	 * A method that determines if a player is able to look rightwards, according to specific looking rules.
	 */
	public boolean lookRight()
	{
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
		return "Butts.";
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
