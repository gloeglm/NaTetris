package natetris;

import java.awt.Color;
import java.awt.Dimension;
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
	public static final int ROW_COUNT = 20;
	
	/**
	 * The size of each individual tile
	 */
	public static final int TILE_SIZE = 25;
	
	/**
	 * The dimensions of the board panel
	 */
	public static final int PANEL_WIDTH = COL_COUNT * TILE_SIZE;
	public static final int PANEL_HEIGHT = ROW_COUNT * TILE_SIZE;
	
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
		
		
	}
	
}
