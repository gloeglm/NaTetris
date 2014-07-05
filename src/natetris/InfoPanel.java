package natetris;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

public class InfoPanel extends JPanel {

	private static final long serialVersionUID = -3849098449596016270L;
	
	/**
	 * Fonts used 
	 */
	private static final Font LARGE_FONT = new Font("Tahoma", Font.PLAIN, 16);
	private static final Font SMALL_FONT = new Font("Tahoma", Font.PLAIN, 13);
	
	/**
	 * Padding used for next piece's box
	 */
	private static final int PADDING = 5;
	
	/**
	 * Invisible panel's border 
	 */
	private static final int BORDER_WIDTH = 5;
	
	/**
	 * The biggest size that a piece may have
	 */
	private static final int BIGGEST_PIECE_SIZE = 4;
	
	/**
	 * The space between the panel's strings. Useful for setting information strings dynamically.
	 */
	private static final int SPACE_BETWEEN_STRINGS = LARGE_FONT.getSize() + 10;
	
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
		 * 
		 */
		String nextPieceMessage = "Next piece: ";
		
		g.drawRect(g.getFontMetrics().stringWidth(nextPieceMessage), offset / 2, getPredictionSquareEdge(), getPredictionSquareEdge());
		g.drawString(nextPieceMessage, 0, offset += SPACE_BETWEEN_STRINGS);
		
		offset += getPredictionSquareEdge(); // strings have to be underneath the prediction square to be visible
		
		String points = "Points: ";
		g.drawString(points, 0, offset);
		g.drawString(Long.toString(Natetris.getScore()), g.getFontMetrics().stringWidth(points), offset);
		offset += SPACE_BETWEEN_STRINGS;
		
		g.setFont(SMALL_FONT);
		String movePointersTip = "Move pieces using the pointers keys, OR:";
		String moveLeftTip = "	move left by pressing 'A'";
		String moveRightTip = "	move right by pressing 'D'";
		String moveDownTip = "	move down by pressing 'S'";
		g.drawString(movePointersTip, 0, offset += SPACE_BETWEEN_STRINGS);
		g.drawString(moveLeftTip, 0, offset += SPACE_BETWEEN_STRINGS);
		g.drawString(moveRightTip, 0, offset += SPACE_BETWEEN_STRINGS);
		g.drawString(moveDownTip, 0, offset += SPACE_BETWEEN_STRINGS);

	}
	
	private int getPredictionSquareEdge() {
		return Board.TILE_SIZE * BIGGEST_PIECE_SIZE + PADDING;
	}
	
}
