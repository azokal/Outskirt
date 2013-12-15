package kr.ac.kmu.gameproject.outskirt.boss;

import kr.ac.kmu.gameproject.outskirt.App;
import kr.ac.kmu.gameproject.outskirt.gameobject.bullet.BasicBullet;
import kr.ac.kmu.gameproject.outskirt.gameobject.weapon.BasicWeapon;
import kr.ac.kmu.gameproject.outskirt.gameobject.weapon.CircleWeapon;
import kr.ac.kmu.gameproject.outskirt.life.Life;
import kr.ac.kmu.gameproject.outskirt.screen.Game;

public class BossOnePart extends Life {
	Game.Color color;
	BossOne owner;
	float timing;
	boolean isPop = false;
	BasicWeapon weapon;

	public BossOnePart(Game game, float maxLife, BossOne owner,
			Game.Color color, float timing) {
		super(game, maxLife);
		this.color = color;
		this.owner = owner;
		oSprite = new sprites.Sprite(game.getApp(), game.pathSprites
				+ "squareGrid.png", 3, 1, 10);
		oSprite.setScale(2);
		this.setPolar(50, 0);
		if (color == Game.Color.RED) {
			oSprite.setFrame(1);
			this.setPolar(50, App.radians(120f));
		}
		if (color == Game.Color.CYAN) {
			oSprite.setFrame(2);
			this.setPolar(50, App.radians(240f));
		}
		oSprite.setVisible(false);
		this.timing = timing;
		this.weapon = new CircleWeapon(game, this, color);
		this.weapon.power = 20;
	}

	@Override
	public void looseLife(BasicBullet coll) {
		if (color == coll.color)
			coll.power /= 2;
		this.owner.looseLife(coll);
	}

	@Override
	public void draw() {
		if (game.tpop.getTotalTime() < timing)
			return;
		else if (isPop == false) {
			game.tpop.stop();
			oSprite.setVisible(true);
			isPop = true;
		}
		if (isPop == true) {
			move();
			weapon.shoot();
		}
	}

	public float getTiming() {
		return timing;
	}

	public void move() {
		addAngle(App.radians(1));
	}

}
