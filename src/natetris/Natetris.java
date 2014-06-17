package natetris;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class Natetris extends JFrame {
	
	
	/**
	 * Creates a new instance of the game. 
	 * This sets the properties of the JFrame that will hold the game section and
	 * adds a key listener to it.
	 */
	public Natetris() {
		/*
		 * basic window properties
		 */
		super("NaTetris!");
		setLayout(new BorderLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		
		pack();
		setVisible(true);
	}
	
	public void startGame() {
		
	}
	
	public static void main(String[] args) {
		Natetris natetris = new Natetris();
		natetris.startGame();
	}

}
