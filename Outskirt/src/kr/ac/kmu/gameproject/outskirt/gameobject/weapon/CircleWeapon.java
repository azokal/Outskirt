package kr.ac.kmu.gameproject.outskirt.gameobject.weapon;

import processing.core.PApplet;
import kr.ac.kmu.gameproject.outskirt.GameObject;
import kr.ac.kmu.gameproject.outskirt.gameobject.bullet.BasicBullet;
import kr.ac.kmu.gameproject.outskirt.screen.Game;
import kr.ac.kmu.gameproject.outskirt.screen.Game.Color;

public class CircleWeapon  extends BasicWeapon {

	public CircleWeapon(Game game, GameObject owner, Color color) {
		super(game, owner, color);
		this.cooldownShoot = 500.0f;
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
			float C = PApplet.sqrt(tmpx * tmpx + tmpy * tmpy);
			float B = PApplet.sqrt(x * -x + y * y);
			float A = PApplet.sqrt((x-tmpx) * (x-tmpx) + (y-tmpy) * (y-tmpy));
			
			BasicBullet b = new BasicBullet(game, owner, C, PApplet.acos((C*C + B*B - A*A) / (2*B*C)), -2, color, 1);
			//new BasicBullet(game, owner, 50, PApplet.radians(a), -2, color, 1);
		}
	}
}
