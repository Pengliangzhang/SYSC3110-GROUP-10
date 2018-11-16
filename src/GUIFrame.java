import java.awt.*;
import java.awt.event.*;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

public class GUIFrame implements ActionListener  {

	private JPanel pane; // top panel
	private JPanel jlistPanel;
	private JFrame jframe;
	private JMenuBar menuBar;
	private GridBagConstraints c;
	private Game game;
	private JMenu Game;
	private JMenuItem newGame, exit;
	private int Width, Height;
	private JButton SF,Pea, PASS;
	private JTextField sun;
	private int status;
	private int plantSelect; // -1 for not select, 0 for sunflower, 1 for peashooter
	private JButton[][] buttons;
	

	/**
	 * @desc Initialize an frame
	 **/
	public GUIFrame() {

		jframe = new JFrame("SYSC3110 GROUP-10");
		jframe.setLayout(new BorderLayout());
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// initialize pane and add to jframe, using GridBagLayout

		Width = (int) java.awt.Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		Height = (int) java.awt.Toolkit.getDefaultToolkit().getScreenSize().getHeight();

		Dimension DimMax = Toolkit.getDefaultToolkit().getScreenSize();
		jframe.setSize(Width, Height);
		jframe.setMaximumSize(DimMax);
		jframe.setExtendedState(JFrame.MAXIMIZED_BOTH);

		// Add menu to JFrame
		menuBar = new JMenuBar();
		Game = new JMenu("Game");
		menuBar.add(Game);
		jframe.add(menuBar, BorderLayout.BEFORE_FIRST_LINE);
		// Add menuItem
		newGame = new JMenuItem("New Game");
		newGame.addActionListener(this);
		Game.add(newGame);
		exit = new JMenuItem("Exit");
		exit.addActionListener(this);
		Game.add(exit);
		selectionPanel();
		mappingPanel();
		
		
		
		disableAll();
		game = new Game();

		jframe.setVisible(true);
	}

	/**
	 * @desc add selection panel to the top of jframe which contains {Sunflower,
	 *       Peashooter etc.}
	 */
	public void selectionPanel() {
		pane = new JPanel();
		pane.setLayout(new GridLayout(1, 5));
		jframe.add(pane, BorderLayout.SOUTH);
		SF = new JButton("Sunflower");
		Pea = new JButton("Peashooter");
		PASS = new JButton("Pass a round");
		SF.addActionListener(this);
		Pea.addActionListener(this);
		PASS.addActionListener(this);
		
		pane.add(SF);
		pane.add(Pea);
		pane.add(PASS);
		sun = new JTextField("Game no start");
		sun.setEditable(false);

		pane.add(sun);
		
		plantSelect = -1;
	}

	/**
	 * @desc add result panel to the middle of jframe which shows current map
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
				} else {
					buttons[i][j].setText("Zombies' Spawn(Invisible)");
				}
			}
		}
	}
	
	/**
	 * @desc Check who is the winner
	 * */
	public void checkWinner() {
		if (status == 0) {
			return;
		}
		disableAll();
		if (status == 1) {
			sun.setText("All zombies are eliminated. You have won!");
		} else if (status == -1) {
			sun.setText("The zombies ate your brains!");
		}
	}
	
	/**
	 * @desc disable all buttons
	 * */
	public void disableAll() {
		for (int i = 0; i < 5; ++i) {
			for (int j = 0; j < 10; ++j) {
				buttons[i][j].setEnabled(false);
			}
		}
		SF.setEnabled(false);
		Pea.setEnabled(false);
		PASS.setEnabled(false);
	}
	
	/**
	 * @desc enable all buttons
	 * */
	public void enableAll() {
		for (int i = 0; i < 5; ++i) {
			for (int j = 0; j < 9; ++j) {
				buttons[i][j].setEnabled(true);
			}
		}
		SF.setEnabled(true);
		Pea.setEnabled(true);
		PASS.setEnabled(true);
	}
	
	/**
	 * @desc clear all text on buttons
	 * */
	public void cleanButton() {
		for (int i = 0; i < 5; ++i) {
			for (int j = 0; j < 9; ++j) {
				buttons[i][j].setText(null);;
			}
		}
	}
	
	/**
	 * @desc printing the map
	 * @param i, j button's position
	 * @param plant type of plant which is selected by user
	 * */
	public void printPlantMap(int i, int j, int plant) {		
		if(plant==0) {
			buttons[i][j].setText("S F");
		}else if(plant == 1) {
			buttons[i][j].setText("PEA");
		}
		
	}
	
	/**
	 * @desc printing the map
	 * @param i, j button's position
	 * */
	public void printZombieMap(int i, int j) {
		int x=i+1;
		int y=j+1;
		String s = "";

		for (Plant p:game.getAllPlants()) {
			if(x==p.getX()&&y==p.getY()&&(p instanceof Sunflower)) {
				s = s+"-SF";
			}else if(x==p.getX()&&y==p.getY()&&(p instanceof Peashooter)) {
				s = s+"-PEA";
			}
		}
		for(Zombie z:game.getAllZombia()) {
			if(x==z.getX()&&y==z.getY()) {
				s = s + "-Z";
			}
		}
		buttons[i][j].setText(s);
	}
	
	/**
	 * @desc re-print the map
	 * */
	public void renewMap() {
		for (int i = 0; i < 5; ++i) {
			for (int j = 0; j < 9; ++j) {
				printZombieMap(i, j);
			}
		}
	}
	
	/**
	 * @desc perform a action when user click the
	 * @param e ActionEvent which user clicked
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == newGame) {
			game.newGame();
			status = 0;
			enableAll();
			cleanButton();
			sun.setText("Your total number of sun is: " + game.getSun());
		} else if (e.getSource() == exit) {
			System.exit(0);
		} else if (e.getSource().equals(SF)) {
			plantSelect = 0;
		} else if (e.getSource().equals(Pea)) {
			plantSelect = 1;
		} else if (e.getSource().equals(PASS)) {
			plantSelect = -1;
			status = game.takeTurn();
			sun.setText("Your total number of sun is: " + game.getSun());
			checkWinner();
			renewMap();
		} else {
			buttons[0][9].setText("");
			for (int i = 0; i < 5; ++i) {
				for (int j = 0; j < 9; ++j) {
					if (e.getSource().equals(buttons[i][j]) && plantSelect != -1) {
						boolean temp = game.userTurn(i + 1, j + 1, plantSelect);
						if (temp) {
							printPlantMap(i, j, plantSelect);
							status = game.takeTurn();
							sun.setText("Your total number of sun is: " + game.getSun());
							checkWinner();
							plantSelect = -1;
						}
						plantSelect = -1;						
					}
					renewMap();
				}
			}
		}
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new GUIFrame();

	}
}
