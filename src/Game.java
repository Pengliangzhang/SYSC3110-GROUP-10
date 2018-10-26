import java.util.ArrayList;
import java.util.Scanner;

public class Game {
	private int tickNumber, sun, totalZombies;
	private ArrayList<Plant> plants = new ArrayList<Plant>();
	private ArrayList<Zombie> Zombie = new ArrayList<Zombie>();
	private String input = "";

	/**
	 * Constractor for the game class
	 */
	private Game() {
		sun = 0;
		totalZombies = 10; // might be changed
		tickNumber = 0;
		titleScreen();
	}
	
	/**
	 * 
	 */
	private void titleScreen() {
		System.out.println("Welcome to SYSC3110 Group 10's PvZ, Console Vers.");

		Scanner console = new Scanner(System.in);
		boolean goodInput = false;

		while (!goodInput) {
			System.out.println("Enter \"play\" to play, and \"exit\" to quit.");
			input = console.nextLine();
			if (input.equals("play") || input.equals("exit")) {
				goodInput = true;
				console.close();
			}
		}
	}

	/**
	 * @
	 */
	public void changePosition() {
		for (Zombie zombie : Zombie) {
			// zombie.
		}
		if (input.equals("play"))

		{
			taketurn();
		} else if (input.equals("exit")) {
			System.exit(0);
		}
	};

	private void taketurn() {
		tickNumber += 10; // increase the sun
		// Print the map
		// Prompt user
		Scanner console = new Scanner(System.in);
		boolean goodInput = false;
		String input = "";
		while (!goodInput) {
			System.out.println("Enter the action at this turn (pass/drop):");
			input = console.nextLine();
			if (input.equals("pass") || input.equals("drop")) {
				goodInput = true;
			}
		}

		if (input.equals("drop")) {
			while (true) {
				System.out.println("Enter the plant you wanna drop (sunflower/peashooter):");
				input = console.nextLine();
				if (input.equals("sunflower") || input.equals("peashooter")) {
					String pType = input;
					int row = -1;
					int column = -1;
					while (true) {
						System.out.println("Enter the place you wanna drop (row column):");
						input = console.nextLine();
						String[] entity = input.split("\\s+");
						row = Integer.parseInt(entity[0]);
						column = Integer.parseInt(entity[1]);
						if (isEmpty(row, column)) {
							if (pType.equals("sunflower")) {
								plants.add(new Sunflower(column, row));
							} else {
								plants.add(new Peashooter(column, row));
							}
							break;
						}
					}
					console.close();
					break;
				}
			}
		}
	}

	private boolean zombieCrossTheLine() {
		for (Zombie z : Zombie) {
			if (z.getX() < 0) {
				return true;
			}
		}
		return false;
	}

	private boolean isEmpty(int row, int column) {
		if (row < 0 || row > 4 || column < 0 || column > 8) {
			return false;
		}
		for (Plant p : plants) {
			if (p.getX() == column && p.getY() == row) {
				return false;
			}
		}
		return true;
	}

	/**
	 * @ desc this method will set the position for the sun flowers
	 * 		  consume 10 sun for each sun flower
	 * 		  Push sun flower into end of plants list
	 * @author BeckZ
	 * @param x, the x position for the sun flower
	 * @param y, the y position for the sun flower
	 * @return null
	 */
	public void setflower(int x, int y) {
		if (sun >= 10) {
			Sunflower flower = new Sunflower(x, y);
			if (plants.add(flower)) {
				sun = sun - 10;
				System.out.println("You create an new sunflower.");
			} else {
				System.out.println("Unable to create a new sunflower!");
				return;
			}
		} else {
			return;
		}

	}

	/**
	 * 
	 */
	public static void main(String[] args) {

	}
}
