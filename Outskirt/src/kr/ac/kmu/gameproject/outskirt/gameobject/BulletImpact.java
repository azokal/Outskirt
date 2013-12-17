package kr.ac.kmu.gameproject.outskirt.gameobject;

import kr.ac.kmu.gameproject.outskirt.GameObject;
import kr.ac.kmu.gameproject.outskirt.screen.Game;
import ddf.minim.AudioSample;

public class BulletImpact extends GameObject {

	float createtime;
	AudioSample impactSound = game.getApp().getSoundManager().getImpact();

	public BulletImpact(Game game, float radius, float angle) {
		super(game);
		createtime = game.timer.getTotalTime();
		oSprite = new sprites.Sprite(game.getApp(), game.pathSprites
				+ "Impact.png", 1, 1, 10);
		setPolar(radius, angle);
		impactSound.trigger();
	}

	@Override
	public void draw() {
		oSprite.setScale(getRadius() / 1000f);
		if (game.timer.getTotalTime() - createtime > 100) {
			kill();
		}
	}

}
