package kr.ac.kmu.gameproject.outskirt.screen;

import kr.ac.kmu.gameproject.outskirt.App;
import kr.ac.kmu.gameproject.outskirt.HUD;
import kr.ac.kmu.gameproject.outskirt.boss.BasicBoss;
import kr.ac.kmu.gameproject.outskirt.gameobject.SpaceSheep;
import kr.ac.kmu.gameproject.outskirt.gameobject.TextObject;

public class RandomMode extends Game {
	int level;
	int nWaves;
	float cooldownPopEnemy = 500f;
	float lastPop = 0;

	public RandomMode(App app) {
		super(app);
		activatePause();
	}

	@Override
	void init() {
		level = 1;
		nWaves = 0;
		spaceSheep = new SpaceSheep(this, false);
		this.hud = new HUD(this, spaceSheep);
		new TextObject(this, "Level 1", 100, 0, 1000);
	}

	@Override
	void endLevel() {
		if (totalTime - lastPop > cooldownPopEnemy) {
			if (nWaves == 50) {
				if (level == 1) {
					BossFactory(Game.BossType.values()[0], 2000, 0);
				} else if (level == 2)
					BossFactory(Game.BossType.values()[1], 2000, 0);
				else if (level == 3)
					BossFactory(Game.BossType.values()[2], 2000, 0);
				else if (level == 4) {
					BasicBoss b1 = (BasicBoss) BossFactory(
							Game.BossType.values()[0], 2000, 0);
					BasicBoss b2 = (BasicBoss) BossFactory(
							Game.BossType.values()[1], 2000, 0);
					b1.addLinkBoss(b2);
					b2.addLinkBoss(b1);
				} else if (level == 5) {
					BasicBoss b1 = (BasicBoss) BossFactory(
							Game.BossType.values()[0], 2000, 0);
					BasicBoss b2 = (BasicBoss) BossFactory(
							Game.BossType.values()[2], 2000, 0);
					b1.addLinkBoss(b2);
					b2.addLinkBoss(b1);
				} else if (level == 6) {
					BasicBoss b1 = (BasicBoss) BossFactory(
							Game.BossType.values()[1], 2000, 0);
					BasicBoss b2 = (BasicBoss) BossFactory(
							Game.BossType.values()[2], 2000, 0);
					b1.addLinkBoss(b2);
					b2.addLinkBoss(b1);
				} else {
					BasicBoss b1 = (BasicBoss) BossFactory(
							Game.BossType.values()[1], 2000, 0);
					BasicBoss b2 = (BasicBoss) BossFactory(
							Game.BossType.values()[2], 2000, 0);
					BasicBoss b3 = (BasicBoss) BossFactory(
							Game.BossType.values()[0], 2000, 0);
					b1.addLinkBoss(b2);
					b1.addLinkBoss(b3);
					b2.addLinkBoss(b1);
					b2.addLinkBoss(b3);
					b3.addLinkBoss(b1);
					b3.addLinkBoss(b2);
				}
				nWaves = 0;
				level++;
				tpop.reset();
				tpop.stop();
				new TextObject(this, "Level " + level, 100, 100, 1000);
			} else if (tpop.isRunning()) {
				for (int i = 0; i < level; i++)
					EnnemyFactory(
							EnemyType.values()[randInt(0,
									EnemyType.values().length - 1)],
							randInt(0, 360),
							Game.Color.values()[randInt(0,
									Color.values().length - 1)], 40, 0);
				nWaves++;
			}
			lastPop = timer.getTotalTime();
		}
	}
}
