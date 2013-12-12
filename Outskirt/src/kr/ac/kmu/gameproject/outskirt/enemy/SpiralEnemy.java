package kr.ac.kmu.gameproject.outskirt.enemy;

import java.util.Random;

import processing.core.PApplet;
import kr.ac.kmu.gameproject.outskirt.gameobject.bullet.BasicBullet;
import kr.ac.kmu.gameproject.outskirt.gameobject.bullet.FreeMoveBullet;
import kr.ac.kmu.gameproject.outskirt.screen.Game;
import kr.ac.kmu.gameproject.outskirt.screen.Game.Color;

public class SpiralEnemy extends Enemy {
	float cooldownShoot = 100f; // 100ms
	float lastShoot = 0.0f;

	public SpiralEnemy(Game game, float startAngle, Color color, float life,
			float timing) {
		super(game, startAngle, color, life, timing, game.pathSprites
				+ "spiralGrid.png");
	}

	@Override
	public void move() {
		addRadius(game.timer.getElapsedTime() / 10f);// Progressive speed

		localAngle += PApplet.PI / 30.f;
		oSprite.setRot(localAngle + PApplet.PI / -2.f);
	}

	@Override
	public void draw() {
		super.draw();
		if (game.timer.getTotalTime() - lastShoot > cooldownShoot) {
			float x = getRadius() * PApplet.cos(getAngle());
			float y = getRadius() * PApplet.sin(getAngle());
			float tmpx = x - 10.0f * PApplet.cos(localAngle);
			float tmpy = y - 10.0f * PApplet.sin(localAngle);

			BasicBullet b = new FreeMoveBullet(game, this, PApplet.sqrt(tmpx
					* tmpx + tmpy * tmpy), PApplet.atan2(tmpy, tmpx),
					PApplet.degrees(localAngle), -3, color, 40);

			lastShoot = game.timer.getTotalTime();
		}
	}
}
