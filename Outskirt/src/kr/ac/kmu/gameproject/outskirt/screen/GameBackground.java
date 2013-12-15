package kr.ac.kmu.gameproject.outskirt.screen;

import kr.ac.kmu.gameproject.outskirt.App;

public class GameBackground extends Game {

	int level;
	int nWaves;
	float cooldownPopEnemy = 500f;
	float lastPop = 0;

	public GameBackground(App app) {
		super(app);
	}

	@Override
	void init() {
		level = 1;
	}

	@Override
	void endLevel() {
		if (totalTime - lastPop > cooldownPopEnemy) {
			for (int i = 0; i < level; i++)
				EnnemyFactory(
						EnemyType.values()[randInt(0,
								EnemyType.values().length - 1)],
						randInt(0, 360),
						Game.Color.values()[randInt(0,
								Color.values().length - 1)], 40, 0);
			lastPop = timer.getTotalTime();
		}
	}

}
