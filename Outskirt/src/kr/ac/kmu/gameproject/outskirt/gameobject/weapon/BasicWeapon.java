package kr.ac.kmu.gameproject.outskirt.gameobject.weapon;

import kr.ac.kmu.gameproject.outskirt.Game;
import kr.ac.kmu.gameproject.outskirt.GameObject;
import kr.ac.kmu.gameproject.outskirt.gameobject.SpaceSheep;
import kr.ac.kmu.gameproject.outskirt.gameobject.bullet.BasicBullet;

public class BasicWeapon extends GameObject implements Weapon {
	
	public SpaceSheep owner;
	float cooldownShoot = 100f; //100ms
	float lastShoot = 0.0f;
	Game.Color color = Game.Color.GREEN;

	public BasicWeapon(Game game, SpaceSheep owner) {
		super(game);
		this.owner = owner;
	}

	@Override
	public void shoot() {
		if (game.timer.getTotalTime() - lastShoot > cooldownShoot)
		{
			BasicBullet tmp = new BasicBullet(game, owner, owner.getRadius(), owner.getAngle(), 4, color);
			lastShoot = game.timer.getTotalTime();
		}
	}

	@Override
	public void draw() {
		if (game.isPressed('q')) {
			color = Game.Color.GREEN;
			oSprite.setFrame(0);
		}
		if (game.isPressed('w')) {
			color = Game.Color.RED;
			oSprite.setFrame(1);
		}
		if (game.isPressed('e')) {
			color = Game.Color.CYAN;
			oSprite.setFrame(2);
		}
	}
	
}
