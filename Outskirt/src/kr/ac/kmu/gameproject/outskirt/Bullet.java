package kr.ac.kmu.gameproject.outskirt;

import processing.core.PApplet;

public class Bullet extends GameObject{

	float velocity;
	Game.Color color;
	
	public Bullet(Game game, float radius, float angle, float velocity, Game.Color color) {
		super(game);
		this.velocity = velocity;
		this.color = color;
		oSprite = new sprites.Sprite(game,"bulletGrid.png", 3, 1, 10);
		if (color == Game.Color.RED)
			oSprite.setFrame(1);
		if (color == Game.Color.CYAN)
			oSprite.setFrame(2);
		setPolar(radius, angle);
	}

	public void draw() {
		
		
		if (getRadius() < 0) {
			game.delGameObject(this);
			oSprite.setDead(true);
		} else {
			addRadius(-velocity);			
		}
	}
	
}
