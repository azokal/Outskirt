package kr.ac.kmu.gameproject.outskirt.screen;

import java.awt.AWTException;
import java.awt.Robot;
import java.util.ArrayList;
import java.util.List;

import kr.ac.kmu.gameproject.outskirt.App;
import kr.ac.kmu.gameproject.outskirt.Camera;
import kr.ac.kmu.gameproject.outskirt.GameObject;
import kr.ac.kmu.gameproject.outskirt.Timer;
import kr.ac.kmu.gameproject.outskirt.gameobject.Enemy;
import kr.ac.kmu.gameproject.outskirt.gameobject.SpaceSheep;
import processing.core.PImage;
import sprites.S4P;

public class Game implements Screen {

	private App app;
	
	public List<GameObject> gameObjectList = new ArrayList<GameObject>();
	public List<GameObject> toAddList = new ArrayList<GameObject>();
	public List<GameObject> toDelList = new ArrayList<GameObject>();

	public Camera camera;
	public PImage bg;
	public Timer timer;
	public SpaceSheep spaceSheep;
	public Enemy enemy;// = new Enemy(this, random(0, 360));
	public Robot robot;
	
	float testCooldownPopEnemy = 150f;
	float testLastPop = 0;

	public enum Color {
		GREEN, RED, CYAN
	}
	
	public Game(App app) {
		this.app = app;
	}

	public void setup() {
		this.camera = new Camera(this);
		this.bg = getApp().loadImage("bg.png");
		this.timer = new Timer();
		getApp().size(getApp().displayWidth, getApp().displayHeight);
		bg.resize(getApp().displayWidth, getApp().displayHeight);
		spaceSheep = new SpaceSheep(this);
		try {
			robot = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}
	
	public void draw() {
		timer.updateTime();
		getApp().background(bg);
		getApp().color(255);
		camera.draw();
		if (this.timer.getTotalTime() - testLastPop > testCooldownPopEnemy) {
			enemy = new Enemy(this, getApp().random(0, 359),
					Color.values()[(int) getApp().random(0, 3)]);
			testLastPop = timer.getTotalTime();
		}
		
		//Draw
		for (GameObject gameObject : gameObjectList) {
			gameObject.draw();
		}

		//Collision detection
		for (GameObject gameObject : gameObjectList) {
			for (GameObject gameObject2 : gameObjectList) {
				if (gameObject.collide(gameObject2)) {
					gameObject.onCollide(gameObject2);
				}
			}
		}
		
		if (!toDelList.isEmpty()) {
			gameObjectList.removeAll(toDelList);
			toDelList.clear();
		}

		if (!toAddList.isEmpty()) {
			gameObjectList.addAll(toAddList);
			toAddList.clear();
		}

		S4P.updateSprites(timer.getTotalTime());
		S4P.drawSprites();
	}

	public void addGameObject(GameObject toAdd) {
		toAddList.add(toAdd);
	}

	public void delGameObject(GameObject toDel) {
		toDelList.add(toDel);
	}
	
	public App getApp() {
		return app;
	}

	public SpaceSheep getSpaceSheep() {
		return spaceSheep;
	}
	
	public void unload() {
		for (GameObject g: gameObjectList) {
			g.kill();
		}
	}
	
}
