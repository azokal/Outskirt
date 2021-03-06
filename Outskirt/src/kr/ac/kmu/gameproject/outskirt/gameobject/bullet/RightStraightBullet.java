package kr.ac.kmu.gameproject.outskirt.gameobject.bullet;

import kr.ac.kmu.gameproject.outskirt.GameObject;
import kr.ac.kmu.gameproject.outskirt.screen.Game;
import kr.ac.kmu.gameproject.outskirt.screen.Game.Color;

public class RightStraightBullet extends BasicBullet {

	public RightStraightBullet(Game game, GameObject owner, float radius,
			float angle, float velocity, Color color, float power) {
		super(game, owner, radius, angle, velocity, color, power);
	}

	@Override
	public void draw() {
		super.draw();
		if (game.timer.isRunning())
			addAngle(-1 / getRadius());
	}

}
