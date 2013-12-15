package kr.ac.kmu.gameproject.outskirt.enemy;

import kr.ac.kmu.gameproject.outskirt.gameobject.bullet.FreeMoveBullet;
import kr.ac.kmu.gameproject.outskirt.screen.Game;
import kr.ac.kmu.gameproject.outskirt.screen.Game.Color;
import processing.core.PApplet;

public class SpiralEnemy extends Enemy {
	protected float cooldownShoot = 100f; // 100ms
	protected float lastShoot = 0.0f;

	public SpiralEnemy(Game game, float startAngle, Color color, float life,
			float timing) {
		super(game, startAngle, color, life, timing, game.pathSprites
				+ "spiralGrid.png");
	}

	@Override
	public void move() {
		addRadius(game.timer.getElapsedTime() / 10f);// Progressive speed

		localAngle += PApplet.PI / 700.f * game.timer.getElapsedTime();
		oSprite.setRot(localAngle + PApplet.PI / -2.f);
	}

	@Override
	public void draw() {
		super.draw();
		if (isPop == true && game.timer.getTotalTime() - lastShoot > cooldownShoot) {
			float x = getRadius() * PApplet.cos(getAngle());
			float y = getRadius() * PApplet.sin(getAngle());
			float tmpx = x - 10.0f * PApplet.cos(localAngle);
			float tmpy = y - 10.0f * PApplet.sin(localAngle);

			new FreeMoveBullet(game, this, PApplet.sqrt(tmpx
					* tmpx + tmpy * tmpy), PApplet.atan2(tmpy, tmpx),
					PApplet.degrees(localAngle), -0.2f, color, 40);

			lastShoot = game.timer.getTotalTime();
		}
	}
}
