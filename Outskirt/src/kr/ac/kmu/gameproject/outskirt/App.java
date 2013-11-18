package kr.ac.kmu.gameproject.outskirt;

import java.util.HashMap;
import java.util.Map;

import processing.core.PApplet;

public class App extends PApplet {

	private Debug debug;
	private Game game;
	
	public Map<Character, Boolean> keys = new HashMap<Character, Boolean>();
	public Map<Integer, Boolean> keysCode = new HashMap<Integer, Boolean>();
	
	public static void main(String args[]) {
		PApplet.main(new String[] { "--present",
				"kr.ac.kmu.gameproject.outskirt.App" });
	}

	public void setup() {
		game = new Game(this);
		debug = new Debug(this);
	}
	
	public void draw() {
		debug.draw();
		game.draw();
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
	
}
