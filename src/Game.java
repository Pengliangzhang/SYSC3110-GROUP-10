import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * This class create a text-based Plants vs Zombie game
 * @author BeckZ, Kevin, Xinrui Li, Bohua Cao
 * @version Oct 28, 2018
 */
public class Game {
	private int tickNumber, sun, totalZombies, zombieUnshowed;
	private ArrayList<Plant> plants = new ArrayList<Plant>();
	private ArrayList<Zombie> zombies = new ArrayList<Zombie>();
	private String input = "";

	/**
	 * @desc Constructor for the game class
	 * @author BeckZ
	 */
	private Game()
	{
		sun = 50;
		totalZombies = 10; // might be changed
		zombieUnshowed = 10;
		tickNumber = 0;
		titleScreen();
	}

	/**
	 * Title "screen", prompts the user to start a game or exit.
	 * 
	 * @author BeckZ, Kevin
	 */
	private void titleScreen()
	{
		System.out.println("Welcome to SYSC3110 Group 10's PvZ, Console Vers.");

		Scanner console = new Scanner(System.in);

		while (true)
		{
			System.out.println("Enter \"play\" to play, and \"exit\" to quit.");
			input = console.nextLine();
			if (input.equals("play"))
			{
				takeTurn();
			}
			else if (input.equals("exit"))
			{
				System.out.println("Thanks for playing our game!");
				System.exit(0);
			}
		}
	}

	/**
	 * Take one turn, every turn has following step: 
	 * 1. increase the sun 
	 * 2. print the map
	 * 3. prompt user (drop a plant on the map or do nothing)
	 * 4. plants' actions (attack the zombie in their row)
	 * 5. check if user win this game
	 * 6. zombies spawn
	 * 7. zombies' actions (attack plants or move on)
	 * 8. check if user lost this game
	 * 
	 * @author Xinrui Li
	 */
	private void takeTurn()
	{
		// increase the sun
		sun += 25;

		// Print the map
		printMap();

		// User turn
		userTurn();

		// Plant turn
		plantAction();

		// Check for player win
		if (totalZombies == 0)
		{
			// Player win
			System.out.println("Player win");
			System.exit(0);
		}

		// Zombie turn
		zombieAction();

		// Check for zombie win
		if (checkZombieWin())
		{
			// Zombie win
			System.out.println("Zombie win");
			System.exit(0);
		}
		else
		{
			tickNumber++;
			takeTurn();
		}
	}

	/**
	 * Print the map to the user to show the position of all zombies and plants
	 * 
	 * @author Xinrui Li
	 */
	private void printMap()
	{
		System.out.println("Sun: " + sun);
	    String s = "Available plants: ";
	    if (sun >= 25)
	    {
	    	 s += "Sunflower ";
	    }
	    if (sun >= 30)
	    {
	    	 s += "Peashooter ";
	    }
	    System.out.println(s.trim());
	    String[][] a = new String[5][10];
	 
		for (int i = 0; i < 5; i++)
		{
			Arrays.fill(a[i], " ");
		}
		
		if (plants != null)
		{
			 for(Plant plant : plants)
			 {
				 if(plant instanceof Sunflower)
				 {
					 a[plant.getX() - 1][plant.getY() - 1] = "s";
				 }
				 else if (plant instanceof Peashooter)
				 {
					 a[plant.getX() - 1][plant.getY() - 1] = "p";
				 }
			 }
		}
		else
		{
			System.out.println("You didn't place any plants.");
		}
		
	    if(zombies != null)
	    {
	    	for	(Zombie zombie : zombies)
	    	{
				if (zombie instanceof BasicZombie)
				{
					if(a[zombie.getX() - 1][zombie.getY() - 1] == " ")
					{
						a[zombie.getX() - 1][zombie.getY() - 1] = "z";	
					}
					else
					{
						a[zombie.getX() - 1][zombie.getY() - 1] = a[zombie.getX() - 1][zombie.getY() - 1] + "/z";
					}
				}
			}
	    }
	    else
	    {
	    	System.out.println("There is no zombies in the map.");
	    }
	    
	    for(int i = 0; i < 5; i++)
	    {
	    	System.out.println(Arrays.toString(a[i]));
	    }
	}

	/**
	 * User action in this turn, user can select drop plant or pass the turn
	 * 
	 * @author Xinrui Li
	 */
	private void userTurn()
	{
		Scanner console = new Scanner(System.in);
		String input = "";
		
		while (true)
		{
			System.out.println("Enter the action at this turn (pass/drop):");
			input = console.nextLine().trim();
			if (input.equals("pass"))
			{
				console.close();
				return;
			}
			else if (input.equals("drop"))
			{
				while (true)
				{
					System.out.println("Enter the plant you want to drop (sunflower/peashooter):");
					input = console.nextLine().trim();
					
					if (input.equals("sunflower") || input.equals("peashooter"))
					{
						while (true)
						{
							System.out.println("Enter the place you wanna drop (row column):");
							String position = console.nextLine();
							int x = 0;
							int y = 0;
							String[] entity = position.split("\\s+");
							x = Integer.valueOf(entity[0]);
							y = Integer.valueOf(entity[1]);
							if (isEmpty(x, y))
							{
								plantAPlant(x, y, input);
								console.close();
								return;
							}
						}
					}
				}
			}
		}
	}

	/**
	 * The action of the plants in this turn: generating sun, attacking zombies, or
	 * standing by
	 * @author Xinrui Li
	 */
	private void plantAction()
	{
		for (Plant p : plants)
		{
			if (p instanceof DamagePlant)
			{
				Zombie firstZombie = null;
				for (Zombie z : zombies)
				{
					if (p.getX() == z.getX() && (firstZombie == null || firstZombie.getY() > z.getY()))
					{
						firstZombie = z;
					}
				}
				if (firstZombie != null)
				{
					firstZombie.takeDamage(((DamagePlant) p).getDamage());
					if (firstZombie.getHealth() <= 0)
					{
						zombies.remove(firstZombie);
						totalZombies--;
					}
				}
			}
			else if (p instanceof SunPlant)
			{
				sun += ((SunPlant) p).generateSun();
			}
		}
	}

	/**
	 * The method will check the position for sun-flower and zombie, if zombie
	 * and sun-flower at same position, sun-flower get attack, otherwise zombie
	 * move forward.
	 * 
	 * @author Xinrui Li, BeckZ
	 */
	private void zombieAction()
	{
		for (Zombie z : zombies)
		{
			boolean action = false;
			for (Plant p : plants)
			{
				if (z.getX() == p.getX() && z.getY() == p.getY())
				{
					p.takeDamage(z.getDamage());
					if (p.getHealth() <= 0)
					{
						plants.remove(p);
					}
					action = true;
					break;
				}
			}
			if (!action)
			{
				z.setY(z.getY() - z.getMoveSpeed());
			}
		}
		
		
		// Zombies spawn
		if (zombieUnshowed > 0)
		{
			Random rand = new Random();
			int n = rand.nextInt(5) + 1;
			if (tickNumber == 0)
			{
				Zombie z = new BasicZombie(n);
				zombies.add(z);
				zombieUnshowed--;
			}
			else if ((tickNumber % 2) == 0)
			{
				Zombie z = new BasicZombie(n);
				zombies.add(z);
				zombieUnshowed--;
			}
		}
	}

	/**
	 * Check whether the zombie(s) cross the whole line
	 * 
	 * @author Xinrui Li
	 * @return True if at least one zombie cross the line, false otherwise
	 */
	private boolean checkZombieWin()
	{
		for (Zombie z : zombies)
		{
			if (z.getY() <= 0)
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * Check the entity in the map is empty (no plant in this coordinate)
	 * 
	 * @author Xinrui Li
	 * @param row    The y of the entity
	 * @param column The x of the entity
	 * @return True if no plant is in that coordinate, false if this coordinate
	 *         already has plant or invalid
	 */
	private boolean isEmpty(int row, int column)
	{
		if (row < 1 || row > 5 || column < 1 || column > 10)
		{
			return false;
		}
		
		for (Plant p : plants)
		{
			if (p.getX() == row && p.getY() == column)
			{
				return false;
			}
		}
		return true;
	}

	/**
	 * This method will set the position for the sun flowers, consume 10 sun
	 * for each sun flower
	 * @author BeckZ
	 * @param x, the x position for the plant
	 * @param y, the y position for the plant
	 * @return null
	 */
	public void plantAPlant(int x, int y, String type)
	{
		if (sun >= 25 && type.equals("sunflower"))
		{
			Sunflower plant = new Sunflower(x, y);
			if (plants.add(plant))
			{
				sun = sun - plant.getSun();
				System.out.println("You created an new sunflower.");
				return;
			}
			System.out.println("Unable to create a new sunflower!");
		}
		else if (sun >= 50 && type.equals("peashooter"))
		{
			Peashooter plant = new Peashooter(x, y);
			if (plants.add(plant))
			{
				sun = sun - plant.getSunCost();
				System.out.println("You create an new Peashooter.");
				return;
			}
			System.out.println("Unable to create a new Peashooter!");
		}
		else
		{
			System.out.println("You do not have enough sun!");
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
