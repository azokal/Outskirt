package kr.ac.kmu.gameproject.outskirt.enemy;

import java.util.Random;

import processing.core.PApplet;
import kr.ac.kmu.gameproject.outskirt.gameobject.bullet.BasicBullet;
import kr.ac.kmu.gameproject.outskirt.gameobject.bullet.FreeMoveBullet;
import kr.ac.kmu.gameproject.outskirt.screen.Game;
import kr.ac.kmu.gameproject.outskirt.screen.Game.Color;

public class ExplosionEnemy extends Enemy{
	public ExplosionEnemy(Game game, float startAngle, Color color, float life,
			float timing) {
		super(game, startAngle, color, life, timing, game.pathSprites+"diamondGrid.png");
	}

	@Override
	public void move() {
		addRadius(game.timer.getElapsedTime() / 10f);//Progressive speed

		localAngle += PApplet.PI / 30.f;
		oSprite.setRot(localAngle);
	}
	
	@Override
	public void draw() {
		if (game.pop < timing && isPop == false)
			return ;
		else if (isPop == false) {
			oSprite.setVisible(true);
			isPop = true;
		}
		if (getRadius() > 275) {
			float x = getRadius() * PApplet.cos(getAngle());
			float y = getRadius() * PApplet.sin(getAngle());
			for (float a = 0.0f; a < 360.0f; a += 20.0f) {
				float tmpx = x + 10.0f * PApplet.cos(PApplet.radians(a));
				float tmpy = y + 10.0f * PApplet.sin(PApplet.radians(a));
				
				BasicBullet b = new FreeMoveBullet(game, this, PApplet.sqrt(tmpx * tmpx + tmpy * tmpy), PApplet.atan2(tmpy, tmpx), a, -4, color, 40);
			}
			this.kill();
		} else {
			oSprite.setScale(getRadius() / 300f);
			move();
		}
	}
}
