package edu.cpp.cs.cs141.FinalProject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * This class represents the file saving and loading system for the game,
 * allowing the player to save their progress/position, and return to it
 * at another time. Multiple files can be saved and loaded.
 * @author Team 404
 */
public class Save
{
	/**
	 * A method to save a file, which saves the location and identity of all
	 * GridObjects on the grid.
	 */
	public static void WriteToFile(Grid grid, String saveFileName){
		try {
			File dir = new File("savefiles");

			if (!(new File("user.dir").exists())) {
				dir.mkdir();
			}
			FileOutputStream f = new FileOutputStream(new File(".\\savefiles\\" + saveFileName));
			ObjectOutputStream o = new ObjectOutputStream(f);
			
		o.writeObject(grid);
		o.close();
		f.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e) {
			System.out.println("Error initializing stream");
		}	
	}
	
	/**
	 * A method to load a file, which replaces all GridObjects that were on the
	 * grid back to their correct positions, with their correct internal values.
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	public static Grid readFromFile(String nameOfFile) throws IOException, ClassNotFoundException
	{
		FileInputStream fi = new FileInputStream(new File(".\\savefiles\\" + nameOfFile));
		ObjectInputStream oi = new ObjectInputStream(fi);
		
		Grid loadedGrid = (Grid)oi.readObject();
		fi.close();
		oi.close();
		return loadedGrid;
	}
}