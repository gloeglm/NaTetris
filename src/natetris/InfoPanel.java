package natetris;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
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
   * The biggest size that a piece may have
   */
  private static final int MAX_PIECE_DIMENSION = Piece.MAX_PIECE_DIMENSION;
  
  /**
   * Constants used throughout the panel for layout
   */
  private static final int TILE_SIZE = Board.TILE_SIZE;
  private static final int SPACE_BETWEEN_STRINGS = 25;
  private static final int PADDING_BOX = 5;
  private static final int BORDER_WIDTH = Board.BORDER_WIDTH;

  /**
   * Represents if the player has just scored. 
   * This is used to manipulate the visibility of a picture for a determined length of time.
   */
  private boolean hasJustScored = false;
  
  /**
   * Natan's face that will pop up when player scores
   */
  private JLabel natanImage = null;
  
  /**
   * Gets the current time in milliseconds to manipulate natanImage's appearance
   */
  private long lastScoreTime = 0L;
  
  /**
   * The game instance
   */
  private Natetris natetris;
  
  public InfoPanel(Natetris natetris) {
    this.natetris = natetris;
    
    /*
	 * Tries to load the image that will be shown when player scores
	 */
    Icon animatedGif = createImageIcon("img/deal_with.gif");
	this.natanImage = new JLabel(animatedGif);
	
	/*
	 * The image will be displayed only when player hits a score, so it starts out as hidden
	 */
	natanImage.setVisible(false);	
	this.add(natanImage);
	
	setLayout(new FlowLayout(FlowLayout.LEFT));
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
     * Enhances drawing methods readability, by considering the 
     * 0,0 axis as the beginning of the border limit of the board
     */
    g.translate(BORDER_WIDTH, BORDER_WIDTH);
    
    /*
     * Draws both the rectangle which will hold the next piece to come and
     * the piece itself 
     */
    g.drawRect(0, 0, predictionBoxWidth(), predictionBoxWidth());
    
    if (natetris.isGameRunning()) {
    	if (hasJustScored) {
    		drawsSuccessImage(g);
    	} else {
    		drawsNextPiece(g);
    	}
    }
    
    offset += predictionBoxWidth(); // strings have to be underneath the prediction square to be visible
    
    g.setColor(FONT_COLOR);
    String points = "Points: ";
    g.drawString(points, 0, offset);
    g.drawString(Long.toString(natetris.getScore()), g.getFontMetrics().stringWidth(points), offset);
    offset += SPACE_BETWEEN_STRINGS;
    
    g.setFont(SMALL_FONT);
    String movePointersTip = "\tMove pieces using the pointers keys, OR:";
    String moveLeftTip = "\tmove left by pressing 'A'";
    String moveRightTip = "\tmove right by pressing 'D'";
    String moveDownTip = "\tmove down by pressing 'S'";
    g.drawString(movePointersTip, 0, offset += SPACE_BETWEEN_STRINGS);
    g.drawString(moveLeftTip, 0, offset += SPACE_BETWEEN_STRINGS);
    g.drawString(moveRightTip, 0, offset += SPACE_BETWEEN_STRINGS);
    g.drawString(moveDownTip, 0, offset += SPACE_BETWEEN_STRINGS);

  }
  
  /**
   * Draws the next piece that will come in the game
   * @param Graphics g
   */
  private void drawsNextPiece(Graphics g) { 
	  Piece piece = natetris.getNextPiece();
	      
	  int dim = piece.getDimension();
	  
	  for (int x = 0; x < dim; x++) {
	    for (int y = 0; y < dim; y++) {
	      if (piece.isTile(x, y, 0)) {
	    	  int currX = PADDING_BOX + (x * TILE_SIZE);
	    	  int currY = PADDING_BOX + (y * TILE_SIZE);
	          drawTile(piece, currX, currY, g);
	      }
	    }
	  }
  }
  
  /**
   * Draws the image the will be shown when the player has just scored
   * @param Graphics g
   */
  private void drawsSuccessImage(Graphics g) {
	  long gifDuration = 5000L;
	  if (gifDuration > (System.currentTimeMillis() - this.lastScoreTime)) {
		  natanImage.setVisible(true);
	  } else {
		  natanImage.setVisible(false);
		  hasJustScored = false;
	  }
  }
  
  /**
   * @return the prediction box's width
   */
  private int predictionBoxWidth() {
	  return Board.TILE_SIZE * MAX_PIECE_DIMENSION + PADDING_BOX;
  }
  
  /**
   * Draws the next piece that will fall
   * @param piece
   * @param x
   * @param y
   * @param g
   */
  private void drawTile(Piece piece, int x, int y, Graphics g) {
	  g.setColor(piece.getColor());
	  g.fillRect(x, y, TILE_SIZE, TILE_SIZE);
  }
  
  /**
   * If the player has just scored in the game, sets up the flag 
   * so that media can be displayed
   */
  public void setPlayerJustScored(boolean value) {
	  this.lastScoreTime = System.currentTimeMillis();
	  this.hasJustScored = value;
  }
  
  /**
   * Reads an image from path
   * @param path
   * @return a new ImageIcon containing the image read from path
   */
  private ImageIcon createImageIcon(String path) {
	 URL imgURL = InfoPanel.class.getClassLoader().getResource(path);
	  if (imgURL != null) {
		  return new ImageIcon(imgURL);
	  } else {
		  System.err.println("Could not load image in " + path);
	  }
	 return null;
  }
  
}
