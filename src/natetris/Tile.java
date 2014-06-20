package natetris;

/**
 * Describes the properties that the pieces share among each other and how they are detailed.
 */
public enum Tile {
	
	TileI (new boolean[][] {
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
		
	TileO(new boolean[][] {
			{
				true,	true, 	true, 	true,
				true,	true,	true,	true
			},
			{
				true,	true, 	true, 	true,
				true,	true,	true,	true
			},
			{
				true,	true, 	true, 	true,
				true,	true,	true,	true
			},
			{
				true,	true, 	true, 	true,
				true,	true,	true,	true
			},
	});
	
	// TODO TileL, TileJ, TileS, TileZ, TileT
	
	/**
	 * How the tiles are set on the piece
	 */
	private boolean[][] tiles;
	
	
	private Tile (boolean [][] tiles) {
		this.tiles = tiles;
	}
	
}
