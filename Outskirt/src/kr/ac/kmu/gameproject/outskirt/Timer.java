package kr.ac.kmu.gameproject.outskirt;

public class Timer {
    
    private long totalTime = 0;
    private long previousUpdateTime;
    private long elapsedTime;
    private long updateTimeCount;
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
	    	previousUpdateTime = updateTimeCount;
	    	updateTimeCount = System.currentTimeMillis();
	    	elapsedTime = updateTimeCount - previousUpdateTime;
	    	totalTime += elapsedTime;
    	}
    }

    public long getElapsedTime() {
    	return elapsedTime;
    }

    public void start() {
    	init();
    	running = true;
    }
    
    public void stop() {
    	init();
    	running = false;
    }
    
    private void init() {
        previousUpdateTime = System.currentTimeMillis();
        updateTimeCount = previousUpdateTime;
        elapsedTime = 0;
    }
    
    public void reset() {
        totalTime = 0;
        init();
    }

	public boolean isRunning() {
		return running;
	}
}