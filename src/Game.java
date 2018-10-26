import java.util.ArrayList;
import java.util.Scanner;

public class Game {
	private int tickNumber, sun, totalZombies;
	private ArrayList<Sunflower> sunflower = new ArrayList<Sunflower>();
	private ArrayList<Zombie> Zombie = new ArrayList<Zombie>();
	private String input = "";

	/**
	 * 
	 */
	private Game() {
		sun = 0;
		totalZombies = 10; // might be changed
		tickNumber = 0;
		titleScreen();
	}

	/**
	 * @desc console allow players to input
	 * @author BeckZ
	 * 
	 * */
	private void titleScreen() {
		System.out.println("Welcome to SYSC3110 Group 10's PvZ, Console Vers.");

		Scanner console = new Scanner(System.in);
		boolean goodInput = false;
		
		while (!goodInput) {
			System.out.println("Enter \"play\" to play, and \"exit\" to quit.");
			input = console.nextLine();
			if (input.equals("play") || input.equals("exit")) {
				if(input.equals("exit")) {
					goodInput = false;
					console.close();
				}else {
					goodInput = true;
				}
				
			}
		}
	}

	/**
	 * @
	 *
	 */
	public void changePosition() {
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
	 * @desc this method will set the position for the sun flowers, consume 10 sun for each sun flower
	 * @author BeckZ
	 * @param x, the x position for the sun flower
	 * @param y, the y position for the sun flower
	 * @return null
	 */
	public void setflower(int x, int y) {
		if(sun>=10) {
			Sunflower flower = new Sunflower(x, y);
			if(sunflower.add(flower)) {
				System.out.println("You create an new sunflower.");
			}else {
				System.out.println("Unable to create a new sunflower!");
			}
		}else {
			sun = sun -10;
			return;
		}
		
		
	}

	/**
	 * 
	 */
	public static void main(String[] args) {
		new Game();
	}
}
