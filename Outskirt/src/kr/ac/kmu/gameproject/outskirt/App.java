package kr.ac.kmu.gameproject.outskirt;

import java.awt.AWTException;
import java.awt.Robot;
import java.util.HashMap;
import java.util.Map;

import kr.ac.kmu.gameproject.outskirt.partition.Partition;
import kr.ac.kmu.gameproject.outskirt.screen.AventureMode;
import kr.ac.kmu.gameproject.outskirt.screen.MainMenu;
import kr.ac.kmu.gameproject.outskirt.screen.Screen;
import processing.core.PApplet;

public class App extends PApplet {

	private Debug debug;
	private Screen screen;
	public Robot robot;
	public Map<Character, Boolean> keys = new HashMap<Character, Boolean>();
	public Map<Integer, Boolean> keysCode = new HashMap<Integer, Boolean>();
	
	public static void main(String args[]) {
		PApplet.main(new String[] { "--present",
				"kr.ac.kmu.gameproject.outskirt.App" });
	}

	public void setup() {
		debug = new Debug(this);
		try {
			robot = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}
		setScreen(new MainMenu(this));
	}
	
	public void draw() {
		pushMatrix();
		screen.draw();
		popMatrix();
		debug.draw();
	}
	
	public Debug getDebug() {
		return debug;
	}
	
	public void keyPressed() {
		if (key == CODED) {
			keysCode.put(keyCode, true);
		} else {
			keys.put(key, true);
		}
	}

	public void keyReleased() {
		if (key == CODED) {
			keysCode.put(keyCode, false);
		} else {
			keys.put(key, false);
		}
	}

	public boolean isPressed(int code) {
		if (keysCode.containsKey(code)) {
			return keysCode.get(code);
		}
		return false;
	}

	public boolean isPressed(char code) {
		if (keys.containsKey(code)) {
			return keys.get(code);
		}
		return false;
	}
	
	public Screen getScreen() {
		return screen;
	}

	public void setScreen(Screen screen) {
		this.screen = screen;
		screen.setup();
	}

	public void resumeScreen(Screen screen) {
		this.screen = screen;
	}

	static public float toX(float radius, float angle) {
		return radius * PApplet.cos(angle);
	}
	
	static public float toY(float radius, float angle) {
		return radius * PApplet.sin(angle);
	}
	
	static public float toRadius(float x, float y) {
		return PApplet.sqrt(x * x + y * y);

	}
	
	static public float toAngle(float x, float y) {
		return PApplet.atan2(y, x);
	}
	
}
