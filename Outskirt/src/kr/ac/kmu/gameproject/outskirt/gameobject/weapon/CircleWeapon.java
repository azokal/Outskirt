package kr.ac.kmu.gameproject.outskirt.gameobject.weapon;

import kr.ac.kmu.gameproject.outskirt.GameObject;
import kr.ac.kmu.gameproject.outskirt.gameobject.bullet.FreeMoveBullet;
import kr.ac.kmu.gameproject.outskirt.screen.Game;
import kr.ac.kmu.gameproject.outskirt.screen.Game.Color;
import processing.core.PApplet;

public class CircleWeapon  extends BasicWeapon {

	public CircleWeapon(Game game, GameObject owner, Color color) {
		super(game, owner, color);
		this.cooldownShoot = 500.0f;
		power = 10;
	}

	@Override
	public void draw() {
		
	}

	@Override
	public void generateBullet() {
		float x = owner.getRadius() * PApplet.cos(owner.getAngle());
		float y = owner.getRadius() * PApplet.sin(owner.getAngle());
		for (float a = 0.0f; a < 360.0f; a += 20.0f) {
			float tmpx = x + 10.0f * PApplet.cos(PApplet.radians(a));
			float tmpy = y + 10.0f * PApplet.sin(PApplet.radians(a));
			
			new FreeMoveBullet(game, owner, PApplet.sqrt(tmpx * tmpx + tmpy * tmpy), PApplet.atan2(tmpy, tmpx), a, -0.2f, color, power);
		}
	}
}
