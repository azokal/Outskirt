package kr.ac.kmu.gameproject.outskirt.boss;

import kr.ac.kmu.gameproject.outskirt.gameobject.bullet.BasicBullet;
import kr.ac.kmu.gameproject.outskirt.screen.Game;

public class BossOne extends BasicBoss {

	public BossOne(Game game, float maxLife) {
		super(game, maxLife);
		
	}

	@Override
	public void looseLife(BasicBullet coll) {
		this.life -= coll.power;
		if (life <= 0)
			kill();
	}

	@Override
	public void draw() {

	}

}
