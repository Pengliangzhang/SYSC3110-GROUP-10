import java.util.Scanner;

public class Game
{
	private int tickNumber, sun, totalZombies;
    
    /**
     * Constractor for the game class 
     */
	private Game()
	{   
        sun = 0;
        totalZombies = 10; // might be changed
        tickNumber = 0;
		titleScreen();
	}
    
    /**
     * 
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
    }
    
    /**
     * @
     */
    public void changePosition(){
        for (Zombie zombie : Zombie) {
            zombie.
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
    
    /**
     * 
     */
	public static void main(String[] args)
	{

    }
}
