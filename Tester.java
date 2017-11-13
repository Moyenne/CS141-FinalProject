package edu.cpp.cs.cs141.FinalProject;

/**
 * This is a class to test the program. Delete this class once the project is finished.
 * @author Team 404
 *
 */
public class Tester {

	public static void main(String[] args) {
		Engine e = new Engine();
		Grid g = e.getGrid();
		g.toggleDebug();
		
		g.moveGridObject(g.getPlayer().getRow(), g.getPlayer().getColumn(), 6, 2);
		
		System.out.println(g.getGrid());
		System.out.println("The player should be able to move in all directions, according to the grid above.\n");
		
		System.out.println("moveUp() method returned: " + e.moveUp() + ", expecting: true");
		System.out.println("moveRight() method returned: " + e.moveRight() + ", expecting: true");
		System.out.println("moveDown() method returned: " + e.moveDown() + ", expecting: true");
		System.out.println("moveLeft() method returned: " + e.moveLeft() + ", expecting: true");
		
		g.moveGridObject(g.getPlayer().getRow(), g.getPlayer().getColumn(), 8, 1);
		System.out.println("\n" + g.getGrid());
		
		System.out.println("Now, according to the grid above, the player should only be able to move left or right.");
		System.out.println("Note that there may be exceptions so be sure to handle them and properly return false.\n");
		
		System.out.println("moveUp() method returned: " + e.moveUp() + ", expecting: false");
		System.out.println("moveRight() method returned: " + e.moveRight() + ", expecting: true");
		System.out.println("moveDown() method returned: " + e.moveDown() + ", expecting: false");
		System.out.println("moveLeft() method returned: " + e.moveLeft() + ", expecting: true");
	}
}
