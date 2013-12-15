package kr.ac.kmu.gameproject.outskirt.gameobject.bullet;

import kr.ac.kmu.gameproject.outskirt.GameObject;
import kr.ac.kmu.gameproject.outskirt.enemy.Enemy;
import kr.ac.kmu.gameproject.outskirt.gameobject.BulletImpact;
import kr.ac.kmu.gameproject.outskirt.gameobject.SpaceSheep;
import kr.ac.kmu.gameproject.outskirt.life.Life;
import kr.ac.kmu.gameproject.outskirt.screen.Game;

public class BasicBullet extends GameObject {

	float velocity;
	public float power;
	public Game.Color color;
	public GameObject owner;

	public BasicBullet(Game game, GameObject owner, float radius, float angle,
			float velocity, Game.Color color, float power) {
		super(game);
		this.velocity = velocity;
		this.color = color;
		oSprite = new sprites.Sprite(game.getApp(), game.pathSprites
				+ "bulletGrid.png", 3, 1, 10);
		if (color == Game.Color.RED)
			oSprite.setFrame(1);
		if (color == Game.Color.CYAN)
			oSprite.setFrame(2);
		setPolar(radius, angle);
		this.owner = owner;
		this.power = power;
		oSprite.setScale(getRadius() / 200f);
	}

	@Override
	public void draw() {
		oSprite.setScale(getRadius() / 200f);

		if (getRadius() < 0) {
			kill();
		} else if (getRadius() > 550) {
			kill();
		} else {
			// addRadius(-velocity);
			// if (game.getApp().isPressed('o')) {
			// addRadius(-velocity);
			// game.getApp().getDebug().put("speedType", "slow");
			// } else {
			addRadius(-1
					* (getRadius() * velocity * game.timer.getElapsedTime()));// Progressive
																				// speed
			game.getApp().getDebug().put("speedType", "fast");
			// }
		}
	}

	@Override
	public void onCollide(GameObject obj) {
		if (owner instanceof SpaceSheep && obj instanceof Enemy) {
			((Enemy) obj).looseLife(this);
			kill();
			new BulletImpact(game, getRadius(), getAngle());
		} else if (owner instanceof Life && obj instanceof SpaceSheep
				&& !(owner instanceof SpaceSheep)) {
			((SpaceSheep) obj).looseLife(this);
			kill();
			new BulletImpact(game, getRadius(), getAngle());
		} else if (owner instanceof SpaceSheep && obj instanceof Life
				&& !(obj instanceof SpaceSheep)) {
			((Life) obj).looseLife(this);
			kill();
			new BulletImpact(game, getRadius(), getAngle());
		}
	}

}
