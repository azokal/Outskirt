package kr.ac.kmu.gameproject.outskirt;

import java.awt.AWTException;
import java.awt.Robot;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.ac.kmu.gameproject.outskirt.gameobject.Enemy;
import kr.ac.kmu.gameproject.outskirt.gameobject.SpaceSheep;
import processing.core.PApplet;
import processing.core.PImage;
import sprites.S4P;

public class Game extends PApplet {

	public Map<Character, Boolean> keys = new HashMap<Character, Boolean>();
	public Map<Integer, Boolean> keysCode = new HashMap<Integer, Boolean>();

	public List<GameObject> gameObjectList = new ArrayList<GameObject>();
	public List<GameObject> toAddList = new ArrayList<GameObject>();
	public List<GameObject> toDelList = new ArrayList<GameObject>();

	public Camera camera = new Camera(this);
	public PImage bg = loadImage("bg.png");
	public Timer timer = new Timer();
	public SpaceSheep spaceSheep;
	public Enemy enemy;// = new Enemy(this, random(0, 360));
	public Robot robot;

	public Debug debug = new Debug(this);
	
	float testCooldownPopEnemy = 150f;
	float testLastPop = 0;

	public enum Color {
		GREEN, RED, CYAN
	}

	public static void main(String args[]) {
		PApplet.main(new String[] { "--present",
				"kr.ac.kmu.gameproject.outskirt.Game" });
	}

	public void setup() {
		size(displayWidth, displayHeight);
		bg.resize(displayWidth, displayHeight);
		spaceSheep = new SpaceSheep(this);
		try {
			robot = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}

	public void draw() {
		timer.updateTime();
		background(bg);
		debug.draw();
		color(255);
		camera.draw();
		if (this.timer.getTotalTime() - testLastPop > testCooldownPopEnemy) {
			enemy = new Enemy(this, random(0, 359),
					Color.values()[(int) random(0, 3)]);
			testLastPop = timer.getTotalTime();
		}
		for (GameObject gameObject : gameObjectList) {
			gameObject.draw();
		}

		gameObjectList.removeAll(toDelList);
		toDelList.clear();

		gameObjectList.addAll(toAddList);
		toAddList.clear();

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

	public void addGameObject(GameObject toAdd) {
		toAddList.add(toAdd);
	}

	public void delGameObject(GameObject toDel) {
		toDelList.add(toDel);
	}

}
