package sound;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import natetris.Piece;

/**
 * The Jukebox class is responsible for handling the game's audio files,
 * such as loading and playing them.
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
		try {
			/*
			 * loads the audio files and assign them to our local "library" 
			 */
			loadAudioFiles();
			
			
		} catch (Exception e) {
			System.err.println("Audio files unavailable");
			e.printStackTrace();
		}
	}
	
	/**
	 * loads all audio files and fills the {@code audioFiles} variable with them
	 */
	private void loadAudioFiles() {
		/*
		 * get all files from the current directory
		 */
		File []totalAudioFiles = currFolder.listFiles();
		
		/*
		 * Initializes our "audio library"
		 */
		for (int i = 1; i <= Piece.MAX_PIECE_DIMENSION; i++) {
			audioLibrary.put(i, new ArrayList<File>());
		}
		
		/*
		 *  fills our audio library with the audio files.
		 *  This has a really strong coupling with the name of the files, 
		 *  but I couln't think of anything better at the time.
		 */
		for (File file: totalAudioFiles) {
			switch (file.getName().charAt(0)) {
				case '1':
					audioLibrary.get(1).add(file);
					break;
				case '2':
					audioLibrary.get(2).add(file);
					break;
				case '3':
					audioLibrary.get(3).add(file);
					break;
				case '4':
					audioLibrary.get(4).add(file);
					break;
			}
		}
	}
}
