package kr.ac.kmu.gameproject.outskirt;

import processing.core.PApplet;
import processing.event.KeyEvent;

public class SpaceSheep  extends GameObject {

	public SpaceSheep(Game game) {
		super(game);
		oSprite = new sprites.Sprite(game,"fzeroracers-sprites.gif", 16, 3, 10);
		setPolar(450, game.PI);
	}
	public void keyEvent(KeyEvent e) {
	
	}
	public void draw() {
		if (game.keyPressed)
		  if (game.key == PApplet.CODED)
			    if (game.keyCode == PApplet.LEFT) {
			    	addAngle(0.1f);
			    } else if (game.keyCode == PApplet.RIGHT) {
			    	addAngle(-0.1f);
			    } else if (game.keyCode == PApplet.DOWN) {

			    } else if (game.keyCode == PApplet.UP) {

			    }
			    if (game.keyCode == ' ') {

			    }
	}
}