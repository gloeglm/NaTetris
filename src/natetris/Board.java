package natetris;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Board extends JPanel {

	private static final long serialVersionUID = 4858532419981185927L;
	
	/**
	 * The number of columns in the board
	 */
	public static final int COL_COUNT = 10;
	
	/**
	 * The number of rows in the board
	 */
	public static final int ROW_COUNT = 22;
	
	/**
	 * The number of visible rows in the board
	 */
	public static final int VISIBLE_ROW_COUNT = 20;
	
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
	 * The dimensions of the actual shown board
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
	private Tile[] tiles;
	
	/**
	 * The game instance
	 */
	private Natetris natetris;
	
	public Board(Natetris natetris) {
		this.natetris = natetris;
		this.tiles = new Tile[ROW_COUNT * COL_COUNT];
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
			
			for (int x = 0; x < BOARD_WIDTH; x++) {
				for (int y = 0; y < BOARD_HEIGHT; y++) {
					
				}
			}
			
			/*
			 * draws the board itself, which is basically made of squares.
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
	
}
