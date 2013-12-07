package kr.ac.kmu.gameproject.outskirt.enemy;

import java.util.Random;

import processing.core.PApplet;
import kr.ac.kmu.gameproject.outskirt.screen.Game;
import kr.ac.kmu.gameproject.outskirt.screen.Game.Color;

public class SquareEnemy extends Enemy{
	float testOccDir = 1;

	public SquareEnemy(Game game, float startAngle, Color color, float life,
			float timing) {
		super(game, startAngle, color, life, timing, game.pathSprites+"squareGrid.png");
		Random rand = new Random();
		testOccDir = rand.nextInt(2) == 1 ? 1: -1;
	}

	@Override
	public void move() {
		float angle = PApplet.sin(testOccDir
				* PApplet.radians(game.timer.getTotalTime()) / 8) / 3;
		setAngle(angle + PApplet.radians(startAngle));
		
		if (game.getApp().isPressed('o')) {
			addRadius(2f);
			game.getApp().getDebug().put("speedType", "slow");
		} else {
			addRadius(game.timer.getElapsedTime() / 10);//Progressive speed
			game.getApp().getDebug().put("Radius", getRadius());
			game.getApp().getDebug().put("Elapsed Time", game.timer.getElapsedTime());
			game.getApp().getDebug().put("speedType", "fast");
		}
		localAngle += PApplet.PI / 30.f;
		oSprite.setRot(localAngle);
	}
}
