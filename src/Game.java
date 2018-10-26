import java.util.ArrayList;
import java.util.Scanner;

public class Game {
	private int tickNumber, sun, totalZombies;
	private ArrayList<Sunflower> sunflower = new ArrayList<Sunflower>();
	private ArrayList<Zombie> zombieList = new ArrayList<Zombie>();

	/**
	 * 
	 */
	private Game() {
		sun = 0;
		totalZombies = 10; // might be changed
		tickNumber = 0;
		titleScreen();
	}

	private void titleScreen() {
		System.out.println("Welcome to SYSC3110 Group 10's PvZ, Console Vers.");

		Scanner console = new Scanner(System.in);
		boolean goodInput = false;
		String input = "";
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
	public void changePosition(Zombie curr) {
		for (Zombie zombie : Zombie) {
			if (input.equals("play")) {
				taketurn();
			} else if (input.equals("exit")) {
				System.exit(0);
			}

		}
	}

	@SuppressWarnings("unused")
	private void taketurn() {
		tickNumber += 10; // increase the sun

		// Prompt user

		// Plant turn

		// Check winner
		if (totalZombies == 0) {
			// Player win
		}

		// Zombie spawn

		// Zombie turn

		if (false) {
			// Zombie win
		} else {
			taketurn();
		}
	}
	
	/** 
	 * @desc this method will set the position for the sun flowers
	 * @author BeckZ
	 * @param x, the x position for the sun flower
	 * @param y, the y position for the sun flower
	 * @return null
	 */
	public void setflower(int x, int y) {
		Sunflower flower = new Sunflower(x, y);
		if(sunflower.add(flower)) {
			System.out.println("You create an new sunflower.");
		}else {
			System.out.println("Unable to create a new sunflower!");
		}
		
	}

	/**
	 * 
	 */
	public static void main(String[] args) {

	}
}
