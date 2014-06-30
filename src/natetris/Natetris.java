package natetris;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class Natetris extends JFrame {

	private static final long serialVersionUID = 6321156896206333624L;
	
	/**
	 * The game board
	 */
	private Board board;
	
	/**
	 *	The information panel, containing general info for the player
	 */
	private InfoPanel infoPanel;
	
	/**
	 * Game control variables.
	 * isNewGame represents if the game hasn't started yet. Occurs only when the game is launched
	 */
	private boolean isNewGame;
	private boolean isGamePaused;
	private boolean isGameOver;
	
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
		
		addKeyListener(new KeyListener() {
			
			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
					// start new game
					case KeyEvent.VK_ENTER:
						
						break;
						
					// move down
					case KeyEvent.VK_NUMPAD2:
					case KeyEvent.VK_S:
					case KeyEvent.VK_DOWN:
						
						break;
						
					// move right
					case KeyEvent.VK_NUMPAD6:
					case KeyEvent.VK_D:
					case KeyEvent.VK_RIGHT:
						
						break;
						
					// move left
					case KeyEvent.VK_NUMPAD4:
					case KeyEvent.VK_A:
					case KeyEvent.VK_LEFT:
						
						break;
						
					// move up
					case KeyEvent.VK_NUMPAD8:
					case KeyEvent.VK_W:
					case KeyEvent.VK_UP:
						
						break;
						
					// pause
					case KeyEvent.VK_P:
						
						break;
						
				}
			}
			
			@Override
			public void keyTyped(KeyEvent e) {
				/* empty block */
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				/* empty block */
			}
		});
		
		this.board = new Board(this);
		this.infoPanel = new InfoPanel(this);
		
		add(board, BorderLayout.WEST);
		add(infoPanel, BorderLayout.EAST);
		
		pack();
		setVisible(true);
	}
	
	/**
	 * The main game loop. 
	 * This will refresh the JPanels and handle the game's logic. 
	 */
	public void startGame() {
		setNewGame(true);
		
		
	}
	
	public boolean isGamePaused() {
		return isGamePaused;
	}

	public void setGamePaused(boolean isGamePaused) {
		this.isGamePaused = isGamePaused;
	}

	public boolean isGameOver() {
		return isGameOver;
	}

	public void setGameOver(boolean isGameOver) {
		this.isGameOver = isGameOver;
	}

	public boolean isNewGame() {
		return isNewGame;
	}

	public void setNewGame(boolean isNewGame) {
		this.isNewGame = isNewGame;
	}

	public static void main(String[] args) {
		Natetris natetris = new Natetris();
		natetris.startGame();
	}

}
