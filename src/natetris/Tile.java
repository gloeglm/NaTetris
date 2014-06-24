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
	
	TileL(new boolean[][] {
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
	
	TileJ(new boolean[][] {
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
	
	TileS(new boolean[][] {
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
	
	TileZ(new boolean[][] {
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
	
	TileT(new boolean[][] {
			{
				true,	true,	true,
				false,	true,	false,
				false,	true,	false
			},
			{
				false,	false,	true,
				true,	true,	true,
				false,	false,	true
			},
			{
				false,	true,	false,
				false,	true,	false,
				true,	true,	true
			},
			{
				true,	false,	false,
				true,	true,	true,
				true,	false,	false
			}	
	});
	
	/**
	 * How the tiles are set on the piece
	 */
	private boolean[][] tiles;
	
	
	private Tile (boolean [][] tiles) {
		this.tiles = tiles;
	}
	
}
