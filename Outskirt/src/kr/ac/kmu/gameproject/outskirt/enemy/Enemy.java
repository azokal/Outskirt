package kr.ac.kmu.gameproject.outskirt.enemy;

import kr.ac.kmu.gameproject.outskirt.GameObject;
import kr.ac.kmu.gameproject.outskirt.gameobject.SpaceSheep;
import kr.ac.kmu.gameproject.outskirt.gameobject.bullet.BasicBullet;
import kr.ac.kmu.gameproject.outskirt.life.Life;
import kr.ac.kmu.gameproject.outskirt.screen.EndScreen;
import kr.ac.kmu.gameproject.outskirt.screen.Game;
import processing.core.PApplet;

public class Enemy extends Life {
	protected boolean isPop = false;
	protected float localAngle = 0;
	float startAngle = 0;
	protected Game.Color color;
	protected float timing;

	public Enemy(Game game, float startAngle, Game.Color color, float life,
			float timing, String path) {
		super(game, life);
		this.timing = timing;
		oSprite = new sprites.Sprite(game.getApp(), path, 3, 1, 10);
		this.startAngle = startAngle;
		setPolar(0, PApplet.radians(this.startAngle));
		oSprite.setScale(getRadius() / 300f);
		this.color = color;
		if (color == Game.Color.RED)
			oSprite.setFrame(1);
		if (color == Game.Color.CYAN)
			oSprite.setFrame(2);
		oSprite.setVisible(false);
	}

	public int getColor() {
		return (color.ordinal());
	}

	public float getLife() {
		return (life);
	}

	public float getTiming() {
		return (timing);
	}

	public void move() {

	}

	public void draw() {
		if (game.pop < timing && isPop == false)
			return;
		else if (isPop == false) {
			oSprite.setVisible(true);
			isPop = true;
		}
		if (getRadius() > 550) {
			this.kill();
		} else {
			oSprite.setScale(getRadius() / 300f);
			move();
		}
	}

	public void onCollide(GameObject obj) {
		if (obj instanceof SpaceSheep) {
			EndScreen screen = new EndScreen(game.getApp(), game);
			screen.setup();
			game.getApp().setScreen(screen);
		}
	}

	public void looseLife(BasicBullet coll) {
		if (color != coll.color) {
			life -= coll.power;
		} else {
			life -= coll.power / 2;
		}
		if (life <= 0) {
			kill();
			((SpaceSheep) coll.owner).addScore(100);
			if (color != coll.color) {
				((SpaceSheep) coll.owner).getCurrentWeapon().upPercentage(
						coll.color);
			} else {
				((SpaceSheep) coll.owner).getCurrentWeapon().upPercentage(
						coll.color);
				((SpaceSheep) coll.owner).getCurrentWeapon().upPercentage(
						coll.color);
			}
			((SpaceSheep) coll.owner).addCombo(color);
		}
	}
}
