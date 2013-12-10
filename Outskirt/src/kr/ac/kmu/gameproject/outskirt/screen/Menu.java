package kr.ac.kmu.gameproject.outskirt.screen;

import kr.ac.kmu.gameproject.outskirt.App;
import kr.ac.kmu.gameproject.outskirt.HUD;
import kr.ac.kmu.gameproject.outskirt.gameobject.SpaceSheep;
import kr.ac.kmu.gameproject.outskirt.partition.Partition;
import kr.ac.kmu.gameproject.outskirt.screen.Game.Color;
import kr.ac.kmu.gameproject.outskirt.screen.Game.EnemyType;

public class Menu extends Game {
	int level;
	int nWaves;
	float cooldownPopEnemy = 500f;
	float lastPop = 0;
	public Menu(App app) {
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
					EnnemyFactory(EnemyType.values()[randInt(0, EnemyType.values().length-1)], randInt(0, 360),
								  Game.Color.values()[randInt(0, Color.values().length-1)], 40, 0);
			lastPop = timer.getTotalTime();
		}
	}
	
	@Override
	public void draw() {
		super.draw();
		app.textAlign(app.CENTER, app.TOP);
		app.fill(255, 255, 255);
		app.text("Press enter to start the game!", 0, 0);
		if (app.isPressed(App.ENTER)) {
			Game g = new AventureMode(app);
			app.setScreen(g);
		}
	}
	
}
