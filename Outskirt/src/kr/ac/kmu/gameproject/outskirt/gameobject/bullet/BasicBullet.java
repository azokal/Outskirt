package kr.ac.kmu.gameproject.outskirt.gameobject.bullet;

import kr.ac.kmu.gameproject.outskirt.GameObject;
import kr.ac.kmu.gameproject.outskirt.gameobject.Enemy;
import kr.ac.kmu.gameproject.outskirt.gameobject.SpaceSheep;
import kr.ac.kmu.gameproject.outskirt.screen.Game;

public class BasicBullet extends GameObject {

	float velocity;
	public float power;
	public Game.Color color;
	public SpaceSheep owner;
	
	public BasicBullet(Game game, SpaceSheep owner, float radius, float angle, float velocity, Game.Color color, float power) {
		super(game);
		this.velocity = velocity;
		this.color = color;
		oSprite = new sprites.Sprite(game.getApp(),game.pathSprites+"bulletGrid.png", 3, 1, 10);
		if (color == Game.Color.RED)
			oSprite.setFrame(1);
		if (color == Game.Color.CYAN)
			oSprite.setFrame(2);
		setPolar(radius, angle);
		this.owner = owner;
		this.power = power;
	}

	@Override
	public void draw() {
		oSprite.setScale(getRadius() / 200f);
		
		if (getRadius() < 0) {
			kill();
		} else {
			addRadius(-velocity);			
		}
	}
	
	@Override
	public void onCollide(GameObject obj) {
		if (obj instanceof Enemy) {
			((Enemy)obj).looseLife(this);
			kill();
			owner.addScore(100);
		}
	}
	
}
