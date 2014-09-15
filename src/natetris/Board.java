package natetris;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Board extends JPanel {

	private static final long serialVersionUID = 4858532419981185927L;
	
	/**
	 * The number of visible rows in the board. Two of them need to stay hid
	 * so that the piece doesn't show up magically 
	 */
	public static final int HIDDEN_ROW_COUNT = 2;

	public static final int VISIBLE_ROW_COUNT = 20;
	
	public static final int ROW_COUNT = VISIBLE_ROW_COUNT + HIDDEN_ROW_COUNT;
	
	public static final int COL_COUNT = 10;
	
	/**
	 * The size of each individual tile
	 */
	public static final int TILE_SIZE = 25;
	
	/**
	 * The pixel distance between the Board JPanel object and the actual border line draw.
	 * As this is a 2D game, the board will have only 4 borders: two placed -
	 * vertically and two placed horizontally, producing, then, a rectangle.
	 */
	public static final int BORDER_WIDTH = 10;
	
	/**
	 * The dimensions of the board panel
	 */
	public static final int PANEL_WIDTH = (BORDER_WIDTH * 2) + (COL_COUNT * TILE_SIZE);
	public static final int PANEL_HEIGHT = (BORDER_WIDTH * 2) + (VISIBLE_ROW_COUNT * TILE_SIZE);
	
	/**
	 * The dimensions of the actually visible board
	 */
	public static final int BOARD_WIDTH = PANEL_WIDTH - (BORDER_WIDTH * 2);
	public static final int BOARD_HEIGHT = PANEL_HEIGHT - (BORDER_WIDTH * 2);
	
	/**
	 * The central position of the game board
	 */
	public static final int CENTER_X = BOARD_WIDTH / 2;
	public static final int CENTER_Y = BOARD_HEIGHT / 2;
	
	/**
	 * Fonts used 
	 */
	private static final Font LARGE_FONT = new Font("Tahoma", Font.PLAIN, 16);
	private static final Font SMALL_FONT = new Font("Tahoma", Font.PLAIN, 11);
	 
	/**
	 * The group of tiles that compose the entire board
	 */
	private Piece[][] tiles;
	
	private Natetris natetris;
	
	public Board(Natetris natetris) {
		this.natetris = natetris;
		tiles = new Piece[COL_COUNT][ROW_COUNT];
		setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
		setBackground(Color.BLACK);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		/*
		 * this enhances drawing methods readability, by considering the 
		 * 0,0 axis the beginning of the border limit of the board
		 */
		g.translate(BORDER_WIDTH, BORDER_WIDTH);
		
		if (natetris.isGamePaused()) {
			g.setFont(LARGE_FONT);
			g.setColor(Color.WHITE);
			String message = "Game Paused";
			g.drawString(message, CENTER_X - g.getFontMetrics().stringWidth(message) / 2, CENTER_Y);
		} else if (natetris.isGameOver() || natetris.isFirstGame()) {
			g.setFont(SMALL_FONT);
			g.setColor(Color.WHITE);
			String message = "To start a new game, press the ENTER key";
			g.drawString(message, CENTER_X - g.getFontMetrics().stringWidth(message) / 2, CENTER_Y);
			
			if (natetris.isGameOver()) {
				g.setFont(LARGE_FONT);
				String gameOver = "Game over :(";
				g.drawString(gameOver, CENTER_X - g.getFontMetrics().stringWidth(gameOver) / 2, CENTER_Y - 30);
			}
		} else {
			// game is running
			
			/**
			 * draws landed pieces
			 */
			for (int x = 0; x < COL_COUNT; x++) {
				for (int y = HIDDEN_ROW_COUNT; y < ROW_COUNT; y++) {
					Piece piece = tiles[x][y];
					if (isOccupied(x, y)) {
						drawTile(piece, x, (y - HIDDEN_ROW_COUNT), g);
					}
				}
			}
			
			/**
			 * draws current piece
			 */
			Piece currentPiece = natetris.getCurrentPiece();
			int currentDirection = natetris.getPieceRotation();
			int currentRow = natetris.getCurrentRow();
			int currentCol = natetris.getCurrentCol();
			for (int col = 0; col < currentPiece.getDimension(); col++) {
				for (int row = 0; row < currentPiece.getDimension(); row++) {
					if (currentPiece.isTile(col, row, currentDirection) && (currentRow + row) >= 2) {
						drawTile(currentPiece, currentCol + col, (currentRow + row - HIDDEN_ROW_COUNT), g);
					}
				}
			}
			
			/*
			 * draws the board itself, which is basically made of empty squares.
			 */
			g.setColor(Color.DARK_GRAY);
			for (int x = 1; x < VISIBLE_ROW_COUNT; x++) {
				g.drawLine(0, (x * TILE_SIZE), BOARD_WIDTH, (x * TILE_SIZE));
			}
			for (int y = 1; y < COL_COUNT; y++) {
				g.drawLine((y * TILE_SIZE), 0, (y * TILE_SIZE), BOARD_HEIGHT);
			}
		}
		
		/*
		 * draws the board borders
		 */
		g.setColor(Color.WHITE);
		g.drawRect(0, 0, BOARD_WIDTH, BOARD_HEIGHT);
	}
	
	public void addPieceToTheBoard(Piece piece, int boardCol, int boardRow, int rotation) {
		for (int pieceCol = 0; pieceCol < piece.getDimension(); pieceCol++) {
			for (int pieceRow = 0; pieceRow < piece.getDimension(); pieceRow++) {
				if (piece.isTile(pieceCol, pieceRow, rotation)) {
					addPiece(piece, boardCol + pieceCol, boardRow + pieceRow);
				}
			}
		}
	}
	
	private void addPiece(Piece piece, int col, int row) {
		tiles[col][row] = piece;
	}

	public boolean isPossibleToMovePiece(Piece piece, int col, int row, int pieceRotation) {
		/*
		 *  check if it is a valid column
		 */
		if (col < (-piece.getLeftmostTile(pieceRotation)) || (col + piece.getRightmostTile(pieceRotation)) >= COL_COUNT) {
			return false;
		}
		/*
		 *  check if it is a valid row
		 */
		if ((row + piece.getLowermostTile(pieceRotation)) >= ROW_COUNT) {
			return false;
		}
		
		/*
		 * Checks if two pieces collided
		 */
		for (int pieceCol = 0; pieceCol < piece.getDimension(); pieceCol++) {
			for (int pieceRow = 0; pieceRow < piece.getDimension(); pieceRow++) {
				if (piece.isTile(pieceCol, pieceRow, pieceRotation) && tiles[col + pieceCol][row + pieceRow] != null) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	/**
	 * Clears the entire board, setting all slots to null
	 */
	public void clear() {
		for (int col = 0; col < tiles.length; col++) {
			for (int row = 0; row < tiles[col].length; row++) {
				tiles[col][row] = null;
			}
		}
	}
	
	/**
	 * Checks if there are lines completely filled up, so that they can
	 * be destroyed
	 * @param piece - current piece at the game
	 * @return number of rows completely filled up
	 */
	public int checkLines(Piece piece) {
		int clearedLines = 0;
		for (int row = 0; row < ROW_COUNT; row++) {
			if (filledLine(row)) {
				clearedLines++;
			}
		}
		return clearedLines;
	}
	
	/**
	 * Checks if the line is complete horizontally
	 * @param row - the checked row
	 * @return true if complete, false otherwise
	 */
	private boolean filledLine(int row) {
		for (int col = 0; col < COL_COUNT; col++) {
			if (!isOccupied(col, row)) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Checks if there is a piece hanging at the determined position
	 * @param x - column to be tested
	 * @param y - row to be tested
	 * @return
	 */
	private boolean isOccupied(int x, int y) {
		return (tiles[x][y] != null);
	}
	
	private void drawTile(Piece piece, int x, int y, Graphics g) {
		g.setColor(piece.getColor());
		g.fillRect(x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
	}
}
