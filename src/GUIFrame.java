import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.*;

// import javax.swing.JFrame;
// import javax.swing.JMenu;
// import javax.swing.JMenuBar;
// import javax.swing.JMenuItem;

public class GUIFrame implements ActionListener{

	private JFrame jframe;
	private Game game;
	private JMenu Game;
	private JMenuItem newGame, exit;
	private int Width, Height;

	/**
	 * @desc Initialize an frame
	 **/
	public GUIFrame() {

		jframe = new JFrame("SYSC3110 GROUP-10");
		jframe.setLayout(new BorderLayout());
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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
		newGame.addActionListener(this);
		Game.add(newGame);
		exit = new JMenuItem("Exit");
		exit.addActionListener(this);
		Game.add(exit);
		
		jframe.setVisible(true);
		
		
	}
	
	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource() == newGame) {
			game = new Game();
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new GUIFrame();
	}

}
