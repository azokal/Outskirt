package kr.ac.kmu.gameproject.outskirt.screen;

import java.util.Random;

import processing.core.PApplet;
import kr.ac.kmu.gameproject.outskirt.App;
import kr.ac.kmu.gameproject.outskirt.HUD;
import kr.ac.kmu.gameproject.outskirt.gameobject.SpaceSheep;
import kr.ac.kmu.gameproject.outskirt.partition.Partition;
import kr.ac.kmu.gameproject.outskirt.screen.Game.EnemyType;

public class RandomMode extends Game{
	int level;
	int nWaves;
	float cooldownPopEnemy = 500f;
	float lastPop = 0;
	
	public RandomMode(App app) {
		super(app);
	}

	
	@Override
	void init() {
		level = 1;
		nWaves = 0;
		spaceSheep = new SpaceSheep(this, false);
		this.hud = new HUD(this, spaceSheep);
	}

	@Override
	void endLevel() {
		if (totalTime - lastPop > cooldownPopEnemy) {
			if (nWaves == 50) {
				if (level == 1)
					BossFactory(Game.BossType.values()[0], 2000, 0);
				else if (level == 2)
					BossFactory(Game.BossType.values()[1], 2000, 0);
				else if (level == 3)
					BossFactory(Game.BossType.values()[2], 2000, 0);
				else if (level == 4) {
					BossFactory(Game.BossType.values()[0], 2000, 0);
					BossFactory(Game.BossType.values()[1], 2000, 0);
				}
				else if (level == 5) {
					BossFactory(Game.BossType.values()[0], 2000, 0);
					BossFactory(Game.BossType.values()[2], 2000, 0);
				}
				else if (level == 6) {
					BossFactory(Game.BossType.values()[1], 2000, 0);
					BossFactory(Game.BossType.values()[2], 2000, 0);
				}
				else {
					BossFactory(Game.BossType.values()[0], 2000, 0);
					BossFactory(Game.BossType.values()[1], 2000, 0);
					BossFactory(Game.BossType.values()[2], 2000, 0);
				}
				nWaves = 0;
				level++;
				tpop.stop();
			}
			else if (tpop.isRunning()){
				for (int i = 0; i < level; i++)
					EnnemyFactory(EnemyType.values()[randInt(0, EnemyType.values().length-1)], randInt(0, 360),
								  Game.Color.values()[randInt(0, Color.values().length-1)], 40, 0);
				nWaves++;
			}
			lastPop = timer.getTotalTime();
		}
	}
}
