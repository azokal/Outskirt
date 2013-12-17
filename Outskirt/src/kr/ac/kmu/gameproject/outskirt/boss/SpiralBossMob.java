package kr.ac.kmu.gameproject.outskirt.boss;

import kr.ac.kmu.gameproject.outskirt.enemy.SpiralEnemy;
import kr.ac.kmu.gameproject.outskirt.gameobject.bullet.BasicBullet;
import kr.ac.kmu.gameproject.outskirt.gameobject.bullet.FreeMoveBullet;
import kr.ac.kmu.gameproject.outskirt.screen.Game;
import kr.ac.kmu.gameproject.outskirt.screen.Game.Color;
import processing.core.PApplet;

public class SpiralBossMob extends SpiralEnemy {
	int shootCount = 30;

	public SpiralBossMob(Game game, float startAngle, float radius,
			Color color, float life, float timing) {
		super(game, startAngle, color, life, timing);
		setRadius(radius);
		cooldownShoot = 80f;
	}

	@Override
	public void move() {
		localAngle += PApplet.PI / 1150.f * game.timer.getElapsedTime();
		oSprite.setRot(localAngle + PApplet.PI / -2.f);
		oSprite.setScale(1.0);
	}

	@Override
	public void looseLife(BasicBullet coll) {
	}

	public void resetShoot() {
		shootCount = 0;
	}

	@Override
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
		if (isPop == true
				&& game.timer.getTotalTime() - lastShoot > cooldownShoot
				&& shootCount < 30) {
			float x = getRadius() * PApplet.cos(getAngle());
			float y = getRadius() * PApplet.sin(getAngle());
			float tmpx = x - 10.0f * PApplet.cos(localAngle);
			float tmpy = y - 10.0f * PApplet.sin(localAngle);

			new FreeMoveBullet(game, this, PApplet.sqrt(tmpx * tmpx + tmpy
					* tmpy), PApplet.atan2(tmpy, tmpx),
					PApplet.degrees(localAngle), -0.2f, color, power);
			shootCount++;
			lastShoot = game.timer.getTotalTime();
		}
	}
}
