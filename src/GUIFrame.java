import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class GUIFrame implements ActionListener{

	private JPanel pane; //top panel
	private JPanel jlistPanel;
	private JFrame jframe;
	private JMenuBar menuBar;
	private GridBagConstraints c;
	private Game game = new Game();
	private JMenu Game;
	private JMenuItem newGame, exit;
	private int Width, Height;
	private JButton SF,Pea, PASS;
	
	private JButton B1, B2, B3, B4, B5, B6, B7, B8, B9, B10, B11, B12, B13, B14, B15, B16, B17, B18, B19, B20;
	private JButton B21, B22, B23, B24, B25, B26, B27, B28, B29, B30, B31, B32, B33, B34, B35, B36, B37, B38, B39, B40;
	private JButton B41, B42, B43, B44, B45, B46, B47, B48, B49, B50;
	
	private JList<Plant> JList;
	private DefaultListModel<Plant> listModel = new DefaultListModel<>();

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

		jframe.setVisible(true);
	}
	
	/**
	 * 	@desc add selection panel to the top of jframe which contains {Sunflower, Peashooter etc.}
	 * */
	public void selectionPanel() {
		pane = new JPanel();
		pane.setLayout(new GridLayout(1, 5));
		jframe.add(pane, BorderLayout.AFTER_LAST_LINE);
		SF= new JButton("Sunflower");
		Pea = new JButton("Peashooter");
		PASS = new JButton("Pass a round");
		
		pane.add(SF);
		pane.add(Pea);
		pane.add(PASS);
		JTextField sun = new JTextField("Your total number of sun is: " + game.getSun());
		sun.setEditable(false);
		
		pane.add(sun);
	}
	
	
	/**
	 * 	@desc add result panel to the middle of jframe which shows current map
	 * */
	public void mappingPanel() {
		jlistPanel= new JPanel();
		jlistPanel.setLayout(new GridLayout(5, 10));
		jframe.add(jlistPanel);
		B1 = new JButton(); B2 = new JButton(); B3 = new JButton(); B4 = new JButton(); B5 = new JButton(); B6 = new JButton(); B7 = new JButton();
		B8 = new JButton(); B9 = new JButton(); B10 = new JButton(); B11 = new JButton(); B12 = new JButton(); B13 = new JButton(); B14 = new JButton();
		B15 = new JButton(); B16 = new JButton(); B17 = new JButton(); B18 = new JButton(); B19 = new JButton(); B20 = new JButton(); B21 = new JButton();
		B22 = new JButton(); B23 = new JButton(); B24 = new JButton(); B25 = new JButton(); B26 = new JButton(); B27 = new JButton(); B28 = new JButton();
		B29 = new JButton(); B30 = new JButton(); B31 = new JButton(); B32 = new JButton(); B33 = new JButton(); B34 = new JButton(); B35 = new JButton();
		B36 = new JButton(); B37 = new JButton(); B38 = new JButton(); B39 = new JButton(); B40 = new JButton(); B41 = new JButton(); B42 = new JButton();
		B43 = new JButton(); B44 = new JButton(); B45 = new JButton(); B46 = new JButton(); B47 = new JButton(); B48 = new JButton(); B49 = new JButton(); B50 = new JButton();
		
		jlistPanel.add(B1);jlistPanel.add(B2);jlistPanel.add(B3);jlistPanel.add(B4);jlistPanel.add(B5);jlistPanel.add(B6);jlistPanel.add(B7);jlistPanel.add(B8);
		jlistPanel.add(B9);jlistPanel.add(B10);jlistPanel.add(B11);jlistPanel.add(B12);jlistPanel.add(B13);jlistPanel.add(B14);jlistPanel.add(B15);jlistPanel.add(B16);
		jlistPanel.add(B17);jlistPanel.add(B18);jlistPanel.add(B19);jlistPanel.add(B20);jlistPanel.add(B21);jlistPanel.add(B22);jlistPanel.add(B23);jlistPanel.add(B24);
		jlistPanel.add(B25);jlistPanel.add(B26);jlistPanel.add(B27);jlistPanel.add(B28);jlistPanel.add(B29);jlistPanel.add(B30);jlistPanel.add(B31);jlistPanel.add(B32);
		jlistPanel.add(B33);jlistPanel.add(B34);jlistPanel.add(B35);jlistPanel.add(B36);jlistPanel.add(B37);jlistPanel.add(B38);jlistPanel.add(B39);jlistPanel.add(B40);
		jlistPanel.add(B41);jlistPanel.add(B42);jlistPanel.add(B43);jlistPanel.add(B44);jlistPanel.add(B45);jlistPanel.add(B46);jlistPanel.add(B47);jlistPanel.add(B48);jlistPanel.add(B49);jlistPanel.add(B50);
	}
	
	/**
	 * @desc perform a action when user click the 
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == newGame) {
			
		} else if (e.getSource() == exit) {
			System.exit(0);
		} else {
			
		}
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new GUIFrame();
		
	}

}
