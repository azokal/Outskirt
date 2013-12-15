package kr.ac.kmu.gameproject.outskirt.boss;

import kr.ac.kmu.gameproject.outskirt.GameObject;
import kr.ac.kmu.gameproject.outskirt.enemy.ExplosionEnemy;
import kr.ac.kmu.gameproject.outskirt.gameobject.bullet.BasicBullet;
import kr.ac.kmu.gameproject.outskirt.screen.Game;

public class BossTwo extends BasicBoss {
	float cooldownShoot = 1500f;
	float lastShoot = 0f;
	private boolean isPop = false;

	public BossTwo(Game game, float maxLife, float timing) {
		super(game, maxLife);
		bossPart = new GameObject[3];
		this.bossPart[0] = new BossTwoPart(game, maxLife, this,
				Game.Color.GREEN, timing);
		this.bossPart[1] = new BossTwoPart(game, maxLife, this,
				Game.Color.CYAN, timing);
		this.bossPart[2] = new BossTwoPart(game, maxLife, this, Game.Color.RED,
				timing);
		this.timing = timing;
	}

	public void looseLife(BasicBullet coll) {
		this.life -= coll.power;
		for (BasicBoss b : bossLink) {
			b.setLife(life);
		}
		if (life <= 0) {
			for (GameObject o : bossPart) {
				o.kill();
			}
			for (BasicBoss b : bossLink) {
				b.kill();
			}
			game.tpop.start();
			kill();
		}
	}

	public void draw() {
		if (game.tpop.getTotalTime() < timing)
			return;
		else if (isPop == false) {
			isPop = true;
		}
		if (isPop == true) {
			if (game.timer.getTotalTime() - lastShoot > cooldownShoot) {
				for (float a = 0.0f; a < 360.0f; a += 40.0f) {
					new ExplosionEnemy(game, a,
							Game.Color.values()[(int) (a % 3)], 50, 0);
				}
				lastShoot = game.timer.getTotalTime();
			}
		}
	}

}
