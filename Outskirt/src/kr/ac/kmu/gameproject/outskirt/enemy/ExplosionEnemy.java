package kr.ac.kmu.gameproject.outskirt.enemy;

import kr.ac.kmu.gameproject.outskirt.gameobject.bullet.FreeMoveBullet;
import kr.ac.kmu.gameproject.outskirt.screen.Game;
import kr.ac.kmu.gameproject.outskirt.screen.Game.Color;
import processing.core.PApplet;

public class ExplosionEnemy extends Enemy{
	public ExplosionEnemy(Game game, float startAngle, Color color, float life,
			float timing) {
		super(game, startAngle, color, life, timing, game.pathSprites+"explosionGrid.png");
	}

	@Override
	public void move() {
		addRadius(game.timer.getElapsedTime() / 10f);//Progressive speed

		localAngle += PApplet.PI / 700.f * game.timer.getElapsedTime();
		oSprite.setRot(localAngle);
	}
	
	@Override
	public void draw() {
		super.draw();
		if (getRadius() > 275) {
			float x = getRadius() * PApplet.cos(getAngle());
			float y = getRadius() * PApplet.sin(getAngle());
			for (float a = 0.0f; a < 360.0f; a += 20.0f) {
				float tmpx = x + 16.0f * PApplet.cos(PApplet.radians(a));
				float tmpy = y + 16.0f * PApplet.sin(PApplet.radians(a));
				
				new FreeMoveBullet(game, this, PApplet.sqrt(tmpx * tmpx + tmpy * tmpy), PApplet.atan2(tmpy, tmpx), a, -0.2f, color, 40);
			}
			this.kill();
		}
	}
}
