package kr.ac.kmu.gameproject.outskirt.gameobject;

import java.util.Random;

import kr.ac.kmu.gameproject.outskirt.EndScreen;
import kr.ac.kmu.gameproject.outskirt.Game;
import kr.ac.kmu.gameproject.outskirt.GameObject;
import processing.core.PApplet;

public class Enemy extends GameObject {

	float localAngle = 0;
	float startAngle = 0;
	Game.Color color;
	float testOccDir = 1;
	
	public Enemy(Game game, float startAngle, Game.Color color) {
		super(game);
		oSprite = new sprites.Sprite(game.getApp(),"squareGrid.png", 3, 1, 10);
		setPolar(0, 0);
		this.startAngle = startAngle;
		oSprite.setScale(getRadius()/300f);
		this.color = color;
		if (color == Game.Color.RED)
			oSprite.setFrame(1);
		if (color == Game.Color.CYAN)
			oSprite.setFrame(2);
		Random rand = new Random();
		testOccDir = rand.nextInt(2) == 1 ? 1: -1;
	}
	
	public void draw() {
		if (getRadius() > 550) {
			this.kill();
		} else {
			oSprite.setScale(getRadius() / 300f);
			float angle = PApplet.sin(testOccDir
					* PApplet.radians(game.timer.getTotalTime()) / 8) / 3;
			setAngle(angle + PApplet.radians(startAngle));
			addRadius(2f);
			localAngle += PApplet.PI / 30.f;
			oSprite.setRot(localAngle);
		}
	}
	
	public void onCollide(GameObject obj) {
		if (obj instanceof SpaceSheep) {
			game.getApp().setScreen(new EndScreen(game.getApp(), game));
		}
	}
	
}
