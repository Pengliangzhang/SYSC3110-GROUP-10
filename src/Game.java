import java.util.ArrayList;

public class Game
{
    private Place[][] map;
    private int tickNumber, sun, totalZombies;
    private ArrayList<Zombie> ZinR1;
    private ArrayList<Plant> 

    private Game() 
    {
        map = new Place[1][10];
        sun = 0;
        totalZombies = 10; // might be changed
        tick = 0;
        splashScreen();
    }

    private void splashScreen()
	{
		// taketurn(); // begin the turn
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

    public static void main(String[] args)
	{

    }
}
