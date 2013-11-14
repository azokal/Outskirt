package kr.ac.kmu.gameproject.outskirt;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import processing.core.PApplet;
import processing.core.PImage;
import sprites.S4P;

import java.util.ArrayList;

public class Game extends PApplet {

	public Map<Character, Boolean> keys = new HashMap<Character, Boolean>();
	public Map<Integer, Boolean> keysCode = new HashMap<Integer, Boolean>();

	public List<GameObject> gameObjectList = new ArrayList<GameObject>();
	public List<GameObject> toAddList = new ArrayList<GameObject>();
	public List<GameObject> toDelList = new ArrayList<GameObject>();
	
	public PImage bg = loadImage("bg.png");
	public Timer timer = new Timer();
	public SpaceSheep spaceSheep = new SpaceSheep(this);
	public Enemy enemy = new Enemy(this);
	
	public enum Color {
	    GREEN, RED, CYAN
	}
	
	public static void main(String args[]) {
		PApplet.main(new String[] { "--present",
				"kr.ac.kmu.gameproject.outskirt.Game" });
	}

	public void setup() {
		size(1920, 1080);
	}

	public void draw() {
		timer.updateTime();
		background(bg);
		/* ellipse(1920/2, 1080/2, 900, 900); */
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
