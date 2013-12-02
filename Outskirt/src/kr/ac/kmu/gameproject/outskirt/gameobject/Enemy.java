package kr.ac.kmu.gameproject.outskirt.gameobject;

import java.util.Random;

import kr.ac.kmu.gameproject.outskirt.GameObject;
import kr.ac.kmu.gameproject.outskirt.gameobject.bullet.BasicBullet;
import kr.ac.kmu.gameproject.outskirt.life.Life;
import kr.ac.kmu.gameproject.outskirt.screen.EndScreen;
import kr.ac.kmu.gameproject.outskirt.screen.Game;
import processing.core.PApplet;

public class Enemy extends Life {
	boolean isPop = false;
	float localAngle = 0;
	float startAngle = 0;
	Game.Color color;
	float testOccDir = 1;
	float timing;
	
	public Enemy(Game game, float startAngle, Game.Color color, float life, float timing) {
		super(game, life);
		this.timing = timing;
		oSprite = new sprites.Sprite(game.getApp(),game.pathSprites+"squareGrid.png", 3, 1, 10);
		this.startAngle = startAngle;
		setPolar(0, this.startAngle);
		oSprite.setScale(getRadius()/300f);
		this.color = color;
		if (color == Game.Color.RED)
			oSprite.setFrame(1);
		if (color == Game.Color.CYAN)
			oSprite.setFrame(2);
		Random rand = new Random();
		testOccDir = rand.nextInt(2) == 1 ? 1: -1;
		oSprite.setVisible(false);
	}
	
	public int getColor() {
		return (color.ordinal());
	}
	
	public float getLife() {
		return (life);
	}
	
	public float getTiming() {
		return (timing);
	}
	
	public void draw() {
		if (game.totalTime < timing)
			return ;
		else if (isPop == false) {
			oSprite.setVisible(true);
			isPop = true;
		}
		if (getRadius() > 550) {
			this.kill();
		} else {
			oSprite.setScale(getRadius() / 300f);
			float angle = PApplet.sin(testOccDir
					* PApplet.radians(game.totalTime) / 8) / 3;
			setAngle(angle + PApplet.radians(startAngle));
			
			if (game.getApp().isPressed('o')) {
				addRadius(2f);
				game.getApp().getDebug().put("speedType", "slow");
			} else {
				addRadius(1 + getRadius() / 200);//Progressive speed
				game.getApp().getDebug().put("speedType", "fast");
			}
			localAngle += PApplet.PI / 30.f;
			oSprite.setRot(localAngle);
		}
	}
	
	public void onCollide(GameObject obj) {
		if (obj instanceof SpaceSheep) {
			game.getApp().setScreen(new EndScreen(game.getApp(), game));
		}
	}
	public void looseLife(BasicBullet coll) {
		if (color != coll.color) {
			life -= coll.power;
		} else {
			life -= coll.power / 2;
		}
		if (life <= 0) {
			kill();
			((SpaceSheep)coll.owner).addScore(100);
			if (color != coll.color) {
				((SpaceSheep)coll.owner).getCurrentWeapon().upPercentage(coll.color);
			} else {
				((SpaceSheep)coll.owner).getCurrentWeapon().upPercentage(coll.color);
				((SpaceSheep)coll.owner).getCurrentWeapon().upPercentage(coll.color);
			}
			((SpaceSheep)coll.owner).addCombo(color);
		}
	}
}
