package natetris;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class InfoPanel extends JPanel {

	private static final long serialVersionUID = -3849098449596016270L;
	
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
	protected void paintComponent(Graphics arg0) {
		super.paintComponent(arg0);
	}
	
	
}
