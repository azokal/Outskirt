package kr.ac.kmu.gameproject.outskirt.gameobject;

import kr.ac.kmu.gameproject.outskirt.GameObject;
import kr.ac.kmu.gameproject.outskirt.screen.Game;

public class BulletImpact extends GameObject {

	float createtime;

	public BulletImpact(Game game, float radius, float angle) {
		super(game);
		createtime = game.timer.getTotalTime();
		oSprite = new sprites.Sprite(game.getApp(), game.pathSprites
				+ "Impact.png", 1, 1, 10);
		setPolar(radius, angle);
	}

	@Override
	public void draw() {
		oSprite.setScale(getRadius() / 1000f);
		if (game.timer.getTotalTime() - createtime > 100) {
			kill();
		}
	}

}
