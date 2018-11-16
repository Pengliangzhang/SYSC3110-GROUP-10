import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class GUIFrame implements ActionListener{

	private JPanel pane; //top panel
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
		
		//initialize pane and add to jframe, using GridBagLayout		

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
		jframe.add(menuBar, BorderLayout.NORTH);
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

		jframe.setVisible(true);
	}
	
	/**
	 * 	@desc add selection panel to the top of jframe which contains {Sunflower, Peashooter etc.}
	 * */
	public void selectionPanel() {
		pane = new JPanel();
		pane.setLayout(new GridLayout(1, 5));
		jframe.add(pane, BorderLayout.SOUTH);
		SF = new JButton("Sunflower");
		Pea = new JButton("Peashooter");
		PASS = new JButton("Pass a round");
		
		pane.add(SF);
		pane.add(Pea);
		pane.add(PASS);
		sun = new JTextField("Game no start");
		sun.setEditable(false);
		
		pane.add(sun);
		
		plantSelect = -1;
	}
	
	
	/**
	 * 	@desc add result panel to the middle of jframe which shows current map
	 * */
	public void mappingPanel() {
		jlistPanel= new JPanel();
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
	
	public void checkWinner() {
		if (status == 0) {
			return;
		}
		disableAll();
		if (status == 1) {
			sun.setText("Player win");
		} else if (status == -1) {
			sun.setText("Zombie win");
		}
	}
	
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
	 * @desc perform a action when user click the 
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == newGame) {
			game = new Game();
			status = 0;
			enableAll();
			sun.setText("Your total number of sun is: " + game.getSun());
		} else if (e.getSource() == exit) {
			System.exit(0);
		} else if (e.getSource() == SF) {
			plantSelect = 0;
		} else if (e.getSource() == Pea) {
			plantSelect = 1;
		} else if (e.getSource() == PASS) {
			plantSelect = -1;
			status = game.takeTurn();
			checkWinner();
		} else if (e.getSource() instanceof JButton) {
			for (int i = 0; i < 5; ++i) {
				for (int j = 0; j < 9; ++j) {
					if (e.getSource().equals(buttons[i][j]) && plantSelect != -1) {
						if (i == 0) {
							game.plantAPlant(i, j, "sunflower");
						} else if (i == 1) {
							game.plantAPlant(i, j, "peashooter");
						}
						game.takeTurn();
					}
				}
			}
		}
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new GUIFrame();
		
	}

}
