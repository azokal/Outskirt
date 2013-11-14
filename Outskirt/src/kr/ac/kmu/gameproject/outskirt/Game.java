package kr.ac.kmu.gameproject.outskirt;

import java.util.HashMap;
import java.util.Map;

import processing.core.PApplet;
import processing.core.PImage;
import sprites.S4P;

import java.util.ArrayList;

public class Game extends PApplet {

	public Map<Character, Boolean> keys = new HashMap<Character, Boolean>();
	public Map<Integer, Boolean> keysCode = new HashMap<Integer, Boolean>();

	public PImage bg;
	public Timer timer;
	public SpaceSheep spaceSheep;
	public Enemy enemy;
	public ArrayList<GameObject> bullets;
	
	public enum Color {
	    GREEN, RED, CYAN
	}
	
	public static void main(String args[]) {
		PApplet.main(new String[] { "--present",
				"kr.ac.kmu.gameproject.outskirt.Game" });
	}

	public void setup() {
		size(1920, 1080);
		bg = loadImage("bg.png");
		timer = new Timer();
		spaceSheep = new SpaceSheep(this);
		enemy = new Enemy(this);
		bullets = new ArrayList<GameObject>(0);
	}

	public void draw() {
		timer.updateTime();
		background(bg);
		/* ellipse(1920/2, 1080/2, 900, 900); */
		spaceSheep.draw();
		enemy.draw();
		for (GameObject bullet : bullets) {
			bullet.draw();
		}
			S4P.updateSprites(timer.getTotalTime());
			S4P.drawSprites();

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
	public void addObject(GameObject toAdd) {
		bullets.add(toAdd);
	}
	
}
