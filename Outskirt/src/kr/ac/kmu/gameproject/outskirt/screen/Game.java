package kr.ac.kmu.gameproject.outskirt.screen;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import kr.ac.kmu.gameproject.outskirt.App;
import kr.ac.kmu.gameproject.outskirt.Camera;
import kr.ac.kmu.gameproject.outskirt.GameObject;
import kr.ac.kmu.gameproject.outskirt.HUD;
import kr.ac.kmu.gameproject.outskirt.Timer;
import kr.ac.kmu.gameproject.outskirt.boss.BasicBoss;
import kr.ac.kmu.gameproject.outskirt.boss.BossOne;
import kr.ac.kmu.gameproject.outskirt.boss.BossThree;
import kr.ac.kmu.gameproject.outskirt.boss.BossTwo;
import kr.ac.kmu.gameproject.outskirt.enemy.DiamondEnemy;
import kr.ac.kmu.gameproject.outskirt.enemy.Enemy;
import kr.ac.kmu.gameproject.outskirt.enemy.ExplosionEnemy;
import kr.ac.kmu.gameproject.outskirt.enemy.SpiralEnemy;
import kr.ac.kmu.gameproject.outskirt.enemy.SquareEnemy;
import kr.ac.kmu.gameproject.outskirt.gameobject.SpaceSheep;
import kr.ac.kmu.gameproject.outskirt.partition.Partition;
import processing.core.PImage;

/**
 * @author brissa_a
 * 
 */
public abstract class Game implements Screen, KeyListener {

	App app;

	public List<GameObject> gameObjectList = new ArrayList<GameObject>();
	public List<GameObject> toAddList = new ArrayList<GameObject>();
	public List<GameObject> toDelList = new ArrayList<GameObject>();

	public Camera camera;
	public PImage[] bg;
	public Timer timer;
	public Timer tpop;
	public SpaceSheep spaceSheep = null;
	public Enemy enemy;
	public float totalTime;
	public float pop;
	public String pathSprites = "." + File.separator + "data" + File.separator
			+ "sprites" + File.separator;
	public String pathMaps = "." + File.separator + "data" + File.separator
			+ "maps" + File.separator;
	public float endGame;
	public boolean isPausable = false;
	Partition part;
	HUD hud = null;

	public static final int MAX_LEVEL = 3;

	public enum Color {
		GREEN, RED, CYAN
	}

	public enum BossType {
		BOSSONE, BOSSTWO, BOSSTHREE
	}

	public enum EnemyType {
		SQUARE, DIAMOND, EXPLOSION, SPIRAL
	}

	public GameObject BossFactory(BossType type, float maxLife, float timing) {

		switch (type) {
		case BOSSONE:
			return (new BossOne(this, maxLife, timing));
		case BOSSTWO:
			return (new BossTwo(this, maxLife, timing));
		case BOSSTHREE:
			return (new BossThree(this, maxLife, timing));
		default:
			return (null);
		}
	}

	public GameObject EnnemyFactory(EnemyType type, float startAngle,
			Color color, float maxLife, float timing) {

		switch (type) {
		case SQUARE:
			return (new SquareEnemy(this, startAngle, color, maxLife, timing));
		case DIAMOND:
			return (new DiamondEnemy(this, startAngle, color, maxLife, timing));
		case EXPLOSION:
			return (new ExplosionEnemy(this, startAngle, color, maxLife, timing));
		case SPIRAL:
			return (new SpiralEnemy(this, startAngle, color, maxLife, timing));
		default:
			return (null);
		}
	}

	public Game(App app) {
		this.app = app;
	}

	abstract void init();

	public void setup() {
		this.camera = new Camera(this);
		this.bg = new PImage[4];
		this.bg[0] = getApp().loadImage(pathSprites + "greenCircle.png");
		this.bg[1] = getApp().loadImage(pathSprites + "redCircle.png");
		this.bg[2] = getApp().loadImage(pathSprites + "cyanCircle.png");
		this.bg[3] = getApp().loadImage(pathSprites + "whiteCircle.png");
		this.timer = new Timer(true);
		this.tpop = new Timer(true);
		getApp().size(getApp().displayWidth, getApp().displayHeight);
		registerEvent();
		init();
	}

	public void registerEvent() {
		this.getApp().addKeyListener(this);
	}

	public void unregisterEvent() {
		this.getApp().removeKeyListener(this);
	}

	public static int randInt(int min, int max) {

		// Usually this can be a field rather than a method variable
		Random rand = new Random();

		// nextInt is normally exclusive of the top value,
		// so add 1 to make it inclusive
		int randomNum = rand.nextInt((max - min) + 1) + min;

		return randomNum;
	}

	void endLevel() {
		if (pop >= endGame) {
			isPausable = false;
			EndScreen screen = new EndScreen(app, this);
			screen.setup();
			app.setScreen(screen);
		}
	}

	public void draw() {
		app.getDebug().put("number gameobject", gameObjectList.size());
		this.timer.updateTime();
		this.tpop.updateTime();
		getApp().background(0);
		getApp().color(255);
		camera.draw();

		// draw outter circle
		if (spaceSheep == null)
			getApp().image(bg[0], 0, 0);
		else
			getApp().image(bg[spaceSheep.getColor().ordinal()], 0, 0);

		totalTime = this.timer.getTotalTime();
		pop = this.tpop.getTotalTime();
		endLevel();
		// if (totalTime - testLastPop > testCooldownPopEnemy) {
		// enemy = new Enemy(this, getApp().random(0, 359),
		// Color.values()[(int) getApp().random(0, 3)], 30, totalTime);
		// part.addEnemy(enemy);
		// testLastPop = timer.getTotalTime();
		// }

		// Draw
		for (GameObject gameObject : gameObjectList) {
			gameObject.draw();
		}

		// Collision detection
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

		// S4P.updateSprites(timer.getTotalTime());
		// S4P.drawSprites();

		for (GameObject b : gameObjectList)
			if (b.oSprite != null)
				b.oSprite.draw();

		if (hud != null)
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
		if (isPausable && e.getKeyChar() == 'p') {
			this.pause();
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

	public void pause() {
		this.timer.stop();
		this.tpop.stop();
		unregisterEvent();
		for (GameObject g : gameObjectList) {
			g.pause();
		}
	}

	public void resume() {
		this.timer.start();
		BasicBoss boss = null;
		boolean isFind = false;
		for (GameObject obj : this.gameObjectList) {
			if (obj instanceof BasicBoss
					&& this.tpop.getTotalTime() >= ((BasicBoss) obj)
							.getTiming()) {
				boss = (BasicBoss) obj;
				isFind = true;
			}
		}
		if (isFind == false)
			this.tpop.start();
		registerEvent();
		for (GameObject g : gameObjectList) {
			g.resume();
		}
	}

	public void unload() {
		unregisterEvent();
		for (GameObject g : gameObjectList) {
			g.kill();
		}
	}

	public void activatePause() {
		isPausable = true;
	}

}
