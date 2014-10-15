package utils;

import java.awt.BorderLayout;
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

import natetris.Natetris;

/**
 * The first screen that appears when you load the game. It shows only once, 
 * at the initialization.
 * @author natan
 */
public class WelcomeScreen extends JFrame {
	
	private static final long serialVersionUID = 1056563433680760508L;
	
	/**
	 * The JPanel that contains the welcome screen main image
	 */
	private WelcomePanel welcomePanel;
	
	/**
	 * The game instance
	 */
	Natetris natetris;
	
	public WelcomeScreen(final Natetris natetris) {
		/*
		 * basic window properties
		 */
		setLayout(new BorderLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		
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
		this.welcomePanel = new WelcomePanel(welcomeImage);
		
		/*
		 * Sets the size of the main JPanel to the size of our welcome image
		 */
		welcomePanel.setPreferredSize(new Dimension(welcomeImage.getWidth(), welcomeImage.getHeight()));
		
		/*
		 * adds our keyListener, so that when ENTER is pressed, we see the game board and the 
		 * welcome window disappears
		 */
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				switch(e.getKeyCode()) {
					case KeyEvent.VK_ENTER:
						setVisible(false);
						natetris.setVisible(true);
						natetris.resetGame();
				}
			}
		});
		
		add(welcomePanel);
		pack();
		setLocationRelativeTo(null); // places the JFrame at the center of the screen
	}
	
	/**
	 * Helper class that will contain the image displayed in our welcome screen
	 * @author natan
	 *
	 */
	private class WelcomePanel extends JPanel {
		private static final long serialVersionUID = 6175097847507912852L;
		/**
		 * The image that makes up the panel
		 */
		BufferedImage welcomeImage = null;
		
		public WelcomePanel(BufferedImage bufferedImage) {
			this.welcomeImage = bufferedImage;
		}
		
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(welcomeImage, 0, 0, null);
		}
	}
}
