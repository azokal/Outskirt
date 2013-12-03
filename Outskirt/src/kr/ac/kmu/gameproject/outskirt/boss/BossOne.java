package kr.ac.kmu.gameproject.outskirt.boss;

import kr.ac.kmu.gameproject.outskirt.gameobject.bullet.BasicBullet;
import kr.ac.kmu.gameproject.outskirt.screen.Game;

public class BossOne extends BasicBoss {
	float timing;
	
	public BossOne(Game game, float maxLife, float timing) {
		super(game, maxLife);
		this.bossPart.add(new BossOnePart(game, maxLife, this, Game.Color.GREEN, timing));
		this.bossPart.add(new BossOnePart(game, maxLife, this, Game.Color.CYAN, timing));
		this.bossPart.add(new BossOnePart(game, maxLife, this, Game.Color.RED, timing));
		this.timing = timing;
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

	public float getTiming() {
		return timing;
	}
}
