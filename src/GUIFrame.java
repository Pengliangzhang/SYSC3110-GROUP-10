import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GUIFrame {

	private JPanel pane; //top panel
	private JPanel jlistPanel;
	private GridBagConstraints c;
	private Game game = new Game();
	private JMenu Game;
	private JMenuItem newGame;
	private int Width, Height;
	
	private JList<Object> JList;
	private DefaultListModel<Object> listModel = new DefaultListModel<>();

	/**
	 * @desc Initialize an frame
	 **/
	public GUIFrame() {

		JFrame jframe = new JFrame("SYSC3110 GROUP-10");
		jframe.setLayout(new BorderLayout());
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//initialize pane and add to jframe, using GridBagLayout
		pane = new JPanel();
		pane.setLayout(new GridLayout(1, 5));
		jframe.add(pane, BorderLayout.BEFORE_FIRST_LINE);
		
		jlistPanel= new JPanel();
		jlistPanel.setLayout(new GridLayout(0, 1));
		jframe.add(jlistPanel);

		Width = (int) java.awt.Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		Height = (int) java.awt.Toolkit.getDefaultToolkit().getScreenSize().getHeight();

		Dimension DimMax = Toolkit.getDefaultToolkit().getScreenSize();
		jframe.setSize(Width, Height);
		jframe.setMaximumSize(DimMax);
		jframe.setExtendedState(JFrame.MAXIMIZED_BOTH);

		// Add menu to JFrame
		JMenuBar menuBar = new JMenuBar();
		Game = new JMenu("Game");
		menuBar.add(Game);
		jframe.add(menuBar, BorderLayout.NORTH);
		// Add menuItem
		newGame = new JMenuItem("New Game");
		Game.add(newGame);
		selectionPanel();

		jframe.setVisible(true);
	}
	
	/**
	 * 	@desc add selection panel to the top of jframe which contains {Sunflower, Peashooter etc.}
	 * */
	public void selectionPanel() {
		pane.add(new JButton("Sunflower"));
		pane.add(new JButton("Peashooter"));
		pane.add(new JButton("Pass a round"));
		JTextField sun = new JTextField("Your total number of sun is: " + game.getSun());
		sun.setEditable(false);
		
		pane.add(sun);
	}
	
	/**
	 * 	@desc add result panel to the middle of jframe which shows current map
	 * */
	public void mappingPanel() {
		JList = new JList<>(listModel);
		pane.add(JList, BorderLayout.CENTER);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new GUIFrame();
	}

}
