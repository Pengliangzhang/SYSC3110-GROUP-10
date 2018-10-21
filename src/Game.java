import java.util.Scanner;

public class Game
{
	private int tickNumber, sun, totalZombies;
    
    /**
     * 
     */
	private Game()
	{
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
		
		if (input.equals("play"))
		{
			
		}
		else if (input.equals("exit"))
		{
			System.exit(0);
		}
	}
    
    /**
     * 
     */
	public static void main(String[] args)
	{
		
	}
}
