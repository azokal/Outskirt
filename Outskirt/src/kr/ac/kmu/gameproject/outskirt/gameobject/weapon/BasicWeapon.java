package kr.ac.kmu.gameproject.outskirt.gameobject.weapon;

import kr.ac.kmu.gameproject.outskirt.GameObject;
import kr.ac.kmu.gameproject.outskirt.enemy.Enemy;
import kr.ac.kmu.gameproject.outskirt.gameobject.SpaceSheep;
import kr.ac.kmu.gameproject.outskirt.gameobject.bullet.BasicBullet;
import kr.ac.kmu.gameproject.outskirt.gameobject.bullet.LeftStraightBullet;
import kr.ac.kmu.gameproject.outskirt.gameobject.bullet.RightStraightBullet;
import kr.ac.kmu.gameproject.outskirt.screen.Game;

public abstract class BasicWeapon extends GameObject implements Weapon {
	
	public GameObject owner;
	float cooldownShoot = 100f; //100ms
	float lastShoot = 0.0f;
	public float[] percentage = {34, 33, 33}; 
	public float power = 20.0f;
	public float powerMax = 100.0f;
	Game.Color color;
	boolean player;
	
	public BasicWeapon(Game game, GameObject owner, Game.Color color) {
		super(game);
		this.owner = owner;
		this.color = color;
	}

	@Override
	public void shoot() {
		if (game.timer.getTotalTime() - lastShoot > cooldownShoot)
		{
			generateBullet();
			lastShoot = game.timer.getTotalTime();
		}
	}
	
	public void generateBullet() {
		new BasicBullet(game, owner, owner.getRadius(), owner.getAngle(), 0.001f, color, percentage[color.ordinal()] * power / 100.0f);
	}
	
	@Override
	public abstract void draw();
	
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
