package mvc;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.*;

import entities.AdvancedPeashooter;
import entities.AdvancedZombie;
import entities.BasicZombie;
import entities.Peashooter;
import entities.Plant;
import entities.Sunflower;
import entities.Zombie;

/**
 * The view part of the MVC implementation of PvZ. Contains a grid that represents the yard,
 * buttons that allow for plants to be planted in the yard, and a menu bar for saving/loading
 * the game.
 * 
 * @author BeckZ, Kevin, Xinrui Li, Bohua Cao
 * @version December 7, 2018
 */
public class GUIFrame implements ActionListener {
	private JPanel pane; // top panel
	private JPanel jlistPanel;
	private JFrame jframe;
	private JMenuBar menuBar;
	private Game game;
	private ArrayList<Game> history;
	private int index, size;
	private JMenu fileMenu, gameMenu;
	private JMenuItem newGame, exit, redo, undo, save, load, level;
	private int width, height;
	private JButton sunflowerButton, peaButton, advancedPea;
	private JTextField sunIndication;
	private int status;
	private int plantSelect; // -1 for not select, 0 for sunflower, 1 for peashooter, 2 for advancedPeashooter
	private JButton[][] buttons;
	private Timer timer;

	/**
	 * Constructor for GUIFrame objects. Initializes the JFrame and its JMenuBar.
	 */
	public GUIFrame() {
		jframe = new JFrame("SYSC3110 GROUP-10");
		jframe.setLayout(new BorderLayout());
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// initialize pane and add to jframe, using GridBagLayout
		width = (int) java.awt.Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		height = (int) java.awt.Toolkit.getDefaultToolkit().getScreenSize().getHeight();

		Dimension maxDimension = Toolkit.getDefaultToolkit().getScreenSize();
		jframe.setSize(width, height);
		jframe.setMaximumSize(maxDimension);
		jframe.setExtendedState(JFrame.MAXIMIZED_BOTH);

		// Add menu to JFrame
		menuBar = new JMenuBar();
		fileMenu = new JMenu("File");
		menuBar.add(fileMenu);
		gameMenu = new JMenu("Games");
		menuBar.add(gameMenu);
		jframe.add(menuBar, BorderLayout.BEFORE_FIRST_LINE);

		// Add menuItem to file menu
		save = new JMenuItem("Save");
		save.addActionListener(this);
		fileMenu.add(save);
		load = new JMenuItem("Load");
		load.addActionListener(this);
		fileMenu.add(load);
		exit = new JMenuItem("Exit");
		exit.addActionListener(this);
		fileMenu.add(exit);
		// Add menuItem to Games menu
		newGame = new JMenuItem("New Game");
		newGame.addActionListener(this);
		gameMenu.add(newGame);
		level = new JMenuItem("Level");
		level.addActionListener(this);
		gameMenu.add(level);
		undo = new JMenuItem("Undo");
		undo.addActionListener(this);
		gameMenu.add(undo);
		redo = new JMenuItem("Redo");
		redo.addActionListener(this);
		gameMenu.add(redo);
		
		selectionPanel();
		mappingPanel();
		disableAllButtons();
		
		game = new Game();
		history = new ArrayList<Game>();
		index = 0;
		size = 0;
		timer = new Timer();
		
		jframe.setVisible(true);
	}

	/**
	 * Add a panel below the board that allows the user to select plants to plant,
	 * allows the user to skip a turn, and informs the user of how much sun they
	 * possess at the moment.
	 */
	public void selectionPanel() {
		pane = new JPanel();
		pane.setLayout(new GridLayout(1, 5));
		jframe.add(pane, BorderLayout.SOUTH);
		sunflowerButton = new JButton("Sunflower");
		peaButton = new JButton("Peashooter");
		advancedPea = new JButton("Advanced Peashooter");
		
		sunflowerButton.addActionListener(this);
		peaButton.addActionListener(this);
		advancedPea.addActionListener(this);

		pane.add(sunflowerButton);
		pane.add(peaButton);
		pane.add(advancedPea);
		sunIndication = new JTextField("The game has not yet started");
		sunIndication.setEditable(false);

		pane.add(sunIndication);

		plantSelect = -1;
	}

	/**
	 * Adds a panel which holds all the JButtons that make up the game board.
	 */
	public void mappingPanel() {
		jlistPanel = new JPanel();
		jlistPanel.setLayout(new GridLayout(5, 10));
		jframe.add(jlistPanel, BorderLayout.CENTER);
		buttons = new JButton[5][10];

		for (int i = 0; i < 5; ++i) {
			for (int j = 0; j < 10; ++j) {
				buttons[i][j] = new JButton();
				jlistPanel.add(buttons[i][j]);
				if (j < 9) {
					buttons[i][j].addActionListener(this);
				}
			}
		}
	}
	
	public void setStatus(int a) {
		this.status = a;
	}

	/**
	 * Determine the winner of the current game, initiated when there are no more
	 * zombies.
	 */
	public void checkWinner() {
		if (status == 0) {
			return;
		}
		disableAllButtons();
		if (status == 1) {
			sunIndication.setText("All zombies are eliminated. You have won!");
			JOptionPane.showMessageDialog(jframe,"All zombies are eliminated. You have won!");
		} else if (status == -1) {
			sunIndication.setText("The zombies ate your brains!");
			JOptionPane.showMessageDialog(jframe,"The zombies ate your brains!");
		}
	}

	/**
	 * Disables all buttons on the board, used when the game is over.
	 */
	public void disableAllButtons() {
		for (int i = 0; i < 5; ++i) {
			for (int j = 0; j < 10; ++j) {
				buttons[i][j].setEnabled(false);
			}
		}
		sunflowerButton.setEnabled(false);
		peaButton.setEnabled(false);
		advancedPea.setEnabled(false);
		undo.setEnabled(false);
		redo.setEnabled(false);
		save.setEnabled(false);
	}

	/**
	 * Enables all buttons on the board.
	 */
	public void enableAllButtons() {
		for (int i = 0; i < 5; ++i) {
			for (int j = 0; j < 9; ++j) {
				buttons[i][j].setEnabled(true);
			}
		}
		sunflowerButton.setEnabled(true);
		peaButton.setEnabled(true);
		advancedPea.setEnabled(true);
		undo.setEnabled(true);
		redo.setEnabled(true);
		save.setEnabled(true);
		load.setEnabled(true);
		timer();
	}

	/**
	 * Wipes the text off all buttons on the board.
	 */
	public void clearButtonText() {
		for (int i = 0; i < 5; ++i) {
			for (int j = 0; j < 10; ++j) {
				buttons[i][j].setText(null);
				;
			}
		}
	}

	/**
	 * Updates the desired button on the board with the given Zombie's position.
	 * 
	 * @param i the zombie's row
	 * @param j the zombie's column
	 */
	public void printZombieMap(int i, int j) {
		int x = i + 1;
		int y = j + 1;
		String s = "";

		for (Plant p : game.getAllPlants()) {
			if (x == p.getX() && y == p.getY() && (p instanceof Sunflower)) {
				s = s + "-sunflower";
			} else if (x == p.getX() && y == p.getY() && (p instanceof Peashooter)) {
				s = s + "-PEA";
			} else if (x == p.getX() && y == p.getY() && (p instanceof AdvancedPeashooter)) {
				s = s + "-PEA(AD)";
			}
		}
		for (Zombie z : game.getAllZombies()) {
			if (x == z.getX() && y == z.getY() && (z instanceof BasicZombie)) {
				s = s + "-Z";
			}else if(x == z.getX() && y == z.getY() && (z instanceof AdvancedZombie)) {
				s = s + "-A Z";
			}
		}
		buttons[i][j].setText(s);
	}

	/**
	 * Calls printZombieMap() for each button the board, in order to update the
	 * board with all the zombies' positions.
	 */
	public void refreshMap() {
		for (int i = 0; i < 5; ++i) {
			for (int j = 0; j < 10; ++j) {
				printZombieMap(i, j);
			}
		}
		sunIndication.setText("Your total number of sun is: " + game.getSun());
	}
	
	/**
	 * Saves the current Game object to a file.
	 */
	public void save() {
		boolean status = game.saveGame(game);
		if(status&&game!=null) {
			JOptionPane.showMessageDialog(jframe,"You saved the game.");
		}
	}
	
	/**
	 * Load another Game object if possible, and refresh the GUI to reflect this.
	 */
	private void load() {
		if(game.loadGame()==null) {
			JOptionPane.showMessageDialog(jframe,"Unable to load the previous game!");
		}else {
			JOptionPane.showMessageDialog(jframe,"Enjoy your game!");
			game = game.loadGame();
			enableAllButtons();
			refreshMap();
		}
		
	}
	
	/**
	 * Applies zombie actions and their effects on the GUI.
	 */
	public void zombieProcess() {
		status = game.takeTurn();
		history.add(index, game.copy());
		index++;
		size = index;
		refreshMap();
		checkWinner();
		if(status!=0) {
			timer.cancel();
		}		
	}
	
	/**
	 * Creates a timer, which will allow the game to become real-time rather than tick-based (as it was before).
	 */
	public void timer() {
		timer = new Timer();
		timer.schedule(new TimerTask() {
            @Override
            public void run() {
            	zombieProcess();
            }
        }, 3000, 3000);
	}
	
	/**
	 * Performs various actions based on which component sent the ActionEvent.
	 * 
	 * @param e Contains the source of the ActionEvent
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == newGame) {
			game.newGame();
			history.clear();
			index = 0;
			size = 0;
			history.add(index, game.copy());
			index++;
			size++;
			status = 0;
			enableAllButtons();
			clearButtonText();
			sunIndication.setText("Your total number of sun is: " + game.getSun());
		} else if (e.getSource() == exit) {
			System.exit(0);
		} else if(e.getSource()==undo) {
			if (index > 0) {
				timer.cancel();
				index--;
				game = history.get(index).copy();
				refreshMap();
				timer();
			}
		} else if(e.getSource()==redo) {
			if (index < size) {
				timer.cancel();
				index++;
				game = history.get(index).copy();
				refreshMap();
				timer();
			}
		} else if(e.getSource()==save) {
			save();			
		} else if(e.getSource()==load) {
			load();
		} else if(e.getSource()==level) {
			timer.cancel();
			disableAllButtons();
			String[] temp = {"1","2","3"};                  
			String s = (String) JOptionPane.showInputDialog(null,"Please select a new level","Selecting Level",
					JOptionPane.DEFAULT_OPTION,null,temp,temp[0]);
			if (s != null) {
				game.changeLevel(Integer.parseInt(s));
			}
			game.newGame();
			history.clear();
			index = 0;
			size = 0;
			history.add(index, game.copy());
			index++;
			size++;
			status = 0;
			enableAllButtons();
			clearButtonText();
			sunIndication.setText("Your total number of sun is: " + game.getSun());
		} else if (e.getSource().equals(sunflowerButton)) {
			plantSelect = 0;
		} else if (e.getSource().equals(peaButton)) {
			plantSelect = 1;
		} else if (e.getSource().equals(advancedPea)) {
			plantSelect = 2;
		} else {
			buttons[0][9].setText("");
			for (int i = 0; i < 5; ++i) {
				for (int j = 0; j < 9; ++j) {
					if (e.getSource().equals(buttons[i][j]) && plantSelect != -1) {
						boolean temp = game.userTurn(i + 1, j + 1, plantSelect);
						if (temp) {
							plantSelect = -1;
						}
						plantSelect = -1;
					}

				}
			}
			refreshMap();
		}
	}	

	/**
	 * Main method that runs an instance of GUIFrame.
	 * 
	 * @param args Required by default
	 */
	public static void main(String[] args) {
		new GUIFrame();		
	}
}
