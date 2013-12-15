package kr.ac.kmu.gameproject.outskirt.gameobject.bullet;

import processing.core.PApplet;
import kr.ac.kmu.gameproject.outskirt.GameObject;
import kr.ac.kmu.gameproject.outskirt.screen.Game;
import kr.ac.kmu.gameproject.outskirt.screen.Game.Color;

public class FreeMoveBullet extends BasicBullet {

	private float angleDir;

	public FreeMoveBullet(Game game, GameObject owner, float radius,
			float angle, float angleDir, float velocity, Color color,
			float power) {
		super(game, owner, radius, angle, velocity, color, power);
		this.angleDir = angleDir;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw() {
		oSprite.setScale(getRadius() / 200f);

		if (getRadius() < 0) {
			kill();
		} else if (getRadius() > 550) {
			kill();
		} else {
			setCart(getX() + velocity * PApplet.cos(PApplet.radians(angleDir))
					* game.timer.getElapsedTime(),
					getY() + velocity * PApplet.sin(PApplet.radians(angleDir))
							* game.timer.getElapsedTime());
			oSprite.setRot(PApplet.radians(angleDir) - PApplet.PI / 2);
		}
	}
}
