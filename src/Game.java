import java.util.Scanner;

public class Game
{
<<<<<<< HEAD
    private ArrayList<Zombie> Zombie = new ArrayList();

=======
>>>>>>> fc217f53adf9b31dc1f370d6f7fd8259714a7307
	private int tickNumber, sun, totalZombies;
    
    /**
     * 
     */
	private Game()
	{   
        sun = 0;
        totalZombies = 10; // might be changed
        tick = 0;
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
		
<<<<<<< HEAD
    }
    
    /**
     * @
     */
    public void changePosition(){
        for (Zombie zombie : Zombie) {
            zombie.
        }
    }
=======
		if (input.equals("play"))
		{
			taketurn();
		}
		else if (input.equals("exit"))
		{
			System.exit(0);
		}
<<<<<<< HEAD
	}
>>>>>>> fc217f53adf9b31dc1f370d6f7fd8259714a7307
=======
    }
    
    private taketurn() 
    {
        tick += 10; // increase the sun
        
        // Prompt user

        // Plant turn

        // Check winner
        if (totalZombies == 0) {
            // Player win
        }

        // Zombie spawn

        // Zombie turn

        if (0) {
            // Zombie win
        } else {
            taketurn();
        }
    }
>>>>>>> 60faf6bfb38bac26acf7cc46ff503ac2beb91247
    
    /**
     * 
     */
	public static void main(String[] args)
	{

    }
}
