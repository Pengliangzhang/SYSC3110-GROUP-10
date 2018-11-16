import java.util.ArrayList;
import java.util.Arrays;
import java.util.Observable;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JFrame;

/**
 * This class creates a text-based Plants vs Zombie game
 * 
 * @author BeckZ, Kevin, Xinrui Li, Bohua Cao
 * @version Oct 28, 2018
 */
public class Game extends Observable {
	
	private int tickCount, sun, totalZombies, remainingZombies;
	private ArrayList<Plant> plants;
	private ArrayList<Zombie> zombies;
	private JFrame jframe;

	/**
	 * Initializes the game.
	 * 
	 * @author BeckZ
	 */
	public Game() {
		plants = new ArrayList<Plant>();
		zombies = new ArrayList<Zombie>();
		
		//titleScreen();
	}
	
	public void newGame() {
		sun = 50;
		totalZombies = 10; // may be changed in the future
		remainingZombies = 10;
		tickCount = 0;
		plants.clear();
		zombies.clear();
	}

	/**
	 * Title "screen", prompts the user to start a game or exit.
	 * 
	 * @author BeckZ, Kevin
	 */
	private void titleScreen() {
		System.out.println("Welcome to SYSC3110 Group 10's PvZ, Console Vers.");

		// TODO closing the Scanner causes errors
		Scanner console = new Scanner(System.in);
		boolean goodInput = false;
		String input = "";

		while (!goodInput) {
			System.out.println("Enter \"play\" to play, and \"exit\" to quit.");
			input = console.nextLine();
			if (input.equals("play")) {
				// initialize the zombies, and give the player some sun to start off with
				sun = 50;
				totalZombies = 10; // may be changed in the future
				remainingZombies = 10;
				tickCount = 0;

				// console.close();

				// start Turn 1
				takeTurn();
			} else if (input.equals("exit")) {
				System.out.println("Thanks for playing our game!");
				System.exit(0);
			}
		}
	}

	/**
	 * Take one turn, every turn has following step: 1. increment sun by 25 (natural
	 * sun generation) 2. print the map 3. prompt user (drop a plant on the map or
	 * do nothing) 4. plants' actions (attack the zombie in their row) 5. check if
	 * user win this game 6. zombies spawn 7. zombies' actions (attack plants or
	 * move on) 8. check if user lost this game
	 * 
	 * @author Xinrui Li
	 */
	public int takeTurn() {
		// increase the sun
		sun += 25;

		// User turn
		// userTurn();

		// Plant turn
		plantAction();

		// Check for player win
		if (totalZombies == 0) {
			// Player win
			System.out.println("All zombies are eliminated.\nYou have won!");
			return 1;
			// System.exit(0);
		}

		// Zombie turn
		zombieAction();

		// Check for zombie win
		if (checkZombieWin()) {
			// Zombie win
			System.out.println("The zombies ate your brains!\nGame over.");
			// System.exit(0);
			return -1;
		} else {
			tickCount++;
			// Print the map
			printMap();
			return 0;
		}
	}

	/**
	 * Print the map to the user to show the position of all zombies and plants
	 * 
	 * @author Xinrui Li
	 */
	private void printMap() {
		// current amount of sun at the player's disposal
		System.out.println("Available Sun: " + sun);

		// plants that can be planted
		String s = "Available plants: ";
		if (sun >= 25) {
			s += "Sunflower ";
		}
		if (sun >= 30) {
			s += "Peashooter ";
		}
		System.out.println(s.trim());

		// text based representation of the board
		String[][] board = new String[5][10];
		for (int i = 0; i < 5; i++) {
			Arrays.fill(board[i], " ");
		}

		// print out the plants
		if (plants != null) {
			for (Plant plant : plants) {
				if (plant instanceof Sunflower) {
					board[plant.getX() - 1][plant.getY() - 1] = "s";
				} else if (plant instanceof Peashooter) {
					board[plant.getX() - 1][plant.getY() - 1] = "p";
				}
			}
		} else {
			System.out.println("You didn't place any plants.");
		}

		// print out the zombies
		if (zombies != null) {
			for (Zombie zombie : zombies) {
				if (zombie instanceof BasicZombie) {
					if (board[zombie.getX() - 1][zombie.getY() - 1] == " ") {
						board[zombie.getX() - 1][zombie.getY() - 1] = "z";
					} else {
						board[zombie.getX() - 1][zombie.getY() - 1] = board[zombie.getX() - 1][zombie.getY() - 1]
								+ "/z";
					}
				}
			}
		} else {
			System.out.println("There are no zombies on the map.");
		}

		for (int i = 0; i < 5; i++) {
			System.out.println(Arrays.toString(board[i]));
		}
	}

	/**
	 * User action in this turn, they can either drop a plant or pass the turn
	 * 
	 * @author Xinrui Li
	 */
	public boolean userTurn(int i, int j, int select) {
		if (isEmpty(i, j)) {
			if (select == 0) {
				return plantAPlant(i, j, "sunflower");
			} else if (select == 1) {
				return plantAPlant(i, j, "peashooter");
			}
		}
		return false;
		
//		// TODO closing the Scanner causes errors
//		Scanner console = new Scanner(System.in);
//		String input = "";
//		boolean goodInput = false;
//
//		while (!goodInput) {
//			System.out.println("\n\nWhat would you like to do? (pass/drop):");
//			input = console.nextLine().trim();
//			if (input.equals("pass")) {
//				// console.close();
//				return;
//			} else if (input.equals("drop")) {
//				goodInput = true;
//			}
//		}
//
//		goodInput = false;
//		while (!goodInput) {
//			System.out.println("Which plant do you want to drop? (sunflower/peashooter):");
//			input = console.nextLine().trim();
//
//			if (input.equals("sunflower") || input.equals("peashooter")) {
//				goodInput = true;
//			}
//		}
//
//		goodInput = false;
//		while (!goodInput) {
//			System.out.println("Enter the coordinates at which the plant should be placed. (row column):");
//			String position = console.nextLine();
//			String[] entity = position.split("\\s+");
//			if (entity.length == 2) {
//				int x = Integer.valueOf(entity[0]);
//				int y = Integer.valueOf(entity[1]);
//				if (isEmpty(x, y)) {
//					plantAPlant(x, y, input);
//					goodInput = true;
//					// console.close();
//					return;
//				} else {
//					System.out.println("That spot is already occupied.");
//				}
//			}
//		}
	}

	/**
	 * This method takes care of all plants' actions. The plants will generate sun,
	 * attack zombies in their row, or be on standby.
	 * 
	 * @author Xinrui Li
	 */
	private void plantAction() {
		for (Plant p : plants) {
			if (p instanceof DamagePlant) {
				Zombie firstZombie = null;
				for (Zombie z : zombies) {
					if (p.getX() == z.getX() && (firstZombie == null || firstZombie.getY() > z.getY())) {
						firstZombie = z;
					}
				}
				if (firstZombie != null) {
					firstZombie.takeDamage(((DamagePlant) p).getDamage());
					if (firstZombie.getHealth() <= 0) {
						zombies.remove(firstZombie);
						totalZombies--;
					}
				}
			} else if (p instanceof SunPlant) {
				sun += ((SunPlant) p).generateSun();
			}
		}
	}

	/**
	 * This method takes care of all zombies' actions. The zombies will attack, if
	 * there is a plant in front of it. Otherwise, they will advance.
	 * 
	 * @author Xinrui Li, BeckZ
	 */
	private void zombieAction() {
		for (Zombie z : zombies) {
			boolean action = false;
			for (Plant p : plants) {
				if (z.getX() == p.getX() && z.getY() == p.getY()) {
					p.takeDamage(z.getDamage());
					if (p.getHealth() <= 0) {
						plants.remove(p);
					}
					action = true;
					break;
				}
			}
			if (!action) {
				z.setY(z.getY() - z.getMoveSpeed());
			}
		}

		// Zombies spawn
		if (remainingZombies > 0) {
			Random rand = new Random();
			int n = rand.nextInt(5) + 1;
			if (tickCount == 0) {
				Zombie z = new BasicZombie(n);
				zombies.add(z);
				remainingZombies--;
			} else if ((tickCount % 2) == 0) {
				Zombie z = new BasicZombie(n);
				zombies.add(z);
				remainingZombies--;
			}
		}
	}
	

	/**
	 * Check for zombies that have arrived at the player's doorstep, which signals a
	 * zombie victory.
	 * 
	 * @author Xinrui Li
	 * @return true if at least one zombie has arrived at the player's doorstep,
	 *         false otherwise
	 */
	private boolean checkZombieWin() {
		for (Zombie z : zombies) {
			if (z.getY() <= 0) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Check if the position given by the coordinates is empty, so that a plant may
	 * be placed there.
	 * 
	 * @author Xinrui Li
	 * @param row
	 * @param column
	 * @return false if the coordinates are out of bounds or the position given by
	 *         the coordinates is taken, true otherwise
	 */
	private boolean isEmpty(int row, int column) {
		if (row < 1 || row > 5 || column < 1 || column > 10) {
			return false;
		}

		for (Plant p : plants) {
			if (p.getX() == row && p.getY() == column) {
				return false;
			}
		}
		return true;
	}

	/**
	 * This method will place the given plant at the given coordinates.
	 * 
	 * @author BeckZ
	 * @param x The column number for the plant
	 * @param y The row number for the plant
	 */
	public boolean plantAPlant(int x, int y, String type) {
		if (sun >= 25 && type.equals("sunflower")) {
			Sunflower plant = new Sunflower(x, y);
			if (plants.add(plant)) {
				sun = sun - plant.getSun();
				System.out.println("Sunflower placed at (" + x + ", " + y + ")");
				return true;
			}

			// TODO this should never happen, since we have already checked the required
			// conditions
			System.out.println("Unable to create a new sunflower!");
		} else if (sun >= 50 && type.equals("peashooter")) {
			Peashooter plant = new Peashooter(x, y);
			if (plants.add(plant)) {
				sun = sun - plant.getSunCost();
				System.out.println("Peashooter placed at (" + x + ", " + y + ")");
				return true;
			}

			// TODO again, this should never happen
			System.out.println("Unable to create a new Peashooter!");
		} else {
			// TODO should never happen, since we check the conditions before
			System.out.println("You do not have enough sun!");
		}
		return false;
	}
	
	/**
	 * @desc get how many suns in current game
	 * @return return number of sun
	 * */
	public int getSun() {
		return sun;
	}

//	public static void main(String[] args) {
//		Game game = new Game();
//	}
}
