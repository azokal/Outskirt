package kr.ac.kmu.gameproject.outskirt.gameobject.bullet;

import kr.ac.kmu.gameproject.outskirt.GameObject;
import kr.ac.kmu.gameproject.outskirt.screen.Game;
import kr.ac.kmu.gameproject.outskirt.screen.Game.Color;

public class LeftStraightBullet extends BasicBullet {

	public LeftStraightBullet(Game game, GameObject owner, float radius,
			float angle, float velocity, Color color, float power) {
		super(game, owner, radius, angle, velocity, color, power);
	}

	@Override
	public void draw() {
		super.draw();
		addAngle(1 / getRadius());
	}

}
