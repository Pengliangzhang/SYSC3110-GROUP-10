import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

public class GUIFrame implements ActionListener {
	private JPanel pane; // top panel
	private JPanel jlistPanel;
	private JFrame jframe;
	private JMenuBar menuBar;
	private Game game=null;
	private JMenu fileMenu, gameMenu;
	private JMenuItem newGame, exit, redo, undo, save, load;
	private int width, height;
	private JButton sunflowerButton, peaButton, passButton, advancedPea;
	private JTextField sunIndication;
	private int status;
	private int plantSelect; // -1 for not select, 0 for sunflower, 1 for peashooter, 2 for advancedPeashooter
	private JButton[][] buttons;
	
	private ArrayList<Game> gameList;

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
		undo = new JMenuItem("Undo");
		undo.addActionListener(this);
		gameMenu.add(undo);
		redo = new JMenuItem("Redo");
		redo.addActionListener(this);
		gameMenu.add(redo);
		newGame = new JMenuItem("New Game");
		newGame.addActionListener(this);
		gameMenu.add(newGame);
		
		selectionPanel();
		mappingPanel();
		disableAllButtons();
		game = new Game();
		gameList = new ArrayList<Game>();

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
		passButton = new JButton("Pass a round");
		
		sunflowerButton.addActionListener(this);
		peaButton.addActionListener(this);
		advancedPea.addActionListener(this);
		passButton.addActionListener(this);

		pane.add(sunflowerButton);
		pane.add(peaButton);
		pane.add(advancedPea);
		pane.add(passButton);
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
		passButton.setEnabled(false);
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
		passButton.setEnabled(true);
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
	 * @desc save the game to an file
	 * */
	public void save() {
		boolean status = game.saveGame(game);
		if(status&&game!=null) {
			JOptionPane.showMessageDialog(jframe,"You saved the game.");
		}
	}
	
	/**
	 * @desc load an old version game to the current round
	 * */
	private void load() {
		// TODO Auto-generated method stub
		if(game.loadGame()==null) {
			JOptionPane.showMessageDialog(jframe,"Unable to load the previous game!");
		}else {
			game = game.loadGame();
			refreshMap();
		}
		
	}

	/**
	 * Performs various actions based on which component sent the ActionEvent.
	 * 
	 * @param e Contains the source of the ActionEvent
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == newGame) {
			game.newGame();
			status = 0;
			enableAllButtons();
			clearButtonText();
			sunIndication.setText("Your total number of sun is: " + game.getSun());
		} else if (e.getSource() == exit) {
			System.exit(0);
		} else if(e.getSource()==undo) {
			game.undo();
			refreshMap();
		} else if(e.getSource()==redo) {
			game.redo();
			refreshMap();
		} else if(e.getSource()==save) {
			save();			
		} else if(e.getSource()==save) {
			load();		
		} else if (e.getSource().equals(sunflowerButton)) {
			plantSelect = 0;
		} else if (e.getSource().equals(peaButton)) {
			plantSelect = 1;
		} else if (e.getSource().equals(advancedPea)) {
			plantSelect = 2;
		} else if (e.getSource().equals(passButton)) {
			plantSelect = -1;
			status = game.takeTurn();
			sunIndication.setText("Your total number of sun is: " + game.getSun());
			checkWinner();			
			refreshMap();
		} else {
			buttons[0][9].setText("");
			for (int i = 0; i < 5; ++i) {
				for (int j = 0; j < 9; ++j) {
					if (e.getSource().equals(buttons[i][j]) && plantSelect != -1) {
						boolean temp = game.userTurn(i + 1, j + 1, plantSelect);
						if (temp) {
							status = game.takeTurn();
//							sunIndication.setText("Your total number of sun is: " + game.getSun());
							checkWinner();
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
