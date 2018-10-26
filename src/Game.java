import java.util.Scanner;
import java.util.ArrayList;

public class Game
{
    private int tickNumber, sun, totalZombies;
    private ArrayList<Zombie> zombies; // a list of zombies appear in map
    private ArrayList<Plant> plants; // a list of plants appear in map
    
    /**
     * Create a PVZ game with title screen
     */
	private Game()
	{   
        sun = 0;
        totalZombies = 10; // might be changed
        tickNumber = 0;
        zombies = new ArrayList<Zombie> ();
        plants = new ArrayList<Plant> ();
		titleScreen();
	}
	
	/**
	 * Title screen, the first thing showed when the game was run 
	 */
	private void titleScreen()
	{
		System.out.println("Welcome to SYSC3110 Group 10's PvZ, Console Vers.");
		
		Scanner console = new Scanner(System.in);
		boolean goodInput = false;
		String input = "";
		while (!goodInput)
		{
			System.out.println("Enter \"play\" to play, and \"exit\" to quit.");
			input = console.nextLine();
			if (input.equals("play") || input.equals("exit"))
			{
				goodInput = true;
				console.close();
			}
		}
		
		if (input.equals("play"))
		{
			taketurn();
		}
		else if (input.equals("exit"))
		{
			System.exit(0);
		}
    }
    
	/**
	 * Take one turn
	 */
    private void taketurn() 
    {
    	sun += 10; // increase the sun
        
        // Print the map

        // Prompt user
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
        
        // Plant turn
		for (Plant p : plants) {
			if (p instanceof DamagePlant) {
				Zombie firstZombie = null;
				for (Zombie z : zombies) {
					if (p.getY() == z.getY() && (firstZombie == null || firstZombie.getX() > z.getX())) {
						firstZombie = z;
					}
				}
				if (firstZombie != null) {
					firstZombie.takeDamage(((DamagePlant)p).getDamageTick());
					if (firstZombie.getHealth() <= 0) {
						zombies.remove(firstZombie);
						totalZombies--;
					}
				}
			} else if (p instanceof SunPlant) {
				sun += ((SunPlant)p).getSunTick();
			}
			
		}
		
        // Check winner
        if (totalZombies == 0) {
            // Player win
        	System.out.println("Player win");
        	System.exit(0);
        }

        // Zombie spawn
        if (false) {
            // zombies.add(new BasicZombie(0));
        }

        // Zombie turn
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
            	z.setX(z.getX() - z.getMoveSpeed());
            }
        }
        
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
     * Check whether the zombie(s) cross the whole line
     * 
     * @return True if at least one zombie cross the line, false otherwise
     */
    private boolean zombieCrossTheLine() {
        for (Zombie z : zombies) {
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
     * Auto-generate method
     */
	public static void main(String[] args)
	{
		Game g = new Game();
    }
}
