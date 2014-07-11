package natetris;

import java.awt.BorderLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

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
	private boolean isFirstGame;
	private boolean isGamePaused;
	private boolean isGameOver;
	
	/**
	 * The player's current score 
     */
	private long score;
	
	/**
	 * The quantity of different tile types 
	 */
	public static final int TILES_COUNT = TilePiece.values().length;
	
	/**
	 * The current piece that is falling down
	 */
	private TilePiece currentPiece;

	/**
	 * The next piece that will be on the game 
	 */
	private TilePiece nextPiece;
	
	/**
	 * The column that the current piece is located
	 */
	private int currentCol;
	
	/**
	 * The row that the current piece is located
	 */
	private int currentRow;
	
	/**
	 * The falling piece current rotation
	 */
	private int rotation;
	
	/**
	 * Random generator
	 */
	private Random random;
	
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
		
		addKeyListener(new KeyAdapter() {
			
			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
					// start new game
					case KeyEvent.VK_ENTER:
						if (isGameOver || isFirstGame) {
							resetGame();
						}
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
						
					// pause
					case KeyEvent.VK_P:
						if (isGamePaused) {
							setGamePaused(false);
						} else {
							// TODO clock: stops clock
							setGamePaused(true);
						}
						break;
				}		
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO clock: when dropping-piece button is released, make game get back to original speed
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
		this.random = new Random();
		resetGame();
		this.isFirstGame = true;
		
		while (true) {
			updateGame();
			
			renderGame();
			
			try {
				/* FIXME adjust FPS.
				 * clock 101: Probably going to create a Clock class measuring time between cycles
				 */
				Thread.sleep(2000); 
			} catch (InterruptedException e) {
				e.printStackTrace();
			} 
		}
		
	}
	
	/**
	 * Handles the game's logic
	 */
	private void updateGame() {
		if (board.isPossibleToMovePiece(currentPiece, currentRow + 1, currentCol)) {
			currentRow++;
		} else {
			
		}
	}
	
	/**
	 * Render all game windows
	 */
	private void renderGame() {
		board.repaint();
		infoPanel.repaint();
	}
	
	/**
	 * Sets all default variables to their initial values
	 */
	private void resetGame() {
		this.isFirstGame = false;
		this.isGameOver = false;
		this.isGamePaused = false;
		this.score = 0L;
		this.nextPiece = (TilePiece.values()[random.nextInt(TILES_COUNT)]);
		
		spawnNewPiece();
	}
	
	/**
	 * Creates a new piece and set it as the next piece to fall. 
	 * The previous piece is then placed as the current one.
	 */
	private void spawnNewPiece() {
		this.currentPiece = nextPiece;
		this.rotation = 0;
		this.currentCol = currentPiece.getSpawnCol();
		this.currentRow = currentPiece.getSpawnRow();
		this.nextPiece = (TilePiece.values()[random.nextInt(TILES_COUNT)]);
	}
	
	public boolean isGamePaused() {
		return isGamePaused;
	}

	public boolean isGameOver() {
		return isGameOver;
	}

	public boolean isFirstGame() {
		return isFirstGame;
	}
	
	public void setGamePaused(boolean value) {
		this.isGamePaused = value;
	}
	
	public TilePiece getCurrentPiece() {
		return currentPiece;
	}
	
	public TilePiece getNextPiece() {
		return nextPiece;
	}

	public long getScore() {
		return score;
	}
	
	public int getCurrentCol() {
		return currentCol;
	}
	
	public int getCurrentRow() {
		return currentRow;
	}

	public int getRotation() {
		return rotation;
	}

	public static void main(String[] args) {
		Natetris natetris = new Natetris();
		natetris.startGame();
	}
}
