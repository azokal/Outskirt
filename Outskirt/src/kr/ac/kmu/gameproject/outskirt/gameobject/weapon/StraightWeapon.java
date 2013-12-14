package kr.ac.kmu.gameproject.outskirt.gameobject.weapon;

import kr.ac.kmu.gameproject.outskirt.gameobject.SpaceSheep;
import kr.ac.kmu.gameproject.outskirt.gameobject.bullet.LeftStraightBullet;
import kr.ac.kmu.gameproject.outskirt.gameobject.bullet.RightStraightBullet;
import kr.ac.kmu.gameproject.outskirt.screen.Game;

public class StraightWeapon extends BasicPlayerWeapon {
	
	public StraightWeapon(Game game, SpaceSheep owner, Game.Color color) {
		super(game, owner, color);
	}

	@Override
	public void generateBullet() {
		new RightStraightBullet(game, owner, owner.getRadius(), owner.getAngle() - 0.1f, 0.001f, color, percentage[color.ordinal()] * power / 100.0f);
		new LeftStraightBullet(game, owner, owner.getRadius(), owner.getAngle() + 0.1f, 0.001f, color, percentage[color.ordinal()] * power / 100.0f);
	}
}
