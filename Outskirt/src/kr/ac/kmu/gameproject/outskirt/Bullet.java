package kr.ac.kmu.gameproject.outskirt;

import processing.core.PApplet;

public class Bullet extends GameObject{

	float velocity;
	
	public Bullet(Game game, float radius, float angle, float velocity) {
		super(game);
		this.velocity = velocity;
		oSprite = new sprites.Sprite(game,"fzeroracers-sprites.gif", 16, 3, 10);
		setPolar(radius, angle);
	}

	public void draw() {
		addRadius(-velocity);
	}
	
}
