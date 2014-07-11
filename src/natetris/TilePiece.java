package natetris;

import java.awt.Color;

/**
 * Describes and details the properties shared among the pieces.
 */
public enum TilePiece {
	
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
	TileJ(3, new Color(0, 0, 255), new boolean[][] {
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
	
	private TilePiece (int dimension, Color color, boolean [][] tiles) {
		this.color = color;
		this.tiles = tiles;
		this.dimension = dimension;
		this.spawnCol = 4; // XXX TEMPORARY -- EACH PIECE WILL HAVE A DIFFERENT SPAWN LOCATION IN THE FUTURE
		this.spawnRow = 0; // XXX TEMPORARY -- EACH PIECE WILL HAVE A DIFFERENT SPAWN LOCATION IN THE FUTURE
	}
	
	public boolean isTile(int direction, int x, int y) {
		return (tiles[direction][x * direction + y ]);
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
