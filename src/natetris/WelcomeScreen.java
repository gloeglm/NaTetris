package natetris;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * The first screen that appears when you load the game. It shows only once, 
 * at the initialization.
 * @author natan
 *
 */
public class WelcomeScreen extends JFrame {
	
	private static final long serialVersionUID = 1056563433680760508L;
	
	/**
	 * The JPanel that represents the 
	 */
	private welcomePanel welcomeScreen;
	
	/**
	 * Variable that controls the welcome screen. When set to false, the 
	 * welcome screen should disappear and the game screen should appear instead.
	 */
	private boolean isRunning = true;
	
	/**
	 * The game instance
	 */
	Natetris natetris;
	
	public WelcomeScreen(final Natetris natetris) {
		/*
		 * Tries to load WelcomeScreen's images
		 */
		BufferedImage welcomeImage = null;
		try {
			welcomeImage = ImageIO.read(new File(System.getProperty("user.dir") + "/img/welcome.jpg"));
		} catch (IOException ioe) {
			System.err.println("Could not open images: " + ioe);
		}

		/*
		 * Initializes class members
		 */
		this.natetris = natetris;
		this.welcomeScreen = new welcomePanel(welcomeImage);
		
		/*
		 * Sets the size of the main JPanel to the size of our welcome image,
		 * then assigns our image to it
		 */
		welcomeScreen.setPreferredSize(new Dimension(welcomeImage.getWidth(), welcomeImage.getHeight()));
		
		/*
		 * adds our keyListener, so that when ENTER is pressed, we see the game board and the 
		 * welcome window disappears
		 */
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				switch(e.getKeyCode()) {
					case KeyEvent.VK_ENTER:
						natetris.setVisible(true);
						natetris.resetGame();
						setVisible(false);
				}
			}
		});
		
		add(welcomeScreen);
		pack();
		setVisible(true);
	}
	
	/**
	 * Helper class that will contain the image displayed in our welcome screen
	 * @author natan
	 *
	 */
	private class welcomePanel extends JPanel {
		BufferedImage welcomeImage = null;
		
		public welcomePanel(BufferedImage bufferedImage) {
			this.welcomeImage = bufferedImage;
		}
		
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(welcomeImage, 0, 0, null);
		}
	}
}
