package utils.sound;

import java.applet.Applet;
import java.applet.AudioClip;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

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
	 * The name of the sound files. They were loaded dynamically before adding
	 * .jar support to the game, but for now they'll be loaded statically.
	 * FIXME: add reflection dynamic loading so that we don't care about the file names no more
	 */
	String []soundFiles = {"1_s1.wav",
	                       "1_s2.wav",
	                       "1_s3.wav",
	                       "2_s1.wav",
	                       "3_s1.wav",
	                       "3_s2.wav",
	                       "4_s1.wav"};
	
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
	private HashMap<Integer, ArrayList<AudioClip>> audioLibrary = new HashMap<>();
	
	
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
	 * @throws Exception 
	 */
	public void play(int clearedLines) throws Exception {
		int audiosAvailable = audioLibrary.get(clearedLines).size();
		int audioIndex = rand.nextInt(audiosAvailable);
		audioLibrary.get(clearedLines).get(audioIndex).play();
	}
	
	/**
	 * Loads all audio files and fills the {@code audioFiles} variable with them
	 */
	private void loadAudioLibrary() {
		/*
		 * Initializes our "audio library"
		 */
		for (int i = 1; i <= Piece.MAX_PIECE_DIMENSION; i++) {
			audioLibrary.put(i, new ArrayList<AudioClip>());
		}
		
		/*
		 *  Fills our audio library with the audio files.
		 *  This has a really strong coupling with the name of the files, 
		 *  but I couln't think of anything better at the time.
		 */
		String fileName = null;
		try {
			for (String file: soundFiles) {
				fileName = "sound/" + file;
				switch (file.charAt(0)) {
					case '1':
						audioLibrary.get(1).add(getAudioClip(fileName)); // One row completed audio
						break;
					case '2':
						audioLibrary.get(2).add(getAudioClip(fileName)); // Two rows completed audio
						break;
					case '3':
						audioLibrary.get(3).add(getAudioClip(fileName)); // Three rows completed audio
						break;
					case '4':
						audioLibrary.get(4).add(getAudioClip(fileName)); // Four rows completed audio
						break;
				}
			}
		} catch (Exception e) {
			System.err.println("Could not open files at: " + fileName);
		}
	}
	
	private AudioClip getAudioClip(String fileName) {
		URL audioURL = Jukebox.class.getClassLoader().getResource(fileName);
		if (audioURL != null) {
			return Applet.newAudioClip(audioURL);
		} else {
			System.err.println("Couldn't find file: " + fileName);
			return null;
		}
	}
}
