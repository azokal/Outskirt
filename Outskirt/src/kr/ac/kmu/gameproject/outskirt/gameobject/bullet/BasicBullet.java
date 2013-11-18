package kr.ac.kmu.gameproject.outskirt.gameobject.bullet;

import kr.ac.kmu.gameproject.outskirt.Game;
import kr.ac.kmu.gameproject.outskirt.GameObject;
import kr.ac.kmu.gameproject.outskirt.gameobject.Enemy;
import kr.ac.kmu.gameproject.outskirt.gameobject.SpaceSheep;

public class BasicBullet extends GameObject {

	float velocity;
	Game.Color color;
	SpaceSheep owner;
	
	public BasicBullet(Game game, SpaceSheep owner, float radius, float angle, float velocity, Game.Color color) {
		super(game);
		this.velocity = velocity;
		this.color = color;
		oSprite = new sprites.Sprite(game,"bulletGrid.png", 3, 1, 10);
		if (color == Game.Color.RED)
			oSprite.setFrame(1);
		if (color == Game.Color.CYAN)
			oSprite.setFrame(2);
		setPolar(radius, angle);
		this.owner = owner;
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
			obj.kill();
			owner.addScore(100);
		}
	}
	
}
