package natetris;

import java.awt.BorderLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

import javax.swing.JFrame;

/**
 * The {@code Natetris} class is responsible for the flow control of the game, setting up key listeners and
 * time control.
 * @author natan
 *
 */
public class Natetris extends JFrame {

	private static final long serialVersionUID = 6321156896206333624L;
	
	/**
	 * The number of milliseconds between each frame to be displayed
	 */
	private static final long FRAME_RATE = 1000L / 50L;
	
	/**
	 * The quantity of different kinds of pieces
	 */
	public static final int PIECES_COUNT = Piece.values().length;
	
	/**
	 * The game board
	 */
	private Board board;
	
	/**
	 *	The information panel, containing general info for the player
	 */
	private InfoPanel infoPanel;
	
	/**
	 * The welcome screen that appears when you first start the game
	 */
	private WelcomeScreen welcomeScreen;
	
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
	private int score;
	
	/**
	 * The current piece that is falling down
	 */
	private Piece currentPiece;

	/**
	 * The next piece that will be on the game 
	 */
	private Piece nextPiece;
	
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
	private int currentRotation;
	
	/**
	 * The timer of the game, which controls the timer between game cycles
	 */
	private Timer timer;
	
	/**
	 * Represents the cooldown that happen when a piece hits an obstacle at super-speed, 
	 * so that the next piece doesn't come down immediately at a high speed
	 */
	private int fallingCooldown;
	
	/**
	 * The values that define the game update speed. The default speed 
	 * is replaced with the faster speed whenever the player hits the accelerating 
	 * key, and then gets back to normal whenever the player releases such key. 
	 */
	private static float defaultSpeed = 1.0f;
	private static float fastSpeed = 25.0f;
	
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
						if (isGameOver) {
							resetGame();
						}
						break;
						
					// move down
					case KeyEvent.VK_NUMPAD2:
					case KeyEvent.VK_S:
					case KeyEvent.VK_DOWN:
						if (!isGamePaused && fallingCooldown == 0) {
							timer.setCyclesPerSecond(fastSpeed);
						}
						break;
						
					// move right
					case KeyEvent.VK_NUMPAD6:
					case KeyEvent.VK_D:
					case KeyEvent.VK_RIGHT:
						if (!isGamePaused && board.isPossibleToMovePiece(currentPiece, currentCol+1, currentRow, currentRotation)) {
							currentCol++;
						}
						break;
						
					// move left
					case KeyEvent.VK_NUMPAD4:
					case KeyEvent.VK_A:
					case KeyEvent.VK_LEFT:
						if (!isGamePaused && board.isPossibleToMovePiece(currentPiece, currentCol-1, currentRow, currentRotation)) {
							currentCol--;
						}
						break;
					
					// rotate anticlockwise
					case KeyEvent.VK_Q:
						if (!isGamePaused) {
							rotateCurrentPiece((currentRotation == 0) ? 3 : currentRotation-1);
						}
						break;
						
					// rotate clockwise
					case KeyEvent.VK_E:
						if (!isGamePaused) {
							rotateCurrentPiece((currentRotation == 3) ? 0 : currentRotation+1);
						}
						break;
						
					// pause
					case KeyEvent.VK_P:
						if (isGamePaused) {
							setGamePaused(false);
						} else {
							setGamePaused(true);
						}
						break;
				}
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_S ||
					e.getKeyCode() == KeyEvent.VK_DOWN ||
					e.getKeyCode() == KeyEvent.VK_NUMPAD2) {
					timer.setCyclesPerSecond(defaultSpeed);
					timer.reset();
				}
			}
		});
		
		this.board = new Board(this);
		this.infoPanel = new InfoPanel(this);
		add(board, BorderLayout.WEST);
		add(infoPanel, BorderLayout.EAST);
		pack();
		setLocationRelativeTo(null); // places the JFrame at the center of the screen
	}
	
	/**
	 * The main game loop. 
	 * This will refresh the JPanels and handle the game's logic. 
	 */
	public void startGame() {
		/*
		 * displays our welcome screen
		 */
		this.welcomeScreen = new WelcomeScreen(this);
		
		this.random = new Random();
		this.isFirstGame = true;
		this.timer = new Timer(defaultSpeed);
		
		timer.setPaused(true);
		
		while (true) {
			/*
			 * we will keep track on how much time was needed for the 
			 * game and graphics update to complete, and discount that 
			 * on the frame rate control
			 */
			long begin = System.nanoTime();
			timer.update();
			
			if (timer.completedOneCycle()) {
				updateGame();
			}
			renderGame();
			
			/*
			 * Decrement cooldown if needed, so that the current piece 
			 * gets able down quickly again
			 */
			if (fallingCooldown > 0) {
				fallingCooldown--;
			}
			
			long delta = (System.nanoTime() - begin) / 1000000L;
			try {
				Thread.sleep(FRAME_RATE - delta);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} 
		}
		
	}
	
	/**
	 * Handles the game's logic
	 */
	private void updateGame() {
		if (board.isPossibleToMovePiece(currentPiece, currentCol, currentRow + 1, currentRotation)) {
			currentRow++;
		} else {
			/*
			 * Piece either hit the ground or another piece, so we add it to the board and get a new one :-) 
			 */
			board.addPieceToTheBoard(currentPiece, currentCol, currentRow, currentRotation);
			
			/*
			 * Get our score updated by shifting left the default value by the number of cleared lines.
			 */
			int clearedLines = board.checkLines(currentPiece);
			if (clearedLines > 0) {
				score += 75 << clearedLines;
			}
			
			/*
			 * Sets the cool down to x, which means that it will run (x * FRAME_RATE) times
			 */
			fallingCooldown = 30;
			
			/*
			 * set the timer to normal speed, so that if the player is still holding 's' the next
			 * piece will only speed up again when fallingCooldown gets to 0
			 */
			timer.reset();
			timer.setCyclesPerSecond(defaultSpeed);
			
			if (!isGameOver) {
				spawnNewPiece();
			}
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
	public void resetGame() {
		this.isFirstGame = false;
		this.isGameOver = false;
		this.isGamePaused = false;
		this.score = 0;
		this.nextPiece = Piece.values()[random.nextInt(PIECES_COUNT)];
		this.board.clear();
		this.timer.reset();
		this.fallingCooldown = 0;
		spawnNewPiece();
	}
	
	/**
	 * Creates a new piece and set it as the next piece to fall. 
	 * The previous piece is then placed as the current one.
	 */
	private void spawnNewPiece() {
		this.currentPiece = nextPiece;
		this.currentRotation = 0;
		this.currentCol = currentPiece.getSpawnCol();
		this.currentRow = currentPiece.getSpawnRow();
		this.nextPiece = Piece.values()[random.nextInt(PIECES_COUNT)];
		
		/*
		 * if current piece already spawned in an invalid location, the game is over
		 */
		if (!board.isPossibleToMovePiece(currentPiece, currentCol, currentRow, currentRotation)) {
			isGameOver = true;
		}
	}
	
	/**
	 * Rotates the piece to a new direction (clockwise or anticlockwise). If, with the new direction, 
	 * the piece overlaps the board, it is moved accordingly to the center of the board so that it doesn't 
	 * go out of bounds.
	 * @param newDirection is the direction in which the piece will be rotated
	 */
	public void rotateCurrentPiece(int newDirection) {
		/* 
		 * newCol is used to rearrange the piece's location 
		 * if it's rotated near enough the edge,
		 * so that the piece doesn't clip out of the board
		 */
		int newCol = currentCol;
		int newRow = currentRow;
		
		/*
		 * get the insets of the piece to check if the piece clips out of the board 
		 */
		int left = currentPiece.getLeftmostTile(newDirection);
		int right = currentPiece.getRightmostTile(newDirection);
		
		// avoids the piece from overflowing the board when rotated near left edge
		while ((newCol + left) < 0) {
			newCol++;
		}
		
		// avoids the piece from overflowing the board when rotated near right edge
		while ((newCol + right) >= Board.COL_COUNT) {
			newCol--;
		} 
		
		if (board.isPossibleToMovePiece(currentPiece,  newCol, newRow, newDirection)) {
			this.currentRotation = newDirection;
			this.currentCol = newCol;
			this.currentRow = newRow;
		}
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
	
	public Piece getCurrentPiece() {
		return currentPiece;
	}
	
	public Piece getNextPiece() {
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

	public int getPieceRotation() {
		return currentRotation;
	}
	
	private void setGamePaused(boolean value) {
		timer.setPaused(value);
		isGamePaused = value;
	}

	public boolean isGameRunning() {
		return !(isFirstGame || isGameOver || isGamePaused);
	}
	
	public static void main(String[] args) {
		Natetris natetris = new Natetris();
		natetris.startGame();
	}
}
