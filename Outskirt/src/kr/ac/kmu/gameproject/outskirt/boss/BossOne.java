package kr.ac.kmu.gameproject.outskirt.boss;

import kr.ac.kmu.gameproject.outskirt.GameObject;
import kr.ac.kmu.gameproject.outskirt.gameobject.bullet.BasicBullet;
import kr.ac.kmu.gameproject.outskirt.life.Life;
import kr.ac.kmu.gameproject.outskirt.screen.Game;

public class BossOne extends BasicBoss {
	float timing;
	
	public BossOne(Game game, float maxLife, float timing) {
		super(game, maxLife);
		bossPart = new GameObject[3];
		this.bossPart[0] = new BossOnePart(game, maxLife, this, Game.Color.GREEN, timing);
		this.bossPart[1] = new BossOnePart(game, maxLife, this, Game.Color.CYAN, timing);
		this.bossPart[2] = new BossOnePart(game, maxLife, this, Game.Color.RED, timing);
		this.timing = timing;
	}


	public void looseLife(BasicBullet coll) {
		this.life -= coll.power;
		if (life <= 0) {
			for (GameObject o: bossPart) {
				o.kill();
			}
			kill();
		}
	}


	public void draw() {

	}

	public float getTiming() {
		return timing;
	}
}