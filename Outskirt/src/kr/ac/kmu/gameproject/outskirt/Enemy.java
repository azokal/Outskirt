package kr.ac.kmu.gameproject.outskirt;

import processing.core.PApplet;
import processing.event.KeyEvent;

public class Enemy extends GameObject {

	float localAngle = 0;
	
	public Enemy(Game game) {
		super(game);
		oSprite = new sprites.Sprite(game,"squareGrid.png", 3, 1, 10);
		setPolar(0, 0);
	}
	
	public void draw() {
		oSprite.setScale(getRadius()/300f);
		float angle = PApplet.sin(game.radians(game.timer.getTotalTime()) / 8)/3;
		setAngle(angle);
		addRadius(2f);
		localAngle += game.PI / 30.f;
		oSprite.setRot(localAngle);
	}
	
}
