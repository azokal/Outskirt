package kr.ac.kmu.gameproject.outskirt.boss;

import kr.ac.kmu.gameproject.outskirt.App;
import kr.ac.kmu.gameproject.outskirt.gameobject.bullet.BasicBullet;
import kr.ac.kmu.gameproject.outskirt.gameobject.weapon.BasicWeapon;
import kr.ac.kmu.gameproject.outskirt.life.Life;
import kr.ac.kmu.gameproject.outskirt.screen.Game;

public class BossThreePart extends Life {
	Game.Color color;
	BossThree owner;
	float timing;
	boolean isPop = false;
	BasicWeapon weapon;
	public int power = 0;

	public BossThreePart(Game game, float maxLife, BossThree owner,
			Game.Color color, float timing) {
		super(game, maxLife);
		this.color = color;
		this.owner = owner;
		oSprite = new sprites.Sprite(game.getApp(), game.pathSprites
				+ "spiralGrid.png", 3, 1, 10);
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
		}
	}

	public float getTiming() {
		return timing;
	}

	public void move() {
		addAngle(game.getApp().radians(game.timer.getElapsedTime() / 22f));
	}

}
