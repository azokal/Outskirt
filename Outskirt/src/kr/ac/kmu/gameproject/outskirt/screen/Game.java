package kr.ac.kmu.gameproject.outskirt.screen;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import kr.ac.kmu.gameproject.outskirt.App;
import kr.ac.kmu.gameproject.outskirt.Camera;
import kr.ac.kmu.gameproject.outskirt.GameObject;
import kr.ac.kmu.gameproject.outskirt.HUD;
import kr.ac.kmu.gameproject.outskirt.Timer;
import kr.ac.kmu.gameproject.outskirt.boss.BossOne;
import kr.ac.kmu.gameproject.outskirt.gameobject.Enemy;
import kr.ac.kmu.gameproject.outskirt.gameobject.SpaceSheep;
import kr.ac.kmu.gameproject.outskirt.partition.Partition;
import processing.core.PImage;
import sprites.S4P;

public class Game implements Screen, KeyListener {

	private App app;
	
	public List<GameObject> gameObjectList = new ArrayList<GameObject>();
	public List<GameObject> toAddList = new ArrayList<GameObject>();
	public List<GameObject> toDelList = new ArrayList<GameObject>();

	public Camera camera;
	public PImage[] bg;
	public Timer timer;
	public SpaceSheep spaceSheep;
	public Enemy enemy;// = new Enemy(this, random(0, 360));
	public float totalTime;
	public String pathSprites =  ".\\data\\sprites\\";
	public String pathMaps =  ".\\data\\maps\\";
	Partition part;
	HUD hud;
	float testCooldownPopEnemy = 150f;
	float testLastPop = 0;

	public enum Color {
		GREEN,
		RED,
		CYAN
	}
	
	public enum BossType {
		BOSSONE
	}

	public GameObject BossFactory(BossType type, float maxLife, float timing) {
		
		switch (type) {
		case BOSSONE : return (new BossOne(this, maxLife, timing));
		default: return (null);
		}
	}
	
	public Game(App app) {
		this.app = app;
	}

	public void setup() {
		this.camera = new Camera(this);
		this.bg = new PImage[4];
		this.bg[0] = getApp().loadImage(pathSprites+"greenCircle.png");
		this.bg[1] = getApp().loadImage(pathSprites+"redCircle.png");
		this.bg[2] = getApp().loadImage(pathSprites+"cyanCircle.png");
		this.bg[3] = getApp().loadImage(pathSprites+"whiteCircle.png");
		this.timer = new Timer();
		getApp().size(getApp().displayWidth, getApp().displayHeight);
		spaceSheep = new SpaceSheep(this);
		this.hud = new HUD(this, spaceSheep);
		this.getApp().addKeyListener(this);
		part = new Partition(app, this, pathMaps+"test.xml");
	}
	
	public void draw() {
		app.getDebug().put("number gameobject", gameObjectList.size());
		timer.updateTime();
		getApp().background(0);
		getApp().color(255);
		camera.draw();
		totalTime = this.timer.getTotalTime();
	//	if (totalTime - testLastPop > testCooldownPopEnemy) {
	//		enemy = new Enemy(this, getApp().random(0, 359),
	//				Color.values()[(int) getApp().random(0, 3)], 30, totalTime);
	//		part.addEnemy(enemy);
	//		testLastPop = timer.getTotalTime();
	//	}
		
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
		//draw outter circle
		getApp().image(bg[spaceSheep.getColor().ordinal()], 0, 0);
		
		//draw inner circle
		getApp().pushMatrix();
		getApp().scale(0.22f);
		getApp().image(bg[spaceSheep.getColor().ordinal()], 0, 0);
		getApp().popMatrix();
		
		hud.draw();
		app.getDebug().put("fps", app.frameRate);
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

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyChar() == 'p') {
			app.setScreen(new PauseScreen(app, this));
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public void unload() {
		for (GameObject g: gameObjectList) {
			g.kill();
		}
	}
	
}
