package edu.cpp.cs.cs141.FinalProject;

import java.io.File;
import java.io.FileNotFoundException;
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
		printStartMessage();
	}
	
	/**
	 * A simple method that prints out a welcome message to the player when the game is first launched,
	 * as well as printing out the main menu, which allows the player to start the game, access the help
	 * menu, load a saved game, and exit.
	 */
	public void printStartMessage()
	{
		boolean atStart = true;
		ArrayList<String> initialOptions = new ArrayList<String>();
		initialOptions.add("start");
		initialOptions.add("help");
		initialOptions.add("load");
		initialOptions.add("exit");
		String input;
		
		do
		{
			System.out.println("Hi! \nPlease enter the option you would like to do:");
			displayOptions(initialOptions);
			input = getInput(initialOptions);
			
			switch(input)
			{
			case "start":
				atStart = false;
				break;								
			case "help":
				printHelpMessage();
				break;
			case "load":
				Grid temp = engine.getGrid();
				doLoadAction();
				if(engine.getGrid() != temp)		// if the player successfully loaded a game
				{
					atStart = false;
					break;
				}
				else
					break;
			case "exit":
				System.out.println("Goodbye! Take care.");
				System.exit(0);
				break;
			}
				
		} while(atStart);
		
		if (!atStart)
			gameLoop();
		
	}
	
	
	/**
	 * A simple method that prints out a help page when the player chooses to access the help "menu".
	 * This details the goal, rules, and other aspects of the game.
	 */
	public void printHelpMessage() 
	{
		System.out.println("\nWelcome! When you start the game you start in the bottom left of a nine space by nine space building. \n"
							+ "There are six incredibly deadly ninjas randomly positioned around the room at least three spaces away \n"
							+ "from your initial position patrolling the building. \n" + "\nThe building you are in is pitch black"
							+ " and there are nine rooms throughout the building. Your mission, if you choose to accept it,"
							+ "\nis to make your way to the briefcase located in one of the rooms. The rooms may only be entered from the north side.\n"
							+ "\nBeware, however, for if you move into a spot with a randomly patrolling ninja they will stab you and take one of your three lives."
							+ "\nIf you lose one of your lives, you respawn in the bottom left corner space again."
							+ "\n\nTo make sure you can make your way to the briefcase in one piece, you are equipped with night vision"
							+ "\ngoggles that allow you to see two spaces in any direction at the start of your turn.\n"
							+ "\nYou also may shoot your gun or move a space forward. If you shoot, any ninja struck with a bullet die instantly.\n"
							+ "\nAs you are only equipped with goggles and a single bullet, there are three power-ups placed around the building to aid you."
							+ "\nThere is another bullet that will top your gun off if it's empty, a radar that shows where the briefcase is,"
							+ "\nas well as an invincibility power-up that will render you invulnerable for five turns.\n"
							+ "\nIf you manage to reach the briefcase alive, you've won. \nGood luck!\n");

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
		ArrayList<String> modeOptions = new ArrayList<String>();
		modeOptions.add("normal");
		modeOptions.add("hard");
		
		System.out.println("Please choose your mode:");
		displayOptions(modeOptions);
		String mode = getInput(modeOptions);
		if(mode == "hard")
			engine.hardMode();
		
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
			case "check":
				doCheckAction();
				initialOptions.remove("check");
				break;
			case "move":
				doMoveAction();
				break;
			case "shoot":
				doShootAction();
				initialOptions.remove("shoot");
				break;
			case "save":
				doSaveAction();
				continue;
			case "load":
				doLoadAction();
				continue;
			case "quit":
				System.out.println("Thank you for playing!");
				return;
			case "tdm404":
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
			
			if(engine.canCheckRoom() && !initialOptions.contains("check"))			// Checks if a player is now on top of a room and
				initialOptions.add(1, "check");										// adds the "check" option if it does not exists in the list.
			if(!engine.canCheckRoom() && initialOptions.contains("check"))
			{
				initialOptions.remove("check");
			}
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
	
	private void doCheckAction() {
		if(!engine.roomHasBriefcase())
			System.out.println("This room is empty!");
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
		if(engine.lookLeft())
			dirOptions.add("left");
		if(engine.lookDown())
			dirOptions.add("down");
		if(engine.lookRight())
			dirOptions.add("right");
		
		System.out.println("Please enter the direction you would like to look:");
		displayOptions(dirOptions);
		input = getInput(dirOptions);
		
		engine.look(input);
	}

	/**
	 * This method implements the "move" action that the player can do.
	 * It first checks for all of the possible directions the player can move,
	 * then prompts the user for a valid choice and finally implements that
	 * action in the game engine.
	 */
	private void doMoveAction() {
		ArrayList<String> dirOptions = new ArrayList<String>();
		String input;
		
		if(engine.moveUp())
			dirOptions.add("up");
		if(engine.moveLeft())
			dirOptions.add("left");
		if(engine.moveDown())
			dirOptions.add("down");
		if(engine.moveRight())
			dirOptions.add("right");
		
		System.out.println("Please enter the direction you would like to move:");
		displayOptions(dirOptions);
		input = getInput(dirOptions);
		
		engine.move(input);
	}

	/**
	 * This method implements the "shoot" action that the player can do.
	 * It first checks for all of the possible directions the player can shoot,
	 * then prompts the user for a valid choice and finally implements that
	 * action in the game engine.
	 */
	private void doShootAction() {
		ArrayList<String> dirOptions = new ArrayList<String>();
		String input;
		
		if(engine.shootUp())
			dirOptions.add("up");
		if(engine.shootLeft())
			dirOptions.add("left");
		if(engine.shootDown())
			dirOptions.add("down");
		if(engine.shootRight())
			dirOptions.add("right");
		
		System.out.println("Please enter the direction you would like to shoot:");
		displayOptions(dirOptions);
		input = getInput(dirOptions);
		
		if(engine.shoot(input)) 
			System.out.println("Great! You killed an enemy.");
		else
			System.out.println("You missed. You'll need to find more ammo now.");	
	}

	private void doSaveAction() {
		System.out.print("Please enter a name for the file: ");
		String fileName = keyboard.nextLine();
		File target = new File(".\\savefiles\\" + fileName);
		if(!target.exists())
			Save.WriteToFile(engine.getGrid(), fileName);
		else
		{
			System.out.println("Would you like to overwrite the file: " + fileName + "?");
			ArrayList<String> options = new ArrayList<String>();
			options.add("yes");
			options.add("no");
			displayOptions(options);
			do
			{
				switch(getInput(options))
				{
				case "yes":
					Save.WriteToFile(engine.getGrid(), fileName);
					return;
				case "no":
					doSaveAction();
					return;
				default:			// only the case when user typed the debug input
					System.err.println("Error! Please enter a valid input according to the options given.");
					continue;
				}
			} while(true);
		}
		
	}

	private void doLoadAction() {
		File dir = new File("savefiles");
		File saveFilesDir = new File(".\\savefiles\\");
		File[] saveFiles = saveFilesDir.listFiles();
		String loadFileName;
		Boolean directoryHasFiles = true;
		

		if (!(new File("user.dir").exists())) {
			dir.mkdir();
		}
			
		if(saveFiles == null) {
			System.out.println("There are no save files to load from!\n");
			directoryHasFiles = false;
			return;
		}
		
		if(saveFiles.length == 0) {
			System.out.println("There are no save files to load from!\n");
			return;
		}
		
		ArrayList<String> loadOptions = new ArrayList<String>();
		
		for(File file : saveFiles)
			loadOptions.add(file.getName());
		System.out.println("Saved files:");
		displayOptions(loadOptions);
		
		do
		{
			System.out.println("Please enter the file name you want to load or enter \"cancel\" to cancel:");
			loadOptions.add("cancel");
			loadFileName = getInput(loadOptions);
			if(loadFileName.equals("cancel"))
				return;
			try {
				engine = new Engine(Save.readFromFile(loadFileName));
				return;
			} catch(FileNotFoundException fnfe) {
				System.err.println("Error: File not found.");
			} catch(Exception e) {
				System.err.println("Error: Please try again.");
			}
		} while(directoryHasFiles);
		
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
				if(input.equalsIgnoreCase("tdm404"))
					return "tdm404";
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
