import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

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
	
	private JList<Object> JList;
	private DefaultListModel<Object> listModel = new DefaultListModel<>();

	/**
	 * @desc Initialize an frame
	 **/
	public GUIFrame() {

		jframe = new JFrame("SYSC3110 GROUP-10");
		jframe.setLayout(new BorderLayout());
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//initialize pane and add to jframe, using GridBagLayout
		pane = new JPanel();
		pane.setLayout(new GridLayout(1, 5));
		jframe.add(pane, BorderLayout.AFTER_LAST_LINE);
		
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
