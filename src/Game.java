import java.util.ArrayList;
import java.util.Arrays;
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
		changePosition();
		printMap();
	}
	
	/**
	 * Title screen, the first thing showed when the game was run 
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
			//taketurn();
		} else if (input.equals("exit")) {
			System.exit(0);
		}
    }
    
	
	private void printMap() {
		 System.out.println("Sun: " + sun);
		    String s = "Available plants: ";
		    if(sun >= 25) {
		    	 s += "Sunflower ";
		     }
		    if(sun >= 30) {
		    	 s += "Peashooter ";
		     }
		    System.out.println(s);
		    
		String[][] a = new String[5][10];
		 
		for(int i=0;i<5;i++) {
			Arrays.fill(a[i], " ");
		}
		
		if(plants != null) {
			 for(int i = 0; i<plants.size();i++) {
				 if(plants.get(i) instanceof Sunflower) {
					 a[plants.get(i).getX()-1][plants.get(i).getY()-1] = "s";
				 }
				 else if (plants.get(i) instanceof Peashooter) {
					 a[plants.get(i).getX()-1][plants.get(i).getY()-1] = "p";
				 }
			 }
		}
		else {
			System.out.println("You didn't place any plants.");
		}
		
	    if(Zombie != null) {
	    	for(int i = 0; i< Zombie.size(); i++) {
				if(Zombie.get(i) instanceof BasicZombie) {
					a[Zombie.get(i).getX()][Zombie.get(i).getY()] = "z";
				}
			}
	    }
	    else {
	    	System.out.println("There is no Zombie in the map.");
	    }
	    for(int i =0; i<5;i++) {
	    	System.out.println(Arrays.toString(a[i]));
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
		Game g = new Game();
		
    }
}
