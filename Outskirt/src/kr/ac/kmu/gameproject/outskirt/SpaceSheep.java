package kr.ac.kmu.gameproject.outskirt;

import java.util.HashSet;
import java.util.Set;

import processing.core.PApplet;

public class SpaceSheep extends GameObject {

	Set<Bullet> bullets = new HashSet<Bullet>();
	Game.Color color = Game.Color.GREEN;
	int cooldownShoot = 0;
	
	public SpaceSheep(Game game) {
		super(game);
		oSprite = new sprites.Sprite(game, "playerGrid.png", 3, 1, 10);
		setPolar(450, 0);
	}

	public void shoot() {
	}

	public void draw() {
		if (game.isPressed(PApplet.LEFT)) {
			addAngle(0.08f);
		}
		if (game.isPressed(PApplet.RIGHT)) {
			addAngle(-0.08f);
		}
		if (game.isPressed(' ')) {
			if (cooldownShoot == 0)
			{
				Bullet tmp = new Bullet(game, this.getRadius(), this.getAngle(), 4, color);
				bullets.add(tmp);
				game.addObject(tmp);
				cooldownShoot = 5;
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
		if (cooldownShoot > 0)
			cooldownShoot--;
	}
}
