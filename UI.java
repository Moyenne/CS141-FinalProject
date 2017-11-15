package edu.cpp.cs.cs141.FinalProject;

import java.util.ArrayList;
import java.util.Arrays;
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
	public UI()
	{
		engine = new Engine();
		keyboard = new Scanner(System.in);
	}
	
	/**
	 * A method to begin all game functions, starting with the start message, which leads into
	 * the main menu. The player can also instantly quit if they choose.
	 */
	public void startGame()
	{
		gameLoop();
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
		ArrayList<String> initialOptions = new ArrayList<String>();
		initialOptions.add("look");
		initialOptions.add("move");
		initialOptions.add("shoot");
		initialOptions.add("save");
		initialOptions.add("load");
		initialOptions.add("quit");
		String input;
		
		do
		{
			System.out.println(engine.displayGrid());
			System.out.println(engine.displayStats());
			System.out.println("Please enter the option you would like to do:");
			displayOptions(initialOptions);
			input = getInput(initialOptions);
			
			switch(input)
			{
			case "look":
				doLookAction();
				initialOptions.remove("look");			// Removes the first element in the list, which would be "look",
				continue;								// because the look action can only be done once per turn.
			case "move":
				doMoveAction();
				break;
			case "shoot":
				doShootAction();
				initialOptions.remove("shoot");
				break;
			case "save":
				// implement save
				break;
			case "load":
				// implement load
				break;
			case "quit":
				// implement quit
				break;
			case "TDM":
				engine.getGrid().toggleDebug();
				continue;
			}
			
			engine.enemyTurn();
			
			if(engine.getGrid().getPlayer().isInvincible())
			{
				engine.getGrid().getPlayer().decreaseInvincibility();
			}
			
			if(!initialOptions.contains("look"))									// This checks if "look" has been removed from the start of the list.
				initialOptions.add(0, "look");										// And properly replaces "look" back into the beginning of the list if it was removed.
			if(engine.checkBullet() && !initialOptions.contains("shoot"))			// True if the player has a bullet and the options list does not contain the "shoot" option.
				initialOptions.add(initialOptions.indexOf("move") + 1, "shoot");	// "shoot" will always be after the "move" option
		} while(!engine.gameOver());
		
		if(engine.victorious())
		{
			System.out.println("We have a winna!");
		}
		else
		{
			System.out.println("Phission mailed.");
		}
	}
	
	/**
	 * This method implements the "look" action that the player can do.
	 * It first checks for all of the possible directions the player can look,
	 * then prompts the user for a valid choice and finally implements that
	 * action in the game engine.
	 */
	private void doLookAction()
	{
		ArrayList<String> dirOptions = new ArrayList<String>();
		String input;
		
		if(engine.lookUp())
			dirOptions.add("up");
		if(engine.lookRight())
			dirOptions.add("right");
		if(engine.lookDown())
			dirOptions.add("down");
		if(engine.lookLeft())
			dirOptions.add("left");
		
		System.out.println("Please enter the direction you would like to look:");
		displayOptions(dirOptions);
		input = getInput(dirOptions);
		
		engine.look(input);
	}

	private void doMoveAction() {
		ArrayList<String> dirOptions = new ArrayList<String>();
		String input;
		
		if(engine.moveUp())
			dirOptions.add("up");
		if(engine.moveRight())
			dirOptions.add("right");
		if(engine.moveDown())
			dirOptions.add("down");
		if(engine.moveLeft())
			dirOptions.add("left");
		
		System.out.println("Please enter the direction you would like to move:");
		displayOptions(dirOptions);
		input = getInput(dirOptions);
		
		engine.move(input);
	}

	private void doShootAction() {
		ArrayList<String> dirOptions = new ArrayList<String>();
		String input;
		
		if(engine.shootUp())
			dirOptions.add("up");
		if(engine.shootRight())
			dirOptions.add("right");
		if(engine.shootDown())
			dirOptions.add("down");
		if(engine.shootLeft())
			dirOptions.add("left");
		
		System.out.println("Please enter the direction you would like to shoot:");
		displayOptions(dirOptions);
		input = getInput(dirOptions);
		
		if(engine.shoot(input)) 
			System.out.println("Great! You killed an enemy.");
		else
			System.out.println("You missed. You'll need to find more ammo now.");	
	}

	/**
	 * This method displays the options that are all in the ArrayList {@code listOfOptions} argument, in a numbered fashion.
	 * 
	 * <p>The format that is printed to the screen is: 1. [e1] 2. [e2] . . . n. [en] such that e1, e2, . . ., en
	 * is the nth element in {@code listOfOptions}.
	 * @param listOfOptions An ArrayList that consists of all the options to be displayed onto the screen
	 */
	private void displayOptions(ArrayList<String> listOfOptions)
	{
		for(int i = 0; i < listOfOptions.size(); i++)
			System.out.print((i + 1) + ". " + listOfOptions.get(i) + " ");
		System.out.println();
	}
	
	/**
	 * This method should be invoked whenever an input is needed from the user.
	 * Provide an ArrayList of strings that consists of valid inputs, ordered from the first option to last,
	 * in which the input from the user must match one of the elements of the ArrayList, ignoring case,
	 * <b>or</b> the user inputs a number that corresponds to the option's index in the ArrayList + 1.
	 * 
	 * <p>
	 * This method also handles invalid inputs from the user, printing an appropriate error message, and
	 * will only return when a valid input is given.
	 * @param options An ArrayList of strings that consists of all the inputs that would properly progress the game
	 * @return One of the strings from the given ArrayList, determined by the input from the user
	 */
	private String getInput(ArrayList<String> options)
	{
		StringTokenizer tokenizer;
		String input;
		int integerInput;
		String finalInput;
		
		do
		{
			try
			{
				System.out.print("> ");
				tokenizer = new StringTokenizer(keyboard.nextLine());
				
				if(tokenizer.countTokens() == 0)									// true if the user entered an empty input,
					throw new Exception();											// in other words they just pressed [ENTER]
				
				input = tokenizer.nextToken();
				if(input.equalsIgnoreCase("toggleDebugMode"))
					return "toggleDebugMode";
				if(input.length() == 1 && Character.isDigit(input.charAt(0)))		// this checks if the user has just entered
				{																	// the number of the option desired
					integerInput = Integer.parseInt(input);
					if(integerInput > 0 && integerInput < options.size() + 1)
						return options.get(integerInput - 1);
					else
						throw new IllegalArgumentException();
				}
				
				finalInput = checkInput(input, options);
				return finalInput;
			} catch(IllegalArgumentException iae) {
				System.err.println("Error! Please enter a valid input according to the options given.");
			} catch(Exception e) {
				System.err.println("Error! Please try again.");
			}
		} while(true);
	}
	
	/**
	 * This method checks whether the given {@code input} is valid, in other words it matches one of the strings
	 * in the given {@code options} ArrayList, ignoring case.
	 * @param input The string to test for validity
	 * @param options An ArrayList consisting of all the possible strings that are valid options
	 * @return One of the strings in {@code options}
	 * @throws IllegalArgumentException when {@code input} does not match any of the elements in {@code options}.
	 */
	private String checkInput(String input, ArrayList<String> options) throws IllegalArgumentException
	{	
		for(String validInput : options)
			if(input.equalsIgnoreCase(validInput))
				return validInput;
		
		throw new IllegalArgumentException();
	}
}
