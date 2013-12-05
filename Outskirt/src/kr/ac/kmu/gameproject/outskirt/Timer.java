package kr.ac.kmu.gameproject.outskirt;

public class Timer {
    
    private long totalTime = 0;
    private long previousUpdateTime;
    private long elapsedTime;
    private long updateTime;
    private boolean running;

    public Timer(boolean running) {
    	init();
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
	    	elapsedTime = updateTime - previousUpdateTime;
	    	totalTime += elapsedTime;
    	}
    }

    public long getElapsedTime() {
    	return elapsedTime;
    }

    public void start() {
    	running = true;
    	init();
    }
    
    public void stop() {
    	running = false;
    }
    
    private void init() {
        previousUpdateTime = System.currentTimeMillis();
        updateTime = previousUpdateTime;
        elapsedTime = 0;
    }
    
    public void reset() {
        totalTime = 0;
        init();
    }
    
}