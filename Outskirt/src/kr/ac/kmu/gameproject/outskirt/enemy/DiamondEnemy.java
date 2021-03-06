package kr.ac.kmu.gameproject.outskirt.enemy;

import kr.ac.kmu.gameproject.outskirt.screen.Game;
import kr.ac.kmu.gameproject.outskirt.screen.Game.Color;

public class DiamondEnemy extends Enemy {
	public DiamondEnemy(Game game, float startAngle, Color color, float life,
			float timing) {
		super(game, startAngle, color, life, timing, game.pathSprites
				+ "diamondGrid.png");
	}

	@Override
	public void move() {
		// float angle = PApplet.sin(PApplet.radians(game.timer.getTotalTime())
		// / 8) / 3;
		// setAngle(angle + PApplet.radians(startAngle));

		if (game.getApp().isPressed('o')) {
			addRadius(2f);
			game.getApp().getDebug().put("speedType", "slow");
		} else {
			addRadius(game.timer.getElapsedTime() / 10);// Progressive speed
			game.getApp().getDebug().put("Radius", getRadius());
			game.getApp().getDebug()
					.put("Elapsed Time", game.timer.getElapsedTime());
			game.getApp().getDebug().put("speedType", "fast");
		}
		// localAngle += PApplet.PI / 30.f;
		// oSprite.setRot(localAngle);
	}
}
