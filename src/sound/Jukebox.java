package sound;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import natetris.Piece;

/**
 * The Jukebox class is responsible for handling the game's audio files,
 * such as loading and playing them.
 * 
 * @author natan
 *
 */
public class Jukebox {
	
	/**
	 * The complete absolute path from where the application has initialized
	 */
	private static final String currPath = System.getProperty("user.dir") + "/sound";
	
	/**
	 * The current folder that we are in
	 */
	private final File currFolder = new File(currPath);
	
	/**
	 * Random number generator to randomize audio selection
	 */
	private Random rand;
	
	/**
	 * {@code audioFiles} contains all the audio files available in '/sound' folder.<br>
	 * 
	 * Depending on the number of rows cleared, there are a number of different 
	 * sounds that can be played. Hence, {@code audioFiles} is organized as an 
	 * HashMap of ArrayList of files: each ArrayList is associated with a key 
	 * representing the number of rows that were cleared, and contains all the audio files 
	 * related to that key
	 */
	private HashMap<Integer, ArrayList<File>> audioLibrary = new HashMap<>();
	
	
	public Jukebox() {
		this.rand = new Random();
		try {
			/*
			 * Loads the audio files and assign them to our local library
			 */
			loadAudioLibrary();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Plays an audio associated with the number of cleared rows
	 * @param clearedRows
	 * @throws IOException 
	 * @throws UnsupportedAudioFileException 
	 * @throws LineUnavailableException 
	 */
	public void play(int clearedLines) throws Exception {
		int audiosAvailable = audioLibrary.get(clearedLines).size();
		int audioIndex = rand.nextInt(audiosAvailable);
		
		/*
		 * Gets a random audio available from clearedLines level and plays it.
		 * This code is overly complex for the function that it does because I get
		 * an IllegalArgumentException when I try to open the clip without an explicit cast. 
		 * I guess there is something wrong with the *.wav files. 
		 * I plan on fixing this code if I get the chance.
		 */
		AudioInputStream ais = AudioSystem.getAudioInputStream(audioLibrary.get(clearedLines).get(audioIndex));
		AudioFormat format = ais.getFormat();
		DataLine.Info info = new DataLine.Info(Clip.class, format);
		Clip clip = (Clip) AudioSystem.getLine(info);
		clip.open(ais);
		clip.start();
	}
	
	/**
	 * Loads all audio files and fills the {@code audioFiles} variable with them
	 */
	private void loadAudioLibrary() {
		/*
		 * Get all files from the current directory
		 */
		File []totalAudioFiles = currFolder.listFiles();
		
		/*
		 * Initializes our "audio library"
		 */
		for (int i = 1; i <= Piece.MAX_PIECE_DIMENSION; i++) {
			audioLibrary.put(i, new ArrayList<File>());
		}
		
		/*
		 *  Fills our audio library with the audio files.
		 *  This has a really strong coupling with the name of the files, 
		 *  but I couln't think of anything better at the time.
		 */
		for (File file: totalAudioFiles) {
			switch (file.getName().charAt(0)) {
				case '1':
					// One row completed audio
					audioLibrary.get(1).add(file);
					break;
				case '2':
					// Two rows completed audio
					audioLibrary.get(2).add(file);
					break;
				case '3':
					// Three rows completed audio
					audioLibrary.get(3).add(file);
					break;
				case '4':
					// Four rows completed audio
					audioLibrary.get(4).add(file);
					break;
			}
		}
	}
}
