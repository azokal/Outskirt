package kr.ac.kmu.gameproject.outskirt;

public class Timer {
    
    private long startTime = 0;
    private long updateTime = 0;

    public Timer() {
        startTime = System.currentTimeMillis();
        updateTime = startTime;
    }

    public long getTotalTime() {
		return System.currentTimeMillis() - startTime;
    }
    
    public void updateTime() {
    	updateTime = System.currentTimeMillis();
    }

    public long getElapsedTime() {
    	return System.currentTimeMillis() - updateTime;
    }

}