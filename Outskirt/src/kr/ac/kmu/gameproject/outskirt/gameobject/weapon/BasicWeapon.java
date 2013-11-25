package kr.ac.kmu.gameproject.outskirt.gameobject.weapon;

import kr.ac.kmu.gameproject.outskirt.GameObject;
import kr.ac.kmu.gameproject.outskirt.gameobject.SpaceSheep;
import kr.ac.kmu.gameproject.outskirt.gameobject.bullet.BasicBullet;
import kr.ac.kmu.gameproject.outskirt.screen.Game;

public class BasicWeapon extends GameObject implements Weapon {
	
	public SpaceSheep owner;
	float cooldownShoot = 100f; //100ms
	float lastShoot = 0.0f;
	public float[] percentage = {34, 33, 33}; 
	public float power = 20.0f;
	Game.Color color;

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
			new BasicBullet(game, owner, owner.getRadius(), owner.getAngle(), 4, color, percentage[color.ordinal()] * power / 100.0f);
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
	
	void manipulatePercentage(int[] minus, int add) {
		if (percentage[minus[0]] > 1) {
			percentage[minus[0]]--;
		}
		if (percentage[minus[1]] > 1) {
			percentage[minus[1]]--;
		}
		percentage[add] = 100 - percentage[minus[0]] - percentage[minus[1]];
	}
	
	public void upPercentage(Game.Color color) {
		if (color.ordinal() == 0) {
			int[] tmp = {2,1};
			manipulatePercentage(tmp, 0);
		}
		else if (color.ordinal() ==	1) {
			int[] tmp = {0,2};
			manipulatePercentage(tmp, 1);
		}
		else if (color.ordinal() ==	2) {
			int[] tmp = {0,1};
			manipulatePercentage(tmp, 2);
		}
	}
	
}
