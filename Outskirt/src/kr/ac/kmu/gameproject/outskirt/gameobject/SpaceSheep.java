package kr.ac.kmu.gameproject.outskirt.gameobject;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import kr.ac.kmu.gameproject.outskirt.gameobject.bullet.BasicBullet;
import kr.ac.kmu.gameproject.outskirt.gameobject.weapon.BasicPlayerWeapon;
import kr.ac.kmu.gameproject.outskirt.gameobject.weapon.BasicWeapon;
import kr.ac.kmu.gameproject.outskirt.gameobject.weapon.CurveWeapon;
import kr.ac.kmu.gameproject.outskirt.gameobject.weapon.StraightWeapon;
import kr.ac.kmu.gameproject.outskirt.life.Life;
import kr.ac.kmu.gameproject.outskirt.screen.EndScreen;
import kr.ac.kmu.gameproject.outskirt.screen.Game;
import processing.core.PApplet;

public class SpaceSheep extends Life implements MouseMotionListener {

	int score = 0;
	public int currentWeapon = 0;
	public BasicWeapon[] weaponList = new BasicPlayerWeapon[3];
	public ArrayList<Game.Color> combo;
	public boolean[] weaponStateList = new boolean[3];

	public SpaceSheep(Game game, boolean story) {
		super(game, 100);

		weaponStateList[0] = true;
		if (story == true) {
			weaponStateList[1] = false;
			weaponStateList[2] = false;
		} else {
			weaponStateList[1] = true;
			weaponStateList[2] = true;
		}
		game.getApp().getDebug().put("bool", (weaponStateList[1]));
		oSprite = new sprites.Sprite(game.getApp(), game.pathSprites
				+ "playerGrid.png", 3, 1, 10);
		oSprite.setScale(1.4f);
		setPolar(450, 0);
		color = Game.Color.GREEN;
		weaponList[0] = new BasicPlayerWeapon(game, this, color);
		weaponList[1] = new StraightWeapon(game, this, color);
		weaponList[2] = new CurveWeapon(game, this, color);
		game.getApp().noCursor();
		oSprite.setCollisionRadius(oSprite.getCollisionRadius() / 10.0f);
		combo = new ArrayList<Game.Color>();
		//
		// float mouseX = getX() - game.getApp().displayWidth / 2;
		// float mouseY = getY() - game.getApp().displayHeight / 2;
		// game.getApp().robot.mouseMove((int)mouseX, (int)mouseY);
		int newMouseX = (int) (getX() + game.getApp().displayWidth / 2);
		int newMouseY = (int) (getY() + game.getApp().displayHeight / 2);
		game.getApp().robot.mouseMove(newMouseX, newMouseY);
		registerEvent();
	}

	public void registerEvent() {
		game.getApp().addMouseMotionListener(this);
	}

	public void unregisterEvent() {
		game.getApp().removeMouseMotionListener(this);
	}

	public void activateWeapon(int id) {
		weaponStateList[id] = true;
	}

	public void draw() {
		game.getApp().getDebug().put("Score", score);
		oSprite.setScale(getRadius() / 300f);
		if (game.getApp().isPressed(PApplet.LEFT)) {
			addAngle(0.08f);
		}
		if (game.getApp().isPressed(PApplet.RIGHT)) {
			addAngle(-0.08f);
		}

		if (game.getApp().isPressed('1')) {
			currentWeapon = 0;
		}

		if (game.getApp().isPressed('2') && weaponStateList[1] == true) {
			currentWeapon = 1;
		}

		if (game.getApp().isPressed('3') && weaponStateList[2] == true) {
			currentWeapon = 2;
		}

		game.getApp()
				.getDebug()
				.put("Current Weapon",
						weaponList[currentWeapon].getClass().getName());

		if (game.getApp().isPressed(' ')
				|| (game.getApp().mousePressed && game.getApp().mouseButton == PApplet.LEFT)) {
			getCurrentWeapon().shoot();
		}
	}

	// Event functions
	@Override
	public void mouseDragged(MouseEvent e) {
		mouseMoved(e);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		float mouseX = e.getX() - game.getApp().displayWidth / 2;
		float mouseY = e.getY() - game.getApp().displayHeight / 2;
		float norme = PApplet.sqrt(mouseX * mouseX + mouseY * mouseY);

		// Free fly
		if (norme > 450) {
			norme = 450;
		}

		if (norme < 100) {
			norme = 100;
		}

		setPolar(norme, PApplet.atan2(mouseY, mouseX));
		int newMouseX = (int) (getX() + game.getApp().displayWidth / 2);
		int newMouseY = (int) (getY() + game.getApp().displayHeight / 2);
		game.getApp().robot.mouseMove(newMouseX, newMouseY);
	}

	public BasicWeapon getCurrentWeapon() {
		return weaponList[currentWeapon];
	}

	public void addCombo(Game.Color c) {
		combo.add(c);
		if (combo.size() >= 3) {
			if (combo.size() == 4) {
				combo.remove(0);
			}
			if (combo.get(0) == combo.get(1) && combo.get(0) == combo.get(2)) {
				addScore(1000);
			}
			if (combo.get(0) != combo.get(1) && combo.get(0) != combo.get(2)
					&& combo.get(1) != combo.get(2)) {
				if (getCurrentWeapon().power + 5 <= getCurrentWeapon().powerMax)
					getCurrentWeapon().power += 5;
				else {
					getCurrentWeapon().power = getCurrentWeapon().powerMax;
					addScore(5000);
				}
			}
		}
	}

	@Override
	public void pause() {
		unregisterEvent();
	}

	@Override
	public void resume() {
		registerEvent();
	}

	@Override
	public void kill() {
		super.kill();
		unregisterEvent();
	}

	public void addScore(int i) {
		score += i;
	}

	public int getScore() {
		return score;
	}

	public Game.Color getColor() {
		return color;
	}

	public void setRed() {
		color = Game.Color.RED;
		oSprite.setFrame(1);
	}

	public void setGreen() {
		color = Game.Color.GREEN;
		oSprite.setFrame(0);
	}

	public void setBlue() {
		color = Game.Color.CYAN;
		oSprite.setFrame(2);
	}

	@Override
	public void looseLife(BasicBullet coll) {
		if (color != coll.color) {
			life -= coll.power;
		} else {
			if (life + coll.power / 2 <= maxLife)
				life += coll.power / 2;
			else
				life = maxLife;
		}
		if (life <= 0) {
			kill();
			EndScreen screen = new EndScreen(game.getApp(), game);
			screen.setup();
			game.getApp().setScreen(screen);
		}
	}

}
