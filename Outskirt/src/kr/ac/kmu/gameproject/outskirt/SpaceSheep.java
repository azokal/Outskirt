package kr.ac.kmu.gameproject.outskirt;

import java.util.HashSet;
import java.util.Set;

import processing.core.PApplet;

public class SpaceSheep extends GameObject {

	//Set<Bullet> bullets = new HashSet<Bullet>();
	Game.Color color = Game.Color.GREEN;
	float cooldownShoot = 100f; //100ms
	float lastShoot = 0.0f;
	
	public SpaceSheep(Game game) {
		super(game);
		oSprite = new sprites.Sprite(game, "playerGrid.png", 3, 1, 10);
		oSprite.setScale(1.4f);
		setPolar(450, 0);
	}

	public void draw() {
		
		if (game.isPressed(PApplet.LEFT)) {
			addAngle(0.08f);
		}
		if (game.isPressed(PApplet.RIGHT)) {
			addAngle(-0.08f);
		}
		if (game.isPressed(' ')) {
			if (game.timer.getTotalTime() - lastShoot > cooldownShoot)
			{
				Bullet tmp = new Bullet(game, this.getRadius(), this.getAngle(), 4, color);
				//bullets.add(tmp);
				lastShoot = game.timer.getTotalTime();
			}
		}
		if (game.isPressed('z')) {
			color = Game.Color.GREEN;
			oSprite.setFrame(0);
		}
		if (game.isPressed('x')) {
			color = Game.Color.RED;
			oSprite.setFrame(1);
		}
		if (game.isPressed('c')) {
			color = Game.Color.CYAN;
			oSprite.setFrame(2);
		}
	}
}
