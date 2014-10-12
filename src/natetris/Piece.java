package natetris;

import java.awt.Color;

/**
 * The {@code Piece} enum describes and details the properties shared among the pieces.
 */
public enum Piece {
	
	/**
	 * I-shaped tetromino, colored cyan
	 */
	TileI (4, new Color(0, 255, 230), new boolean[][] {
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
	TileO(2, new Color(255, 255, 0), new boolean[][] {
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
	 * L-shaped tetromino, colored orange. 
	 */
	TileL(3, new Color(255, 160, 0), new boolean[][] {
			{
				false,	false,	true,
				true,	true,	true,
				false,	false,	false
				
			},	
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
			}
	}), 
	
	/**
	 * J-shaped tetromino, colored blue
	 */
	TileJ(3, new Color(0, 0, 255), new boolean[][] {
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
			},
			{
				false,	true,	false,
				false,	true,	false,
				true,	true,	false
			}
	}),
	
	/**
	 * S-shaped tetromino, colored green
	 */
	TileS(3, new Color(0, 255, 0), new boolean[][] {
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
	TileZ(3, new Color(255, 0, 0), new boolean[][] {
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
	TileT(3, new Color(170, 0, 255), new boolean[][] {
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
	 */
	private Color color;
	
	/**
	 * The dimension of the piece
	 */
	private int dimension;
	
	/**
	 * The column that the piece will be originally spawned
	 */
	private int spawnCol;
	
	/**
	 * The row that the piece will be originally spawned
	 */
	private int spawnRow;
	
	private Piece (int dimension, Color color, boolean [][] tiles) {
		this.color = color;
		this.tiles = tiles;
		this.dimension = dimension;
		this.spawnCol = 5; // XXX TEMPORARY -- EACH PIECE WILL HAVE A DIFFERENT SPAWN LOCATION IN THE FUTURE
		this.spawnRow = 0;
	}
	
	public boolean isTile(int x, int y, int rotation) {
		return (tiles[rotation][y * dimension + x]);
	}
	
	/**
	 * Scans upside down the piece searching for a tile
	 * returns when a tile is found.
	 * @param rotation - current rotation of the piece
	 * @return the first found tile's column location, starting from up-right ending in up-left
	 */
	public int getLeftmostTile(int rotation) {
		for (int x = 0; x < dimension; x++) {
			for (int y = dimension - 1; y >= 0; y--) {
				if (isTile(x, y, rotation)) {
					return x; // returns the current column
				}
			}
		}
		return -1;
	}
	
 	/**
	 * Scans from bottom to up the piece searching for a tile 
	 * returns when a tile is found.
	 * @param rotation - current rotation of the piece
	 * @return the first found tile's column location, starting from bottom-right to up-left
	 */
	public int getRightmostTile(int rotation) {
		for (int x = dimension - 1; x >= 0; x--) {
			for (int y = dimension - 1; y >= 0; y--) {
				if (isTile(x, y, rotation)) {
					return x; // returns the current column
				}
			}
		}
		return -1;
	}
	
	/**
	 * Scans the piece from right to left searching for a tile, and 
	 * returns when a tile is found.
	 * @param rotation - current rotation of the piece
	 * @return the first found tile's location, starting from bottom-right to up-left
	 */
	public int getLowermostTile(int rotation) {
		for (int y = dimension - 1; y >= 0; y--) {
			for (int x = dimension - 1; x >= 0; x--) {
				if (isTile(x, y, rotation)) {
					return y;
				}
			}
		}
		return -1;
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
	
	/**
	 * @param alpha - the alpha value that the piece will have
	 * @return a {@code Color} object containing the semi-transparent
	 * version of this piece's original color
	 */
	public Color getGhostColor(float alpha) {
		float[] RGB = this.color.getRGBColorComponents(null);
		return new Color(RGB[0], RGB[1], RGB[2], alpha);
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public int getDimension() {
		return dimension;
	}
	
	public int getSpawnCol() {
		return spawnCol;
	}

	public int getSpawnRow() {
		return spawnRow;
	}
}
