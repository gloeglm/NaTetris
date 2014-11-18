package natetris;

/**
 * NatetrisTimer is a special timer used to calculate each step in the game, which is constant 
 * unless the player chooses to speed it up, by measuring how many milliseconds passed through each 
 * game frame. When it completes one cycle, the game board is updated.
 */
public class NatetrisTimer {
	/**
	 * Represents the time (in milliseconds) that takes for a cycle to be complete
	 */
	private float millisPerCycle;
	
	/**
	 * Represents if the timer is running or not
	 */
	private boolean isPaused;
	
	/**
	 * Represents the time in which the last update occurred
	 */
	private long lastUpdate;
	
	/**
	 * Represents how many cycles are still left on queue
	 */
	private int currentCycles;
	
	/**
	 * Represents how much time has been passed until a cycle is complete
	 */
	private float timeToNextCycle;
	
	NatetrisTimer(float cyclesPerSecond) {
		setCyclesPerSecond(cyclesPerSecond);
		reset();
	}
	
	/**
	 * Sets the amount of cycles that occur every second  
	 * @param cyclesPerSecond
	 */
	public void setCyclesPerSecond(float cyclesPerSecond) {
		this.millisPerCycle = (1.0f / cyclesPerSecond) * 1000;
	}
	
	/**
	 * Resets the time to it's default values
	 */
	public void reset() {
		this.isPaused = false;
		this.currentCycles = 0;
		this.lastUpdate = getCurrentTime();
	}
	
	/**
	 * Updates the amount of cycles left to reproduce
	 */
	public void update() {
		long currentUpdate = getCurrentTime();
		float delta = (float)(currentUpdate - lastUpdate) + timeToNextCycle;
		if (!isPaused) {
			currentCycles += (int)Math.floor((delta / millisPerCycle));
			timeToNextCycle = delta % millisPerCycle;
		}
		lastUpdate = currentUpdate;
		
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean completedOneCycle() {
		if (currentCycles > 0) {
			currentCycles--;
			return true;
		}
		return false;
	}
	
	/**
	 * Sets the state of the timer to paused or running
	 * @param state false meaning the timer's paused, true otherwise
	 */
	public void setPaused(boolean state) {
		this.isPaused = state;
	}
	
	/**
	 * Gets a representation of the current time in milliseconds
	 * @return a {@code long} value representing the time in milliseconds
	 */
	private long getCurrentTime() {
		return (System.nanoTime() / 1000000L);
	}
}
