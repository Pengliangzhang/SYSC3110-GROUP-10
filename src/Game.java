import java.util.ArrayList;
import java.util.Scanner;

public class Game {
	private int tickNumber, sun, totalZombies;
	private ArrayList<Plant> plants = new ArrayList<Plant>();
	private ArrayList<Zombie> Zombie = new ArrayList<Zombie>();
	private String input = "";

	/**
	 * @desc Constractor for the game class
	 */
	private Game() {
		sun = 0;
		totalZombies = 10; // might be changed
		tickNumber = 0;
		titleScreen();
	}
	
	/**
	 * @desc Title screen, the first thing showed when the game was run
	 * @author BeckZ, Kevin
	 */
	private void titleScreen()
	{
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
					System.out.println("Thanks for playing our game again!");
				}
				goodInput = true;
				taketurn();
			}
		}
	}

	/**
	 * @desc change the position for all zombies
	 * 
	 */
	public void changePosition() {
		for (Zombie zombie : Zombie) {
			int currY = zombie.getY();
			zombie.setY(currY +1);
		}
		if (input.equals("play"))

		{
			taketurn();
		} else if (input.equals("exit")) {
			System.exit(0);
		}
    }
    
	/**
	 * Take one turn, every turn has following step:
	 * 1. increase the sun
	 * 2. print the map
	 * 3. prompt user (drop a plant on the map or do nothing)
	 * 4. plants' actions (attack the zombie in their line)
	 * 5. check if user win this game
	 * 6. zombie spawn
	 * 7. zombies' actions (attack plants or move on)
	 * 8. check if user lost this game
	 */
    private void taketurn() 
    {
		// increase the sun
    	sun += 10; 
        
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
    	
    }
    
    /**
     * User action in this turn, user can select drop plant or pass the turn
     */
    private void userTurn() {
    	Scanner console = new Scanner(System.in);
		String input = "";
		while (true)
		{
			System.out.println("Enter the action at this turn (pass/drop):");
			input = console.nextLine();
			if (input.equals("pass") || input.equals("drop"))
			{
				break;
			}
		}
		
		if (input.equals("drop"))
		{
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
                            if (pType.equals("sunflower")){
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
    
    /**
     * The action of the plants in this turn: generating sun, attacking zombies, or standing by
     */
    private void plantAction() {
    	for (Plant p : plants) {
			if (p instanceof DamagePlant) {
				Zombie firstZombie = null;
				for (Zombie z : Zombie) {
					if (p.getY() == z.getY() && (firstZombie == null || firstZombie.getX() > z.getX())) {
						firstZombie = z;
					}
				}
				if (firstZombie != null) {
					firstZombie.takeDamage(((DamagePlant)p).getDamageTick());
					if (firstZombie.getHealth() <= 0) {
						Zombie.remove(firstZombie);
						totalZombies--;
					}
					break;
				}
			} else if (p instanceof SunPlant) {
				sun += ((SunPlant)p).getSunTick();
			}
		}
    }
    
    /**
     * The action of the zombies in this turn: spawning zombie, attacking plants, or moving forward
     */
    private void zombieAction() {
    	// Zombie spawn
        if (true) {
            // zombies.add(new BasicZombie(0));
        }
    	
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
            	z.setX(z.getX() - z.getMoveSpeed());
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
            if (z.getX() < 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check the entity in the map is empty (no plant in this coordinate)
     * 
     * @param row The y of the entity 
     * @param column The x of the entity 
     * @return True if no plant is in that coordinate, false if this coordinate already has plant or invalid
     */
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
	 * @desc this method will set the position for the sun flowers, consume 10 sun
	 *       for each sun flower
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
     * Auto-generate method
     */
	public static void main(String[] args)
	{
		Game game = new Game();
    }
}
