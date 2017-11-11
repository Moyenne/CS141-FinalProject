package edu.cpp.cs.cs141.FinalProject;

import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * This class represents all of the visuals and interactable portions of the game.
 * The player can input values, operate menus, save, load, get help, and see the game
 * and its functions here. Ain't that crazy!?
 * @author Team 404
 */
public class UI
{
	/**
	 * An Engine value that allows the game to function and maintain its normal processes
	 * and rules. Without this, no game. And the UI wouldn't even work. It would probably
	 * bug out to shit.
	 */
	private Engine engine;
	
	/**
	 * A Scanner value that allows the player to input their actions, influencing the game
	 * and receiving a response through the UI.
	 */
	private Scanner keyboard;
	
	/**
	 * The main constructor for the class, which accepts an Engine as a parameter, and then sets
	 * the engine and keyboard to their appropriate values.
	 */
	public UI(Engine engine)
	{
		this.engine = engine;
		keyboard = new Scanner(System.in);
	}
	
	/**
	 * A method to begin all game functions, starting with the start message, which leads into
	 * the main menu. The player can also instantly quit if they choose.
	 */
	public void startGame()
	{
		
	}
	
	/**
	 * A simple method that prints out a welcome message to the player when the game is first launched.
	 */
	public void printStartMessage()
	{
		
	}
	
	/**
	 * A simple method that prints out a help page when the player chooses to access the help "menu".
	 */
	public void printHelpMessage()
	{
		
	}
	
	/**
	 * A method that allows the player to choose how to progress: Start a new game, load a saved game,
	 * access the help menu, or quit the game. This is determined by an int input.
	 */
	public int mainMenu()
	{
		return 0;
	}
	
	/**
	 * A method that allows the player to either leave the save menu, or select a saved file to load.
	 */
	public int saveMenu()
	{
		return 0;
	}
	
	/**
	 * The main game loop, with many of the game's processes and functions repeating and being altered
	 * continuously until the game ends. From here, the player acts, taking their turns, and the enemy
	 * responds, taking their turns, until one proves victorious. Many of the player's actions are visualized,
	 * in particular with the printing of the grid image.
	 */
	public void gameLoop()
	{
		
	}
	
	/**
	 * This method should be invoked whenever an input is needed from the user.
	 * Provide an array of strings that consists of valid inputs, in which the input from the user
	 * must match one of the elements of the array, ignoring case.
	 * <p>
	 * This method also handles invalid inputs from the user, printing an appropriate error message, and
	 * will only return when a valid input is given.
	 * @param validInputs An array of strings that consists of all the inputs that would properly progress the game
	 * @return One of the strings from the given array, determined by the input from the user
	 */
	private String getInput(String[] validInputs)
	{
		StringTokenizer tokenizer;
		String input;
		
		do
		{
			try
			{
				tokenizer = new StringTokenizer(keyboard.nextLine());
				
				if(tokenizer.countTokens() == 0)							// true if the user entered an empty input,
					throw new Exception();									// in other words they just pressed [ENTER]
				
				input = checkInput(tokenizer.nextToken(), validInputs);
				return input;
			} catch(IllegalArgumentException iae) {
				System.err.println("Error! Please enter a valid input according to the options given.");
			} catch(Exception e) {
				System.err.println("Error! Please try again.");
			}
		} while(true);
	}
	
	/**
	 * This method checks whether the given {@code input} is valid, in other words it matches one of the strings
	 * in the given {@code validInputs} array, ignoring case.
	 * @param input The string to test for validity
	 * @param validInputs An array consisting of all the possible strings that are valid
	 * @return One of the strings in {@code validInputs}
	 * @throws IllegalArgumentException when {@code input} does not match any of the elements in {@code validInputs}.
	 */
	private String checkInput(String input, String[] validInputs) throws IllegalArgumentException
	{	
		for(String validInput : validInputs)
			if(input.equalsIgnoreCase(validInput))
				return validInput;
		
		throw new IllegalArgumentException();
	}
}
