package kr.ac.kmu.gameproject.outskirt;

public class Timer {
    
    private long totalTime = 0;
    private long previousUpdateTime = 0;
    private long elapsedTime = 0;
    private long updateTime = 0;
    private boolean running;

    public Timer(boolean running) {
    	this.running = running;
    }
    
    public Timer() {
    	this(true);
    }

    public long getTotalTime() {
		return totalTime;
    }
    
    public void updateTime() {
    	if (running) {
	    	previousUpdateTime = updateTime;
	    	updateTime = System.currentTimeMillis();
	    	elapsedTime = previousUpdateTime - updateTime;
	    	totalTime += elapsedTime;
    	}
    }

    public long getElapsedTime() {
    	return elapsedTime;
    }

    public void start() {
    	running = true;
    }
    
    public void stop() {
    	running = false;
    }
    
    public void reset() {
        totalTime = 0;
        previousUpdateTime = 0;
        elapsedTime = 0;
        updateTime = 0;
    }
    
}