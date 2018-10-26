import java.util.Scanner;
import java.util.ArrayList;

public class Game
{
    private ArrayList<Zombie> Zombie = new ArrayList();

	private int tickNumber, sun, totalZombies;
    private ArrayList<Zombie> zombies;
    private ArrayList<Plant> plants;
    private final int spawnWave;
    
    /**
     * 
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
    }
    
    /**
     * @
     */
    public void changePosition(){
        for (Zombie zombie : Zombie) {
            //zombie.
        }
        if (input.equals("play"))
		{
			//taketurn();
		}
		else if (input.equals("exit"))
		{
			System.exit(0);
		}
    }
		
    
    private void taketurn() 
    {
        tickNumber += 10; // increase the sun
        
        // Print the map

        // Prompt user
        Scanner console = new Scanner(System.in);
        boolean goodInput = false;
		String input = "";
		while (!goodInput)
		{
			System.out.println("Enter the action at this turn (pass/drop):");
			input = console.nextLine();
			if (input.equals("pass") || input.equals("drop"))
			{
				goodInput = true;
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
			if (typeof(p) == DamagePlant) {
				for (Zombie z : zombies) {
					Zombie firstZombie = null;
					if (p.getY() == z.getY() && (firstZombie == null || firstZombie.getX() > z.getX())) {
						firstZombie = z;
					}
				}
				if (firstZombie != null) {
					firstZombie.takeDamage(p.getDamageTick());
					if (firstZombie.getHealth() <= 0) {
						zombies.remove(firstZombie);
						totalZombies--;
					}
				}
			} else if (typeof(p) == SunPlant) {
				sun += p.getSunTick();
			}
			
		}
		
        // Check winner
        if (totalZombies == 0) {
            // Player win
        	System.out.println("Player win");
        }

        // Zombie spawn
        if (0) {
            zombies.add(new BasicZombie(0));
            zombies.add(new BasicZombie(1));
            zombies.add(new BasicZombie(2));
            zombies.add(new BasicZombie(3));
            zombies.add(new BasicZombie(4));
        }

        // Zombie turn
        for (Zombie z : zombies) {
        	boolean action = false;
            for (Plant p : plants) {
                if (z.getX == p.getX && z.getY == p.getY) {
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
        } else {
            taketurn();
        }
    }
>>>>>>> 60faf6bfb38bac26acf7cc46ff503ac2beb91247
    
    private Object typeof(Plant p) {
		// TODO Auto-generated method stub
		return null;
	}

	private boolean zombieCrossTheLine() {
        for (Zombie z : zombies) {
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
     * 
     */
	public static void main(String[] args)
	{

    }
}
