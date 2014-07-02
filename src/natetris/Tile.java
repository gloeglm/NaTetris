package natetris;

import java.awt.Color;

/**
 * Describes and details the properties shared among the pieces.
 */
public enum Tile {
	
	/**
	 * I-shaped tetromino, colored cyan
	 */
	TileI (new Color(0, 255, 230), new boolean[][] {
			{
				false, 	false, 	false, 	false,
				true,	true,	true,	true,
				false, 	false, 	false,	false,
				false,	false,	false,	false
			},
			{
				false,	false,	true,	false,
				false,	false,	true,	false,
				false,	false,	true,	false,
				false,	false,	true,	false
			},
			{
				false,	false,	false,	false,
				false,	false,	false,	false,
				true,	true,	true,	true,
				false,	false,	false,	false
			},
			{
				false,	true, 	false,	false,
				false,	true, 	false,	false,
				false,	true, 	false,	false,
				false,	true, 	false,	false 
			}
		}),

	/**
	 * O-shaped tetromino, colored yellow.
	 */
	TileO(new Color(255, 255, 0), new boolean[][] {
			{
				true,	true, 
				true,	true
			},
			{
				true,	true,
				true,	true
			},
			{
				true,	true, 
				true,	true
			},
			{
				true,	true,
				true,	true
			}
	}),
	
	/**
	 * L-shaped tetromino, colored range. 
	 */
	TileL(new Color(255, 160, 0), new boolean[][] {
			{
				false,	true,	false,
				false,	true,	false,
				false,	true,	true
			},
			{
				false,	false,	false,
				true,	true,	true,
				true,	false,	false
			},
			{
				true,	true,	false,
				false,	true,	false,
				false,	true,	false
			},
			{
				false,	false,	true,
				true,	true,	true,
				false,	false,	false
				
			}
	}), 
	
	/**
	 * J-shaped tetromino, colored blue
	 */
	TileJ(new Color(0, 0, 255), new boolean[][] {
			{
				false,	true,	false,
				false,	true,	false,
				true,	true,	false
			},
			{
				true,	false,	false,
				true,	true,	true,
				false,	false,	false
			},
			{
				false,	true,	true,
				false,	true,	false,
				false,	true,	false
			},
			{
				false,	false,	false,
				true,	true,	true,
				false,	false,	true
			}
	}),
	
	/**
	 * S-shaped tetromino, colored green
	 */
	TileS(new Color(0, 255, 0), new boolean[][] {
			{
				false,	true,	true,
				true,	true,	false,
				false,	false,	false
			},
			{
				true,	false,	false,
				true,	true,	false,
				false,	true,	false
			},
			{
				false,	false,	false,
				false,	true,	true,
				true,	true,	false
			},
			{
				false,	true,	false,
				false,	true,	true,
				false,	false,	true
			}
	}),
	
	/**
	 * Z-shaped tetromino, colored red
	 */
	TileZ(new Color(255, 0, 0), new boolean[][] {
			{
				true,	true,	false,
				false,	true,	true,
				false,	false,	false
			},
			{
				false,	false,	true,
				false,	true,	true,
				false,	true,	false
			},
			{
				false,	false,	false,
				true,	true,	false,
				false,	true,	true
			},
			{
				false,	true,	false,
				true,	true,	false,
				true,	false,	false
			}	
	}),
	
	/**
	 * T-shaped tetromino, colored purple
	 */
	TileT(new Color(170, 0, 255), new boolean[][] {
			{
				true,	true,	true,
				false,	true,	false,
				false,	false,	false
			},
			{
				false,	false,	true,
				false,	true,	true,
				false,	false,	true
			},
			{
				false,	false,	false,
				false,	true,	false,
				true,	true,	true
			},
			{
				true,	false,	false,
				true,	true,	false,
				true,	false,	false
			}	
	});
	
	/**
	 * How the tiles are set on the piece
	 */
	private boolean[][] tiles;
	
	/**
	 * The piece color
	 * @param tiles
	 */
	private Color color; 
	
	private Tile (Color color, boolean [][] tiles) {
		setColor(color);
		setTiles(tiles);
	}

	public boolean[][] getTiles() {
		return tiles;
	}

	public void setTiles(boolean[][] tiles) {
		this.tiles = tiles;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
}
