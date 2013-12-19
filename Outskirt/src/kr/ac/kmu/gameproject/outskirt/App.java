package kr.ac.kmu.gameproject.outskirt;

import java.awt.AWTException;
import java.awt.Robot;
import java.util.HashMap;
import java.util.Map;

import kr.ac.kmu.gameproject.outskirt.screen.MainMenu;
import kr.ac.kmu.gameproject.outskirt.screen.Screen;
import processing.core.PApplet;
import processing.core.PFont;

public class App extends PApplet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Debug debug;
	private Screen screen;
	private Screen nextScreen;
	public Robot robot;
	public Map<Character, Boolean> keys = new HashMap<Character, Boolean>();
	public Map<Integer, Boolean> keysCode = new HashMap<Integer, Boolean>();

	private SoundManager soundManager;

	public static void main(String args[]) {
		PApplet.main(new String[] { "--present",
				"kr.ac.kmu.gameproject.outskirt.App" });
	}

	public boolean sketchFullScreen() {
		return true;
	}

	public void setup() {
		debug = new Debug(this);
		try {
			robot = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}
		soundManager = new SoundManager(this);
		PFont l = loadFont("Xolonium-96.vlw");
		textFont(l);
		MainMenu screen = new MainMenu(this);
		screen.setup();
		setScreen(screen);
	}

	public void draw() {
		pushMatrix();
		if (keysCode.containsKey(27) && keysCode.get(27)) {
			if (!(screen instanceof MainMenu)) {
				screen.unload();
				MainMenu screen = new MainMenu(this);
				screen.setup();
				setScreen(screen);
			}
			keysCode.remove(27);
		}
		if (nextScreen != screen) {
			screen = nextScreen;
		}
		screen.draw();
		popMatrix();
		debug.draw();
	}

	public Debug getDebug() {
		return debug;
	}

	public void keyPressed() {
		if (keyPressed) {
			if (key == 27) {
				keysCode.put(27, true);
				key = 0;
			}
		}
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
		this.nextScreen = screen;
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

	/**
	 * @return the soundManager
	 */
	public SoundManager getSoundManager() {
		return soundManager;
	}

	/**
	 * @param soundManager
	 *            the soundManager to set
	 */
	public void setSoundManager(SoundManager soundManager) {
		this.soundManager = soundManager;
	}

}
