package natetris;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.lang.reflect.Array;
import java.util.Arrays;

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
	public static final int ROW_COUNT = 20;
	
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
	public static final int PANEL_HEIGHT = (BORDER_WIDTH * 2) + (ROW_COUNT * TILE_SIZE);
	
	/**
	 * The dimensions of the actual shown board
	 */
	public static final int BOARD_WIDTH = PANEL_WIDTH - (BORDER_WIDTH * 2);
	public static final int BOARD_HEIGHT = PANEL_HEIGHT - (BORDER_WIDTH * 2);
	
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
		
		g.setColor(Color.WHITE);
		
		/*
		 * draws the board itself, which is basically made of squares.
		 */
		g.setColor(Color.DARK_GRAY);
		for (int x = 1; x < ROW_COUNT; x++) {
			g.drawLine(BORDER_WIDTH, (x * TILE_SIZE) + BORDER_WIDTH , BOARD_WIDTH + BORDER_WIDTH, (x * TILE_SIZE) + BORDER_WIDTH);
		}
		for (int y = 1; y < COL_COUNT; y++) {
			g.drawLine((y * TILE_SIZE) + BORDER_WIDTH, BORDER_WIDTH, (y * TILE_SIZE) + BORDER_WIDTH, BOARD_HEIGHT + BORDER_WIDTH);
		}
		
		/*
		 * draws the board borders
		 */
		g.setColor(Color.WHITE);
		g.drawRect(BORDER_WIDTH, BORDER_WIDTH, BOARD_WIDTH, BOARD_HEIGHT);
	}
	
}