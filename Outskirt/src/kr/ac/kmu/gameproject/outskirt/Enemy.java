package kr.ac.kmu.gameproject.outskirt;

import processing.core.PApplet;
import processing.event.KeyEvent;

public class Enemy extends GameObject {

	public Enemy(Game game) {
		super(game);
		oSprite = new sprites.Sprite(game,"fzeroracers-sprites.gif", 16, 3, 10);
		setPolar(0, 0);
	}
	
	public void draw() {
		float angle = PApplet.sin(game.timer.getTotalTime() / 100);
		setAngle(angle);
		addRadius(1f);
	}
	
}
