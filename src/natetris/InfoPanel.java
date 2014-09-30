package natetris;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

/**
 * The {@code InfoPanel} class is responsible for showing the current game information, such as
 * the next piece that is going to be displayed and the current score.
 */
public class InfoPanel extends JPanel {

  private static final long serialVersionUID = -3849098449596016270L;
  
  /**
   * Fonts properties
   */
  private static final Font LARGE_FONT = new Font("Tahoma", Font.PLAIN, 16);
  private static final Font SMALL_FONT = new Font("Tahoma", Font.PLAIN, 13);
  private static final Color FONT_COLOR = Color.GRAY;
  
  /**
   * The biggest size that a piece may have (piece I)
   */
  private static final int MAX_PIECE_DIMENSION = 4;
  
  /**
   * Constants used throughout the panel for layout
   */
  private static final int TILE_SIZE = Board.TILE_SIZE;
  private static final int SPACE_BETWEEN_STRINGS = 25;
  private static final int PADDING = 5;
  private static final int BORDER_WIDTH = Board.BORDER_WIDTH;
  
  /**
   * The game instance
   */
  private Natetris natetris;
  
  public InfoPanel(Natetris natetris) {
    this.natetris = natetris;
    
    setPreferredSize(new Dimension(Board.PANEL_WIDTH, Board.PANEL_HEIGHT));
    setBackground(Color.BLACK);
  }
  
  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.setColor(Color.WHITE);
    g.setFont(LARGE_FONT);
    int offset = SPACE_BETWEEN_STRINGS;
    /*
     * this enhances drawing methods readability, by considering the 
     * 0,0 axis the beginning of the border limit of the board
     */
    g.translate(BORDER_WIDTH, BORDER_WIDTH);
    
    /*
     * Draws both the rectangle which will hold the next piece to come and
     * the piece itself 
     */
    g.drawRect(0, 0, predictionBoxWidth(), predictionBoxWidth());
    if (natetris.isGameRunning()) {
      Piece piece = natetris.getNextPiece();
      
      int dim = piece.getDimension();
      
      for (int x = 0; x < dim; x++) {
        for (int y = 0; y < dim; y++) {
          if (piece.isTile(x, y, 0)) {
        	  int currX = PADDING + (x * TILE_SIZE);
        	  int currY = PADDING + (y * TILE_SIZE);
	          drawTile(piece, currX, currY, g);
          }
        }
      }
    }
    
    g.setColor(FONT_COLOR);
    offset += predictionBoxWidth(); // strings have to be underneath the prediction square to be visible
    
    String points = "Points: ";
    g.drawString(points, 0, offset);
    g.drawString(Long.toString(natetris.getScore()), g.getFontMetrics().stringWidth(points), offset);
    offset += SPACE_BETWEEN_STRINGS;
    
    g.setFont(SMALL_FONT);
    String movePointersTip = "Move pieces using the pointers keys, OR:";
    String moveLeftTip = "  move left by pressing 'A'";
    String moveRightTip = " move right by pressing 'D'";
    String moveDownTip = "  move down by pressing 'S'";
    g.drawString(movePointersTip, 0, offset += SPACE_BETWEEN_STRINGS);
    g.drawString(moveLeftTip, 0, offset += SPACE_BETWEEN_STRINGS);
    g.drawString(moveRightTip, 0, offset += SPACE_BETWEEN_STRINGS);
    g.drawString(moveDownTip, 0, offset += SPACE_BETWEEN_STRINGS);

  }
  
  private int predictionBoxWidth() {
    return Board.TILE_SIZE * MAX_PIECE_DIMENSION + PADDING;
  }
  
  private void drawTile(Piece piece, int x, int y, Graphics g) {
    g.setColor(piece.getColor());
    g.fillRect(x, y, TILE_SIZE, TILE_SIZE);
  }
  
}
