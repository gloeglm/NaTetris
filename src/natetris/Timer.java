package natetris;

public class Timer {
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
	
	Timer(float cyclesPerSecond) {
		setCyclesPerSecond(cyclesPerSecond);
		reset();
	}
	
	private void setCyclesPerSecond(float cyclesPerSecond) {
		this.millisPerCycle = (1.0f / cyclesPerSecond) * 1000;
	}
	
	public void reset() {
		this.isPaused = false;
		this.lastUpdate = getCurrentTime();
	}
	
	public void update() {
		long currentTime = System.nanoTime();
		float delta = (float)(currentTime - lastUpdate);
		if (!isPaused) {
			// TODO  floor(delta / millisPerCycle)   
		}
	}
	
	public boolean completedOneCycle() {
		return true; // TODO
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
