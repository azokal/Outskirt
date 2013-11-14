package kr.ac.kmu.gameproject.outskirt;

import java.util.HashSet;
import java.util.Set;

import processing.core.PApplet;

public class SpaceSheep extends GameObject {

	Set<Bullet> bullets = new HashSet<Bullet>();
	
	public SpaceSheep(Game game) {
		super(game);
		oSprite = new sprites.Sprite(game, "playerGrid.png", 3, 1, 10);
		setPolar(450, 0);
	}

	public void shoot() {
	}

	public void draw() {
		if (game.keyPressed) {
			if (game.key == PApplet.CODED) {
				if (game.keyCode == PApplet.LEFT) {
					addAngle(0.1f);
				} else if (game.keyCode == PApplet.RIGHT) {
					addAngle(-0.1f);
				}
			}
			if (game.key == ' ') {
				bullets.add(new Bullet(game, this.getRadius(), this.getAngle(), 1));
			}
		}
		
		for (Bullet bullet : bullets) {
			bullet.draw();
		}
		
	}
}
