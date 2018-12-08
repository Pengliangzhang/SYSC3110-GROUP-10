package mvc;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import entities.AdvancedPeashooter;
import entities.AdvancedZombie;
import entities.BasicZombie;
import entities.DamagePlant;
import entities.Peashooter;
import entities.Plant;
import entities.SunPlant;
import entities.Sunflower;
import entities.Zombie;

/**
 * This class represents the model portion of an MVC representation of a Plants vs. Zombies game.
 * 
 * @author BeckZ, Kevin, Xinrui Li, Bohua Cao
 * @version December 7, 2018
 */
public class Game implements Serializable{

	private int tickCount, sun, totalZombies, remainingZombies;
	private ArrayList<Plant> plants;
	private ArrayList<Zombie> zombies;
	private int level;

	/**
	 * Initializes the game.
	 * 
	 * @author BeckZ
	 */
	public Game() {
		plants = new ArrayList<Plant>();
		zombies = new ArrayList<Zombie>();
//		lists = new ArrayList<Game>(100);
		level = 1;
		totalZombies = 10; // may be changed in the future

		// titleScreen();
	}
	
	public void newGame() {
		sun = 50;
		remainingZombies = totalZombies;
		tickCount = 0;
		plants.clear();
		zombies.clear();
//		lists.clear();
//		index = 0;
//		lists.add(index, copy());
//		size = 1;
	}
	
	/**
	 * Change the level of this game, if fail, the level would not change.
	 * 
	 * @param l The new level in this game
	 */
	public void changeLevel(int l) {
		readLevelFile(l);
	}

	/**
	 * Take one turn, every turn has following step: 1. increment sun by 25 (natural
	 * sun generation) 2. print the map 3. prompt user (drop a plant on the map or
	 * do nothing) 4. plants' actions (attack the zombie in their row) 5. check if
	 * user win this game 6. zombies spawn 7. zombies' actions (attack plants or
	 * move on) 8. check if user lost this game
	 * 
	 * @author Xinrui Li
	 */
	public int takeTurn() {
		// increase the sun
		// sun += 25;

		// User turn
		// userTurn();

		// Plant turn
		plantAction();

		// Check for player win
		if (totalZombies == 0) {
			// Player win
			System.out.println("All zombies are eliminated.\nYou have won!");
			return 1;
			// System.exit(0);
		}

		// Zombie turn
		zombieAction();

		// Check for zombie win
		if (checkZombieWin()) {
			// Zombie win
			System.out.println("The zombies ate your brains!\nGame over.");
			// System.exit(0);
			return -1;
		}
		tickCount++;
		// Print the map
		//printMap();
//		index++;
//		lists.add(index, copy());
//		size = index + 1;
		return 0;
	}

	/**
	 * User action in this turn, they can either drop a plant or pass the turn
	 * 
	 * @author Xinrui Li
	 */
	public boolean userTurn(int i, int j, int select) {
		if (isEmpty(i, j)) {
			if (select == 0) {
				return plantAPlant(i, j, "sunflower");
			} else if (select == 1) {
				return plantAPlant(i, j, "peashooter");
			} else if (select == 2) {
				return plantAPlant(i, j, "advancedPeashooter");
			}
		}
		return false;
	}

	/**
	 * This method takes care of all plants' actions. The plants will generate sun,
	 * attack zombies in their row, or be on standby.
	 * 
	 * @author Xinrui Li
	 */
	private void plantAction() {
		for (Plant p : plants) {
			if (p instanceof DamagePlant) {
				Zombie firstZombie = null;
				for (Zombie z : zombies) {
					if (p.getX() == z.getX() && (firstZombie == null || firstZombie.getY() > z.getY())) {
						firstZombie = z;
					}
				}
				if (firstZombie != null) {
					firstZombie.takeDamage(((DamagePlant) p).getDamage());
					if (firstZombie.getHealth() <= 0) {
						zombies.remove(firstZombie);
						totalZombies--;
					}
				}
			} else if (p instanceof SunPlant) {
				sun += ((SunPlant) p).generateSun();
			}
		}
	}

	/**
	 * This method takes care of all zombies' actions. The zombies will attack, if
	 * there is a plant in front of it. Otherwise, they will advance.
	 * 
	 * @author Xinrui Li, BeckZ
	 */
	private void zombieAction() {
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
				z.setY(z.getY() - z.getMoveSpeed());
			}
		}

		// Zombies spawn
		if (tickCount != 0 && (tickCount % 2) == 0) {
			if (remainingZombies > 0) {
				Random rand = new Random();
				int n = rand.nextInt(5) + 1;
				if (level == 1) {
					zombies.add(new BasicZombie(n));
				} else if (level == 2) {
					zombies.add(new AdvancedZombie(n));
				} else if (level == 3) {
					int m = rand.nextInt(2);
					Zombie z;
					if ((m % 2) == 0) {
						z = new BasicZombie(n);
					} else {
						z = new AdvancedZombie(n);
					}				
					zombies.add(z);
				}
				remainingZombies--;
			}
		}
	}

	/**
	 * Check for zombies that have arrived at the player's doorstep, which signals a
	 * zombie victory.
	 * 
	 * @author Xinrui Li
	 * @return true if at least one zombie has arrived at the player's doorstep,
	 *         false otherwise
	 */
	private boolean checkZombieWin() {
		for (Zombie z : zombies) {
			if (z.getY() <= 0) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Check if the position given by the coordinates is empty, so that a plant may
	 * be placed there.
	 * 
	 * @author Xinrui Li
	 * @param row
	 * @param column
	 * @return false if the coordinates are out of bounds or the position given by
	 *         the coordinates is taken, true otherwise
	 */
	private boolean isEmpty(int row, int column) {
		if (row < 1 || row > 5 || column < 1 || column > 10) {
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
	 * This method will place the given plant at the given coordinates.
	 * 
	 * @author BeckZ
	 * @param x The column number for the plant
	 * @param y The row number for the plant
	 */
	public boolean plantAPlant(int x, int y, String type) {
		if (sun >= 25 && type.equals("sunflower")) {
			Sunflower plant = new Sunflower(x, y);
			if (plants.add(plant)) {
				sun = sun - plant.getSun();
				System.out.println("Sunflower placed at (" + x + ", " + y + ")");
				return true;
			}

			System.out.println("Unable to create a new sunflower!");
		} else if (sun >= 50 && type.equals("peashooter")) {
			Peashooter plant = new Peashooter(x, y);
			if (plants.add(plant)) {
				sun = sun - plant.getSunCost();
				System.out.println("Peashooter placed at (" + x + ", " + y + ")");
				return true;
			}

			System.out.println("Unable to create a new Peashooter!");
		} else if (sun >= 75 && type.equals("advancedPeashooter")) {
			AdvancedPeashooter plant = new AdvancedPeashooter(x, y);
			if (plants.add(plant)) {
				sun = sun - plant.getSunCost();
				System.out.println("AdvancedPeashooter placed at (" + x + ", " + y + ")");
				return true;
			}
			System.out.println("Unable to create a new Peashooter!");
		} else {
			System.out.println("You do not have enough sun!");
		}
		return false;
	}

	/**
	 * @return The ArrayList that contains all the zombies in the game
	 */
	public ArrayList<Zombie> getAllZombies() {
		return zombies;
	}

	/**
	 * @return The ArrayList that contains all the plants in the game
	 */
	public ArrayList<Plant> getAllPlants() {
		return plants;
	}

	/**
	 * @return The amount of sun currently at the user's disposal
	 */
	public int getSun() {
		return sun;
	}

	/**
	 * Updates the sun count with the given amount.
	 * 
	 * @param sun The new amount of sun
	 */
	public void setSun(int sun) {
		this.sun = sun;
	}
	
	/**
	 * The default level in a new game is 1, and this method will set that value to be 1.
	 */
	public void setLevel(int l) {
		level = l;
	}
	
	/**
	 * @return the current level
	 */
	public int getlevel() {
		return level;
	}
	
	/**
	 * @return The current turn number
	 */
	public int getTickCount() {
		return tickCount;
	}

	/**
	 * @return The total number of zombies to be spawned on the map throughout the game
	 */
	public int getTotalZombies() {
		return totalZombies;
	}
	
	/**
	 * @param num The total number of zombies to be spawned on the map throughout the game
	 */
	public void setTotalZombie(int num) {
		totalZombies = num;
	}
	
	/**
	 * @return The number of zombies yet to spawn
	 */
	public int getRemainingZombies() {
		return remainingZombies;
	}
	
	/**
	 * Creates a deep copy of the given Game instance.
	 * The resulting copy can be modified without affecting the original instance, and vice versa.
	 * 
	 * @param g an instance of Game to be deep copied
	 * @return A deep copy of the given Game instance
	 */
	public Game copy() {
		Game temp = null;
		try {
			ByteArrayOutputStream m1 = new ByteArrayOutputStream();
			ObjectOutputStream out = new ObjectOutputStream(m1);
			out.writeObject(this);
			out.flush();
			out.close();
			
			ByteArrayInputStream m2 = new ByteArrayInputStream(m1.toByteArray());
			ObjectInputStream in = new ObjectInputStream(m2);
			temp = (Game) in.readObject();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return temp;
	}
	
	/**
	 * This will save the given Game object into a file, which is saved in the file system.
	 * 
	 * @param g The Game object that the user would like to store
	 * @return return true if save to a file, false otherwise
	 * */
	public boolean saveGame(Game g) {
		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("games.ser"));
			out.writeObject(g);
			out.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Load in a previous save of the game, and set the frame to be as the game was then.
	 * 
	 * @return returns a previously saved Game to be loaded in
	 */
	public Game loadGame() {
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream("games.ser"));
			Game g = (Game) in.readObject();
			in.close();
			return g;
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Will initialize the given level, of which there are 3, stored in an XML file.
	 * 
	 * @param level used to describe the level to be loaded in
	 */
	public void readLevelFile(int level) {
		File file = new File("level.xml");
		try {
			readSAX(file, level);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Parses the given file (which contains level information) in order to fetch the contents of the given level from the file.
	 * The given file is in XML, thus this method uses SAX (Simple API for XML) to parse it.
	 * 
	 * @param file The given XML file, which contains level information
	 * @param level The level that should be retrieved from the file
	 * @throws Exception
	 */
	public void readSAX(File file, int level) throws Exception{
		SAXParserFactory spf = SAXParserFactory.newInstance();
		SAXParser s = spf.newSAXParser();
		
		DefaultHandler dh = new DefaultHandler() {
			boolean i = false, j = false, k = false;
			
			Integer name;
			String type;
			Integer num;
			/**
			 * This method is called every time the parser hits an open tag '<'
			 */
			public void startElement(String u, String localName, String qName, Attributes a) throws SAXException {
				// System.out.println("START: " + qName);
				
				if(qName.equalsIgnoreCase("name")){
					i = true;
				}
				if(qName.equalsIgnoreCase("type")){
					j = true;
				}
				if(qName.equalsIgnoreCase("num")){
					k = true;
				}
			}					
			
			public void characters(char[] ch, int start, int length) throws SAXException{				
				if(i) {
					name = Integer.parseInt(new String(ch, start, length));
					i = false;
				}
				if(j) {
					type = new String(ch, start, length);
					j = false;
				}
				if(k && name == level) {
					num = Integer.parseInt(new String(ch, start, length));
					System.out.println(num);
					setLevel(level);
					setTotalZombie(num);
					k = false;
				}
			}
			
			public void endElement(String uri, String localname, String qName) throws SAXException{
				// System.out.println("END: " + qName);
			}
			
		};
		
		s.parse(file, dh);
	}	
}
