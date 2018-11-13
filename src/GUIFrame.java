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
		jlistPanel.setLayout(new GridLayout(0, 1));
		jframe.add(jlistPanel, BorderLayout.BEFORE_LINE_BEGINS);
		JList = new JList<>(listModel);
		JList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JList.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!JList.getValueIsAdjusting()) {
					//selected = JList.getSelectedValue();
				}
			}
		});
		
		listModel.addElement(new Sunflower(1, 2));
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
