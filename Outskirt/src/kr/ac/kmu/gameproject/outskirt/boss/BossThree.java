package kr.ac.kmu.gameproject.outskirt.boss;

import kr.ac.kmu.gameproject.outskirt.GameObject;
import kr.ac.kmu.gameproject.outskirt.gameobject.bullet.BasicBullet;
import kr.ac.kmu.gameproject.outskirt.screen.Game;
import processing.core.PApplet;

public class BossThree extends BasicBoss {
	float cooldownShoot = 3000f;
	float lastShoot = 0f;
	int nbShoot = 0;
	int phase = 0;
	private boolean isPop = false;
	private boolean isInTransition = false;

	public BossThree(Game game, float maxLife, float timing) {
		super(game, maxLife);
		bossPart = new GameObject[12];
		this.bossPart[0] = new BossThreePart(game, maxLife, this, Game.Color.GREEN, timing);
		this.bossPart[1] = new BossThreePart(game, maxLife, this, Game.Color.CYAN, timing);
		this.bossPart[2] = new BossThreePart(game, maxLife, this, Game.Color.RED, timing);
		for (int a = 0; a < 360; a += 360/9) {
			this.bossPart[3 + a/(360/9)] = new SpiralBossMob(game, a,  90, Game.Color.values()[(a/(360/9)) % 3], 100, timing);
		}
		this.timing = timing;
	}

	

	public void looseLife(BasicBullet coll) {
		this.life -= coll.power;
		if (life <= 0) {
			for (GameObject o: bossPart) {
				o.kill();
			}
			game.tpop.start();
			kill();
		}
		else if (life/maxLife <= 0.66 && phase == 0) {
			lastShoot = game.timer.getTotalTime();
			transition();
			phase++;
		}
		else if (life/maxLife <= 0.33 && phase == 1) {
			lastShoot = game.timer.getTotalTime();
			transition();
			phase++;
		}
	}
	
	public void transition() {
		isInTransition = true;
		if (game.timer.getTotalTime() - lastShoot < 10000f) {
			for (GameObject o: bossPart) {
				if (o instanceof SpiralBossMob) {
					SpiralBossMob b = (SpiralBossMob) o;
					b.resetShoot();
					b.addAngle(PApplet.PI / 20.0f);
				}
			}
		}
		else
			isInTransition = false;
	}

	public void draw() {
		if (game.tpop.getTotalTime() < timing)
			return ;
		else if (isPop  == false) {
			isPop = true;
		}
		if (isInTransition == false && isPop == true) {
			if (game.timer.getTotalTime() - lastShoot > cooldownShoot)
			{
				for (GameObject o: bossPart) {
					if (o instanceof SpiralBossMob) {
						SpiralBossMob b = (SpiralBossMob) o;
						if (phase == 0) {
							if (b.getColor() == nbShoot%3)
								b.resetShoot();
						}
						else if (phase == 1) {
							if (b.getColor() == nbShoot%3 || b.getColor() == (nbShoot+1)%3)
								b.resetShoot();
						}
						else
							b.resetShoot();
					}
				}
				nbShoot++;
				lastShoot = game.timer.getTotalTime();
			}
		}
		else
			transition();
	}

}
