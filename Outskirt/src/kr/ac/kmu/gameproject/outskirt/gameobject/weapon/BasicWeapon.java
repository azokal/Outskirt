package kr.ac.kmu.gameproject.outskirt.gameobject.weapon;

import kr.ac.kmu.gameproject.outskirt.GameObject;
import kr.ac.kmu.gameproject.outskirt.gameobject.SpaceSheep;
import kr.ac.kmu.gameproject.outskirt.gameobject.bullet.BasicBullet;
import kr.ac.kmu.gameproject.outskirt.screen.Game;

public class BasicWeapon extends GameObject implements Weapon {
	
	public SpaceSheep owner;
	float cooldownShoot = 100f; //100ms
	float lastShoot = 0.0f;
	Game.Color color;
	float[] power = {10, 10, 10};

	public BasicWeapon(Game game, SpaceSheep owner) {
		this(game, owner, Game.Color.GREEN);
		
	}
	
	public BasicWeapon(Game game, SpaceSheep owner, Game.Color color) {
		super(game);
		this.owner = owner;
		this.color = color;
	}

	@Override
	public void shoot() {
		if (game.timer.getTotalTime() - lastShoot > cooldownShoot)
		{
			new BasicBullet(game, owner, owner.getRadius(), owner.getAngle(), 4, color, power[color.ordinal()]);
			lastShoot = game.timer.getTotalTime();
		}
	}
	
	@Override
	public void draw() {
		if (game.getApp().isPressed('q')) {
			color = Game.Color.GREEN;
			owner.setGreen();
		}
		if (game.getApp().isPressed('w')) {
			color = Game.Color.RED;
			owner.setRed();
		}
		if (game.getApp().isPressed('e')) {
			color = Game.Color.CYAN;
			owner.setBlue();
		}
	}
	
}
