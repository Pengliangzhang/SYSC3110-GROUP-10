import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Game {
	private int tickNumber, sun, totalZombies, zombieUnshowed;
	private ArrayList<Plant> plants = new ArrayList<Plant>();
	private ArrayList<Zombie> Zombie = new ArrayList<Zombie>();
	private String input = "";

	/**
	 * @desc Constractor for the game class
	 * @author BeckZ
	 */
	private Game() {
		sun = 50;
		totalZombies = 10; // might be changed
		zombieUnshowed = 10;
		tickNumber = 0;
		titleScreen();
	}

	/**
	 * @desc Title screen, the first thing showed when the game was run
	 * @author BeckZ, Kevin
	 */
	private void titleScreen() {
		System.out.println("Welcome to SYSC3110 Group 10's PvZ, Console Vers.");

		Scanner console = new Scanner(System.in);
		boolean goodInput = false;

		while (!goodInput) {
			System.out.println("Enter \"play\" to play, and \"exit\" to quit.");
			input = console.nextLine();
			if (input.equals("play") || input.equals("exit")) {
				if (input.equals("exit")) {
					goodInput = false;
					console.close();
					System.out.println("Thanks for playing our game again!");
				}
				goodInput = true;
				taketurn();
			}
		}
	}

	/**
	 * Take one turn, every turn has following step: 1. increase the sun 2. print
	 * the map 3. prompt user (drop a plant on the map or do nothing) 4. plants'
	 * actions (attack the zombie in their line) 5. check if user win this game 6.
	 * zombie spawn 7. zombies' actions (attack plants or move on) 8. check if user
	 * lost this game
	 */
	private void taketurn() {
		// increase the sun
		sun += 50;

		// Print the map
		printMap();

		// User turn
		userTurn();

		// Plant turn
		plantAction();

		// Check winner
		if (totalZombies == 0) {
			// Player win
			System.out.println("Player win");
			System.exit(0);
		}

		// Zombie turn
		zombieAction();

		// Check winner
		if (zombieCrossTheLine()) {
			// Zombie win
			System.out.println("Zombie win");
			System.exit(0);
		} else {
			tickNumber++;
			taketurn();
		}
	}

	/**
	 * Print the map to the user to show the position of all zombies and plants
	 */
	private void printMap() {
		for(Zombie zombie : Zombie) {
			if(zombie instanceof BasicZombie) {
				System.out.println("There have a zombie at (" + zombie.getX() + ' '+zombie.getY() + ")" + zombie.getHealth());
			}
		}
		for (Plant plant:plants) {
			if(plant instanceof Sunflower) {
				System.out.println("There have a sunflower at (" + plant.getX() + ' '+plant.getY() + ")");
			}else if(plant instanceof DamagePlant) {
				System.out.println("There have a damage-plant at (" + plant.getX() + ' '+plant.getY() + ")");
			}
		}
	}

	/**
	 * User action in this turn, user can select drop plant or pass the turn
	 */
	private void userTurn() {
		Scanner console = new Scanner(System.in);
		String input = "";
		while (true) {
			System.out.println("Enter the action at this turn (pass/drop):");
			input = console.nextLine();
			if (input.equals("pass") || input.equals("drop")) {
				break;
			}
		}
		if (input.equals("drop")) {
			while (true) {
				System.out.println("Enter the plant you wanna drop (sunflower/peashooter):");
				input = console.nextLine();
				if (input.equals("sunflower") || input.equals("peashooter")) {
					String pType = input;
					while (true) {
						System.out.println("Enter the place you wanna drop (row column):");
						String position = console.nextLine();
						int x = 0;
						int y = 0;
						String[] entity = position.split("\\s+");
						x = Integer.valueOf(entity[0]);
						y = Integer.valueOf(entity[1]);
						if(isEmpty(x, y)) {
							setplant(x, y, input);
						}
						break;
					}
					break;
				}
			}

		}
	}

	/**
	 * The action of the plants in this turn: generating sun, attacking zombies, or
	 * standing by
	 */
	private void plantAction() {
		for (Plant p : plants) {
			if (p instanceof DamagePlant) {
				Zombie firstZombie = null;
				for (Zombie z : Zombie) {
					if (p.getX() == z.getX() && (firstZombie == null || firstZombie.getY() > z.getY())) {
						firstZombie = z;
					}
				}
				if (firstZombie != null) {
					firstZombie.takeDamage(((DamagePlant) p).getDamage());
					if (firstZombie.getHealth() <= 0) {
						Zombie.remove(firstZombie);
						totalZombies--;
					}
				}
			} else if (p instanceof SunPlant) {
				sun += ((SunPlant) p).getSunTick();
			}
		}
	}

	/**
	 * @desc The method will check the position for sun-flower and zombie, if zombie
	 *       and sun-flow at same position, sun-flower get attack, otherwise zombie
	 *       move forward.
	 * @author OliverL, BeckZ
	 */
	private void zombieAction() {
		for (Zombie z : Zombie) {
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
		// Zombie spawn
		if (zombieUnshowed > 0) {
			Random rand = new Random();
			int n = rand.nextInt(5) + 1;
			if (tickNumber == 0) {
				Zombie z = new BasicZombie(n);
				Zombie.add(z);
				zombieUnshowed--;
			} else if ((tickNumber % 2) == 0) {
				Zombie z = new BasicZombie(n);
				Zombie.add(z);
				zombieUnshowed--;
			}
		}
	}

	/**
	 * Check whether the zombie(s) cross the whole line
	 * 
	 * @return True if at least one zombie cross the line, false otherwise
	 */
	private boolean zombieCrossTheLine() {
		for (Zombie z : Zombie) {
			if (z.getY() <= 0) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Check the entity in the map is empty (no plant in this coordinate)
	 * 
	 * @param row    The y of the entity
	 * @param column The x of the entity
	 * @return True if no plant is in that coordinate, false if this coordinate
	 *         already has plant or invalid
	 */
	private boolean isEmpty(int row, int column) {
		if (row<1 || row> 5 || column < 1 || column > 10) {
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
	 * @desc this method will set the position for the sun flowers, consume 10 sun
	 *       for each sun flower
	 * @author BeckZ
	 * @param x, the x position for the plant
	 * @param y, the y position for the plant
	 * @return null
	 */
	public void setplant(int x, int y, String type) {
		if (sun >= 25 && type.equals("sunflower")) {
			Sunflower plant = new Sunflower(x, y);
			if (plants.add(plant)) {
				sun = sun - plant.getSun();
				System.out.println("You created an new sunflower.");
				return;
			}
			System.out.println("Unable to create a new sunflower!");
		} else if (sun >= 50 && type.equals("peashooter")) {
			Peashooter plant = new Peashooter(x, y);
			if (plants.add(plant)) {
				sun = sun - plant.getSun();
				System.out.println("You create an new Peashooter.");
				return;
			}
			System.out.println("Unable to create a new Peashooter!");
		} else {
			System.out.println("You do not have enough sun!");
			return;
		}
	}

	/**
	 * Auto-generate method
	 */
	public static void main(String[] args) {
		Game game = new Game();
	}
}
